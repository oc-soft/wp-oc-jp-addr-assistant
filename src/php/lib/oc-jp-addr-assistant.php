<?php

require_once implode('/', [__DIR__, 'oc-jp-addr-assistant-ajax.php']);

/**
 * manage send message
 */
class OcJpAddrAssistant {

    /**
     * domain name for gettext
     */
    static $i18n_domain = 'oc-jp-addr-assistant';

    /**
     * script handle
     */
    static $script_handle = 'oc-jp-addr-assistant';

    /**
     * javascript name
     */
    static $js_script_name = 'oc-jp-addr-assistant.js';

    /**
     * css style sheet name
     */
    static $css_style_name = 'oc-jp-addr-assistant.css';

    
    /**
     * oc send message instance
     */
    static $instance = null;


    /**
     * activate plugin
     */
    function activate_plugin() {
    } 

    /**
     * deactivate plugin 
     */
    function deactivate_plugin() {
    }

    /**
     * get inline script
     */
    function get_ajax_inline_script() {
        $ajax_url = admin_url('admin-ajax.php');
        $result = <<<EOT
window.oc = window.oc || { }
window.oc.jpAddrAssistant = window.oc.jpAddrAssistant || { }
window.oc.jpAddrAssistant.ajax = window.oc.jpAddrAssistant.ajax || { }
window.oc.jpAddrAssistant.ajax.url = '$ajax_url'
EOT;
        return $result;
    }



    /**
     * setup css directory
     */
    function setup_style($css_dir) {
        wp_register_style(self::$script_handle,
            implode('/', [$css_dir, self::$css_style_name]));
        wp_enqueue_style(self::$script_handle); 
    }


    /**
     * setup script
     */
    function setup_script($js_dir_url) {

        wp_register_script(self::$script_handle,
            implode('/', [$js_dir_url, self::$js_script_name]),
            [], false);

        wp_add_inline_script(
            self::$script_handle,
            $this->get_ajax_inline_script());


        wp_enqueue_script(self::$script_handle);
     }

    /**
     * handle init event
     */
    function on_init(
        $js_dir_url,
        $css_dir_url) {

        add_action('wp', function() use($js_dir_url, $css_dir_url) {
            $this->setup_script($js_dir_url);
        });
        load_plugin_textdomain(self::$i18n_domain);
    }


    /**
     * handle wp_loaded event
     */
    function on_wp_loaded() {

    }

    /**
     * setup plugin in
     */
    function run(
        $js_dir_url,
        $css_dir_url,
        $resource_dir) {

        add_action('init', 
            function() use($js_dir_url, $css_dir_url) {
                $this->on_init($js_dir_url, $css_dir_url);
        });
        OcJpAddrAssistantAjax::$instance->setup($resource_dir);
    }
}

OcJpAddrAssistant::$instance = new OcJpAddrAssistant;
// vi: se ts=4 sw=4 et:

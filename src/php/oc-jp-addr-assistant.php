<?php
/**
 * Plugin Name: Oc Jp Address assistant
 * Text Domain: oc-jp-addr-assistant
 */
require_once implode('/', [
    plugin_dir_path( __FILE__), 'lib', 'oc-jp-addr-assistant.php']);
/**
 * activate plugin
 */
function oc_jp_addr_assistant_activate() {

    OcJpAddrAssistant::$instance->activate_plugin();
}

/**
 * deactivate plugin
 */
function oc_jp_addr_assistant_deactivate() {
    OcJpAddrAssistant::$instance->deactivate_plugin();
}


register_activation_hook(__FILE__, 'oc_jp_addr_assistant_activate');
register_deactivation_hook(__FILE__, 'oc_jp_addr_assistant_deactivate');

OcJpAddrAssistant::$instance->run(
    implode('/', [plugin_dir_url(__FILE__), 'js']),
    implode('/', [plugin_dir_url(__FILE__), 'css']),
    implode('/', [plugin_dir_path(__FILE__), 'resource']));




// vi: se ts=4 sw=4 et:

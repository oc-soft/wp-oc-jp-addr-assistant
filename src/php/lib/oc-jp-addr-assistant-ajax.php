<?php

class OcJpAddrAssistantAjax {


    /**
     * send message ajax object
     */
    static $instance;

    /**
     * reply address 
     */
    function reply_address($resource_dir) {
        $file_name = 'postal-number.json';
        if (!empty($_REQUEST['kind'])) {
            if ('pref-city' == $_REQUEST['kind']) {
                $file_name = 'pref-city.json';
            } else {
                $file_name = 'postal-number.json';
            }
        }
        $contents = file_get_contents(
            implode('/', [ $resource_dir, $file_name ]));
        $length = strlen($contents);

        error_log('get address json');
        error_log(print_r($length, true));
        header('Content-Type: application/json');
        header("Content-Length: $length");
        echo $contents;
        wp_die();
    }



    /**
     * setup
     */
    function setup(
        $resource_dir) {
        $prefixes = [
            'wp_ajax',
            'wp_ajax_nopriv'
        ]; 

        $hdlr = function() use($resource_dir) {
            $this->reply_address($resource_dir);
        };

        foreach($prefixes as $prefix) {
            add_action("${prefix}_oc-jp-addr-assistant-get-address", $hdlr);
        }
    }
}

OcJpAddrAssistantAjax::$instance = new OcJpAddrAssistantAjax;

// vi: se ts=4 sw=4 et:

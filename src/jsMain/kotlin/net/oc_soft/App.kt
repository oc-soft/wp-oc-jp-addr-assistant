package net.oc_soft

import kotlinx.browser.window


/**
 * application to send email from form in wordpress post.
 */
class App(
    /**
     * manage address
     */
    val ui: Ui = Ui()) {


    /**
     * bind this application into html elements
     */
    fun bind() {
        ui.bind()
    }

    /**
     * unbind this application from html elements
     */
    fun unbind() {
        ui.unbind()
    }


    /**
     * run application
     */
    fun run() {
        window.addEventListener("load", {
                bind()
            },
            object {
                @JsName("once")
                val once = true
            })
        window.addEventListener("unload", {
                unbind()
            },
            object {
                @JsName("once")
                val once = true
            })
    }
}

// vi: se ts=4 sw=4 et:

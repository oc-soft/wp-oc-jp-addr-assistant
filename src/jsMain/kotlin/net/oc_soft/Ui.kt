package net.oc_soft


import kotlinx.browser.document
import kotlinx.browser.window

import kotlin.js.Promise
import kotlin.js.Json

import kotlin.collections.MutableList
import kotlin.collections.ArrayList

import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.get

import net.oc_soft.ui.Address


/**
 * bind/unbind japanese street address and user interface
 */
class Ui {

    /**
     * class instance
     */
    companion object {
        /**
         * elements contained address data input
         */
        val addressHolderElements: Array<Element>
            get() {
                val elements = document.querySelectorAll(".jp-address-holder") 
                return Array<Element>(elements.length) {
                    elements[it] as Element
                }
            }
    }

    /**
     * address ui elements to assists inputing japanese address.
     */
    val addressElements: MutableList<Address> = ArrayList<Address>()


    /**
     * bind this object into html elements
     */
    fun bind() {

        addressHolderElements.forEach {
            if (it is HTMLElement) {
                val address = Address()
                address.bind(it)
                addressElements.add(address) 
            }
        }

        if (addressElements.size > 0) {
            startLoadAddress()
        }
    }

    /**
     * detach this object from html elements
     */
    fun unbind() {
        addressElements.forEach { it.unbind() }
        addressElements.clear()
    }


    /**
     * start to load address
     */
    fun startLoadAddress() {
        loadPostalNumber().then {
            val postalNumberMap = it
            addressElements.forEach {
                it.postalNumberPrefCityMap = postalNumberMap
            }
        }

        loadPrefCity().then {
            val prefCityMap = it
            addressElements.forEach {
                it.prefCityMap = prefCityMap
            }
        }
    }


    /**
     * load postal number data
     */
    fun loadPostalNumber(): Promise<Map<String, Array<String>>> {

        val url = Site.requestUrl
        val searchParams = url.searchParams
        searchParams.set("action", "oc-jp-addr-assistant-get-address")
        searchParams.set("kind", "postal-numuber")

        return window.fetch(url).then {
            it.json() as Json
        }.then {
            StreetAddress.jsonToPostalNumber(it)
        }
    }

    /**
     * load prefecture and city mapping data
     */
    fun loadPrefCity(): 
        Promise<Map<String, Map<String, Map<String, Array<String>>>>> {

        val url = Site.requestUrl
        val searchParams = url.searchParams
        searchParams.set("action", "oc-jp-addr-assistant-get-address")
        searchParams.set("kind", "pref-city")
        return window.fetch(url).then {
            it.json() as Json
        }.then {
            StreetAddress.jsonToPrefCity(it)
        }
    }

}


// vi: se ts=4 sw=4 et:

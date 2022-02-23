package net.oc_soft

import kotlin.js.Json
import kotlin.collections.Map

/**
 * manage address by japanese postal number.
 */
class StreetAddress {

    /**
     * class instance
     */
    companion object {
        /**
         * convert json to postal number
         */
        fun jsonToPostalNumber(
            postalJson: Json): Map<String, Array<String>> {

            val result = LinkedHashMap<String, Array<String>>()

            val keys: dynamic = js("Object.keys(postalJson)") 
            for (idx in 0 until (keys.length as Number).toInt()) {
                val key = keys[idx].toString()
                val values: dynamic = postalJson[key] 
                val valuesLength = (values.length as Number).toInt()
                val valueArray = Array<String>(valuesLength) {
                    values[it]
                }
                result[key] = valueArray
            }
            return result
        }


        /**
         * convert json to precture city map
         */
        fun jsonToPrefCity(
            prefCity: Json): 
                Map<String, Map<String, Map<String, Array<String>>>> {
            val result = LinkedHashMap<
                String, Map<String, Map<String, Array<String>>>>()
            val keys0: dynamic = js("Object.keys(prefCity)") 
            for (idx0 in 0 until (keys0.length as Number).toInt()) {
                val map0 = LinkedHashMap<String, Map<String, Array<String>>>()  
                val key0 = keys0[idx0] as String
                val pcMap0: dynamic = prefCity[key0]
                val keys1: dynamic = js("Object.keys(pcMap0)")
                for (idx1 in 0 until (keys1.length as Number).toInt()) {
                    val map1 = LinkedHashMap<String, Array<String>>() 
                    val key1 = keys1[idx1] as String
                    val pcMap1: dynamic = pcMap0[key1]
                    val keys2: dynamic = js("Object.keys(pcMap1)")
                    for (idx2 in 0 until (keys2.length as Number).toInt()) {
                        val key2 = keys2[idx2] as String
                        val pcArray2: dynamic = pcMap1[key2]
                        val keys2Length = (pcArray2.length as Number).toInt()
                        val items = Array<String>(keys2Length) {
                            pcArray2[it] as String
                        } 
                        map1[key2] = items
                    }
                    map0[key1] = map1
                }
                result[key0] = map0
            }
            return result
        }
    }

}
// vi: se ts=4 sw=4 et:  

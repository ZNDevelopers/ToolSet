package com.zndevs.toolset.tools

import org.apache.commons.lang.text.StrLookup
import org.apache.commons.lang.text.StrSubstitutor

/**
 * Tools to help with templating text
 */

object StringTemplate {

    /**
     * Templated strings: "my string ${my variable}"
     *
     * @param text Text to parse with TemplateMap
     * @param map  TemplateMap to parse text with
     * @return Parsed string
     */
    fun parse(text: String, map: TemplateMap): String {
        val strSubstitutor = StrSubstitutor(
                object : StrLookup() {
                    override fun lookup(key: String): String {
                        return map.getItem(key) ?: "[null]"
                    }
                })
        return strSubstitutor.replace(text)
    }

}
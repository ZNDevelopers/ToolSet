package com.zndevs.toolset.tools;

import org.apache.commons.lang.text.StrLookup;
import org.apache.commons.lang.text.StrSubstitutor;

/**
 * Tools to help with templating text
 */

public class StringTemplate {

    /**
     * Templated strings: "my string ${my variable}
     *
     * @param text Text to parse with TemplateMap
     * @param map  TemplateMap to parse text with
     * @return Parsed string
     */
    public static String parse(String text, final TemplateMap map) {
        StrSubstitutor strSubstitutor = new StrSubstitutor(
                new StrLookup() {
                    @Override
                    public String lookup(final String key) {
                        return map.getItem(key);
                    }
                });
        return strSubstitutor.replace(text);
    }

}
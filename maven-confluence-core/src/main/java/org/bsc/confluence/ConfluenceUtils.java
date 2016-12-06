package org.bsc.confluence;

import java.net.MalformedURLException;

/**
 *
 * @author Sorrentino
 *
 */
public class ConfluenceUtils {

    /**
     *
     * @param value
     * @return
     */
    public static String decode(String value) {
        if (null == value) {
            return null;
        }
        final String result = value
                .replaceAll("([{}\\[\\]\\+\\*_])", "\\\\$1") // escape every char that would mean something for confluence
                .replaceAll("(?i)</?pre>", "{noformat}")
                .replaceAll("(?i)<code>", "{{")
                .replaceAll("(?i)</code>", "}}")
                .replaceAll("(?i)</?(b|strong)>", "*")
                .replaceAll("(?i)<br/?>", "\\\\")
                .replaceAll("(?i)<hr/?>", "----")
                .replaceAll("(?i)</?p>", "\n")
                .replaceAll("(?i)</?u>", "+")
                .replaceAll("(?i)</?(s|del)>", "-")
                .replaceAll("(?i)</?(i|em)>", "_");
        return ConfluenceHtmlListUtils.replaceHtmlList(result);
    }

    /**
     *
     * @param value
     * @return
     */
    public static String encodeAnchor(String value) {

        if (null == value) {
            return null;
        }

        String v = decode(value);

        try {
            new java.net.URL(v);

            return v;

        } catch (MalformedURLException e) {

            return v.replace(':', '_');

        }

    }

    /**
     *
     * @return ads banner
     */
    public static String getBannerWiki() {

        final StringBuilder wiki = new StringBuilder()
                .append("{info:title=").append("Generated page").append('}')
                .append("this page has been generated by plugin: ")
                .append("[org.bsc.maven:maven-confluence-reporting-plugin|https://github.com/bsorrentino/maven-confluence-plugin]")
                .append("{info}")
                .append('\n');

        return wiki.toString();
    }

}
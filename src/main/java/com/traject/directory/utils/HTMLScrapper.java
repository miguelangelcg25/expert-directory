/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.utils;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author miguelangel
 */
public class HTMLScrapper {

    private final String url;
    private Document doc;

    public HTMLScrapper(String url) {
        this.url = url;
    }

    public void connect() throws IOException {
        doc = Jsoup.connect(url).get();
    }

    public Set<String> select(String query) {
        if (doc == null) {
            throw new IllegalStateException("Document is empty, connect first");
        }

        Elements elements = doc.select(query);
        return elements.stream().map(e -> e.text()).collect(Collectors.toSet());
    }
}

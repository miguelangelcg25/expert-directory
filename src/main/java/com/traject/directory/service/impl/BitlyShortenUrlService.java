/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traject.directory.service.impl;

import com.traject.directory.service.ShortenUrl;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author miguelangel
 */
@Service
@Scope("singleton")
public class BitlyShortenUrlService implements ShortenUrl {

    @Value("${bitly.url}")
    private String bitlyUrl;

    @Value("${bitly.token}")
    private String token;

    private static final String LONG_URL = "long_url";
    private static final String SHORT_URL = "link";
    private static final String AUTHORIZATION = "Authorization";
    private static final HttpHeaders HEADERS = new HttpHeaders();

    @PostConstruct
    private void init() {
        HEADERS.add(AUTHORIZATION, "Bearer " + token);
    }

    @Override
    public String shortenUrl(String url) {
        RestTemplate client = new RestTemplate();
        Map<String, String> payload = new HashMap<>();
        payload.put(LONG_URL, url);
        HttpEntity entity = new HttpEntity(payload, HEADERS);
        ResponseEntity<Map> response = client.postForEntity(bitlyUrl, entity, Map.class);
        return (String) response.getBody().get(SHORT_URL);
    }

}

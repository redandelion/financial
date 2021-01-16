package com.seeha.stock.basic.components;

import com.seeha.stock.basic.config.HttpConvertConfig;
import com.seeha.stock.basic.dto.ResultDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpClientComponent {

    public String httpClient(String url, HttpMethod method, MultiValueMap<String,String> params){

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        headers.add("Content-Type","text/plain;charset=utf-8");
        ResponseEntity<String> response = template.exchange(url, method, requestEntity, String.class);

        System.out.println(response.getBody());
        return response.getBody();
    }

}

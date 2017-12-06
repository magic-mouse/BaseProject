package com.github.dronezcc.riser.gui.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ReCaptchaService {

    @Value("${captcha.path}")
    private String captchaUrl;
    @Value("${captcha.secret}")
    private String captchaSecret;

    public boolean validateService(String stringToValidate){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", captchaSecret);
        map.add("response", stringToValidate);

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<Map> quote = restTemplate.postForEntity(captchaUrl,request, Map.class);

        return quote.getBody().get("success").toString().equals("true");
    }


}

package com.crud.tasks.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


public class InfoConfig implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String,String> owner = new HashMap<>();
        owner.put("name","Mateusz");
        owner.put("surname","Olszak");
        builder.withDetail("owner",owner);
    }
}

package com.apap.tutorial7.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class Setting {
    final public static String pilotUrl = "https://608c7a4f-3b40-4564-97fe-85fe8897b9bc.mock.pstmn.io";
    final public static String airPortUrl = "https://api.sandbox.amadeus.com/v1.2/airports/autocomplete?apikey=Td1U1be6GHXuJ14pAqJ2tf5AVlVTSdUZ&";

    @Autowired
    public static RestTemplate restTemplate;

    @Bean
    public RestTemplate rest(){
        return new RestTemplate();
    }
}

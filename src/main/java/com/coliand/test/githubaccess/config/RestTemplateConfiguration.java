package com.coliand.test.githubaccess.config;

import java.util.Arrays;
import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    
 
    @Autowired
    private ObjectMapper objectMapper;


    /**
     *
     * @return a {@link RestTemplate} with a HAL converter
     */
    @Bean
    public RestTemplate restTemplate() {

        // converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converter.setObjectMapper(objectMapper);

        RestTemplate restTemplate = new RestTemplate(Collections.singletonList(converter));

        return restTemplate;
    }
}

package com.eafit.middleware.shared.client;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eafit.middleware.shared.dtos.request.Seniority;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@Component
public class RestClient {
    protected String URL = "";
    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public RestClient() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <O> O GET(String path, Class<O> responseType) {
        return getData(path, null, HttpMethod.GET, responseType);
    }

    public <O, K, V> O GET(String path, Class<O> responseType, Class<K> keyType, Class<V> valueType) {
        return getData(path, null, HttpMethod.GET, responseType, keyType, valueType);
    }

    public <I, O> O POST(String path, I body, HttpHeaders headers, Class<O> responseType) {
        HttpEntity<I> request = new HttpEntity<I>(body, headers);

        return getData(path, request, HttpMethod.POST, responseType);
    }

    private String getEndpoint(String path) {
        return String.format("%s/%s", getBaseEndpoint(), path);
    }

    private boolean isAccepted(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    private <O> O getData(String path, HttpEntity request, HttpMethod method, Class<O> responseType) {
        return getData(path, request, method, responseType, null, null);
    }

    private <O, K, V> O getData(String path, HttpEntity request, HttpMethod method, Class<O> responseType,
            Class<K> keyType, Class<V> valueType) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(getEndpoint(path), method, request, String.class);
        Optional<String> response = Optional.ofNullable(
            isAccepted(responseEntity.getStatusCode().value()) ? responseEntity.getBody() : null);
            
            if (response.isPresent()) {
                try {
                    if (Map.class.isAssignableFrom(responseType)) {
                        JavaType mapType = TypeFactory.defaultInstance().constructMapType(Map.class, keyType, valueType);
                        
                        return responseType.cast(
                            objectMapper.readValue(response.get(), mapType));
                        }
                        
                        return objectMapper.readValue(response.get(), responseType);
            } catch (Exception e) {
                System.out.println("=================> " + e.getMessage());
             }
        }

        return null;
    }

    protected String getBaseEndpoint() {
        return this.URL;
    }
}

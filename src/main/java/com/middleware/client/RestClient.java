package com.middleware.client;

import java.net.URI;

import javax.inject.Singleton;
import javax.inject.Inject;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.rxjava3.http.client.Rx3HttpClient;

@Singleton
public abstract class RestClient {
    @Inject
    public Rx3HttpClient httpClient;

    protected String API_URI;

    public RestClient() {}

    public RestClient(String URI) {
        this.API_URI = URI;
    }
    
    public <O> O GET(String path, Class<O> clazz) {
        HttpResponse response = httpClient.exchange(getApiEndpoint(path), clazz).blockingFirst();

        return clazz.cast(response.body());
    }

    public <I, O> O POST(String path, I body, Class<O> clazz) {
        HttpRequest request = HttpRequest.POST(getApiEndpoint(path), body);
        HttpResponse<O> response = (HttpResponse) httpClient.exchange(request, clazz).blockingFirst();

        return clazz.cast(response.body());
    }

    protected String getApiEndpoint(String path) {
        return String.format("%s/%s", API_URI, path);
    }

}

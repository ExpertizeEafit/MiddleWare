package com.middleware.config;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import io.micronaut.core.io.ResourceResolver;
import io.micronaut.core.io.scan.ClassPathResourceLoader;

public class SystemConfiguration {

    private static final String PATH = "C:/Users/JDPRIETO/University/PI 2/Middleware Prueba/middleware/mocks/";

    public static String getEnv(String variableName) {
        return System.getenv(variableName);
    }
    
    public static <Input> Input loadJson(String path, Class<Input> clazz) {
        ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
        Optional<URL> resource = loader.getResource("classpath:mocks/" + path);

        if (resource.isPresent()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(resource.get(), clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static <Input> List<Input> loadJsonToList(String path, Class<Input> clazz) {
        ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
        Optional<URL> resource = loader.getResource("classpath:mocks/" + path);

        if (resource.isPresent()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(resource.get(), mapper.getTypeFactory().constructCollectionType(List.class, clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static <Key, Value> HashMap<Key, Value> loadJsonToMap(String path, Class<Key> key, Class<Value> value) {
        ClassPathResourceLoader loader = new ResourceResolver().getLoader(ClassPathResourceLoader.class).get();
        Optional<URL> resource = loader.getResource("classpath:mocks/" + path);

        if (resource.isPresent()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                TypeFactory typeFactory = mapper.getTypeFactory();
                MapType mapType = typeFactory.constructMapType(HashMap.class, key, value);
                mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
                return mapper.readValue(resource.get(), mapType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

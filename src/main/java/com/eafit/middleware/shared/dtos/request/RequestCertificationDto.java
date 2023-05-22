package com.eafit.middleware.shared.dtos.request;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RequestCertificationDto {
    public MultipartFile file;
    public String user_id;
    public String requirement_id;

    public MultiValueMap<String, Object> getMultiValue() {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();

        List<Field> fields = Arrays.asList(this.getClass().getDeclaredFields());

        for(Field field: fields) {
            if (field.canAccess(this)) {
                try {
                    if (!(field.get(this) instanceof MultipartFile)) {
                        multiValueMap.add(field.getName(), field.get(this));
                    } else {
                        multiValueMap.add(field.getName(), getFileFromMultipart((MultipartFile) field.get(this)));
                    }
                } catch (Exception e) { }
            }
        }

        return multiValueMap;
    }

    private Resource getFileFromMultipart(MultipartFile file) throws IOException {
        ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                Random random = new Random();
                int number = random.nextInt(10000);
                
                return String.format("file_%04d.pdf",number);
            }
        };

        return resource;
    }

}

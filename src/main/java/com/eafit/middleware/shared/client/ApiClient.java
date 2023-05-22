package com.eafit.middleware.shared.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.eafit.middleware.shared.dtos.NewPasswordDto;
import com.eafit.middleware.shared.dtos.NewUserDto;
import com.eafit.middleware.shared.dtos.UserAuthenticated;
import com.eafit.middleware.shared.dtos.UserCredentials;
import com.eafit.middleware.shared.dtos.UserRegisteredDto;
import com.eafit.middleware.shared.dtos.request.RequestCertificationDto;
import com.eafit.middleware.shared.dtos.request.Seniority;
import com.eafit.middleware.shared.dtos.response.RankingResponse;
import com.eafit.middleware.shared.dtos.response.RequirementRequestDto;

@Component
public class ApiClient extends RestClient {
    private static String apiEndpoint = "http://localhost:8080/expertize/v1/writer";

    public ApiClient() {
        super(apiEndpoint);
    }

    public Map<String, Seniority> getLearningPath() {
        Map<String, Seniority> response = new HashMap<>();
        response = GET("getPaths", response.getClass(), String.class, Seniority.class);

        return response;
    }

    public Map<String, Seniority> getLearningPathByUser(String userId) {
        Map<String, Seniority> response = new HashMap<>();
        response = GET(String.format("getCurrentAndNextSeniority/%s", userId), response.getClass(), String.class, Seniority.class);

        return response;
    }

    public UserAuthenticated login(UserCredentials userCredentials) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserAuthenticated user = POST("login", userCredentials, headers, UserAuthenticated.class);

        return user;
    }

    public List<UserRegisteredDto> register(List<NewUserDto> newUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<UserRegisteredDto> users = new ArrayList<>();
        users = POST("register", newUser, headers, users.getClass());

        return users;
    }

    public List<RequirementRequestDto> getAllRequests(String userId) {

        List<RequirementRequestDto> requests = new ArrayList<>();
        requests = GET(String.format("getRequirementsHistory/%s", userId), requests.getClass());

        return requests;
    }

    public String uploadRequirement(RequestCertificationDto certification) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = certification.getMultiValue();

        String response = POST("upload", body, headers, String.class);

        return response;
    }

    public String updateRequirement(RequirementRequestDto certification) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String response = POST("UpdateStatus", certification, headers, String.class);

        return response;
    }

    public String changePassword(NewPasswordDto newPassword) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String response = POST("updatePassword", newPassword, headers, String.class);

        return response;
    }

    public RankingResponse getRankingByUser(String userId) {
        RankingResponse response= GET(String.format("ranking/%s", userId), RankingResponse.class);

        return response;
    }
}

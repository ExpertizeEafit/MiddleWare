package com.eafit.middleware.shared.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
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
import com.eafit.middleware.shared.dtos.response.PendingRequirementRequestDto;
import com.eafit.middleware.shared.dtos.response.RankingResponse;
import com.eafit.middleware.shared.dtos.response.RequirementRequestDto;
import com.eafit.middleware.shared.exceptions.RegisterUsersException;
import com.eafit.middleware.shared.exceptions.UnexpectedInternalException;
import com.eafit.middleware.shared.exceptions.UserNotFoundException;

@Component
public class ApiClient extends RestClient {
    @Value("${api.endpoint}")
    private String apiEndpoint;

    public ApiClient() { }

    @Override
    public String getBaseEndpoint() {
        return this.apiEndpoint;
    }

    public Map<String, Seniority> getLearningPath() {
        Map<String, Seniority> response = new HashMap<>();
        response = GET("getPaths").validateData(() -> {
            throw new UnexpectedInternalException();
        }).getData(response.getClass(), String.class,
                Seniority.class);

        return response;
    }

    public Map<String, Seniority> getLearningPathByUser(String userId) {
        Map<String, Seniority> response = new HashMap<>();
        response = GET(String.format("getCurrentAndNextSeniority/%s", userId)).validateData(() -> {
            throw new UnexpectedInternalException();
        }).getData(response.getClass(), String.class,
                Seniority.class);

        return response;
    }

    public UserAuthenticated login(UserCredentials userCredentials) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserAuthenticated user = POST("login", userCredentials, headers).validateData(() -> {
            throw new UserNotFoundException("User not found. Verify your credentials.");
        }).getData(UserAuthenticated.class);

        return user;
    }

    public List<UserRegisteredDto> register(List<NewUserDto> newUser) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<UserRegisteredDto> users = new ArrayList<>();
        users = POST("register", newUser, headers).validateData(() -> {
            throw new RegisterUsersException("Failed to register users. Unexpected error.");
        }).getData(users.getClass());

        return users;
    }

    public List<RequirementRequestDto> getAllRequests(String userId) {

        List<RequirementRequestDto> requests = new ArrayList<>();
        requests = GET(String.format("getRequirementsHistory/%s", userId)).validateData(() -> {
            throw new UnexpectedInternalException();
        }).getData(requests.getClass());

        return requests;
    }

    public String uploadRequirement(RequestCertificationDto certification) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = certification.getMultiValue();

        String response = POST("upload", body, headers).validateData(() -> {
            throw new UnexpectedInternalException();
        }).getData(String.class);

        return response;
    }

    public String updateRequirement(RequirementRequestDto certification) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String response = POST("UpdateStatus", certification, headers).validateData(() -> {
            throw new UnexpectedInternalException();
        }).getData(String.class);

        return response;
    }

    public String changePassword(NewPasswordDto newPassword) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String response = POST("updatePassword", newPassword, headers).validateData(() -> {
            throw new UnexpectedInternalException();
        }).getData(String.class);

        return response;
    }

    public RankingResponse getRankingByUser(String userId) {
        RankingResponse response = GET(String.format("ranking/%s", userId)).validateData(() -> {
            throw new UnexpectedInternalException();
        }).getData(RankingResponse.class);
        return response;
    }

    public List<PendingRequirementRequestDto> getAllPendingRequests() {
        List<PendingRequirementRequestDto> requests = new ArrayList<>();
        requests = GET("PendingRequirements").validateData(() -> {
            throw new UnexpectedInternalException();
        }).getData(requests.getClass());

        return requests;
    }
}
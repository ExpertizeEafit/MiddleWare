package com.eafit.middleware.shared.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eafit.middleware.shared.dtos.response.RankingResponse;
import com.eafit.middleware.shared.services.RankingService;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;
    
    @GetMapping("/{userId}")
    public RankingResponse getRanking(@PathVariable String userId) {
        return rankingService.getRanking(userId);
    }
}

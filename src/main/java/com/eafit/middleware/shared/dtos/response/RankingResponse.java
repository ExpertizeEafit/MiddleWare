package com.eafit.middleware.shared.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RankingResponse {
    public List<UserRanked> ranking;
    public UserRanked myrank;

    public RankingResponse() { }

    public static class UserRanked {
        public Integer rank;
        public String username;
        public Integer points;

        public UserRanked() {}
    }
}

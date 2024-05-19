package com.example.matchingresult.service;


import com.example.matchingresult.dto.MatchingResultDTO;

public interface MatchingService {
    MatchingResultDTO getMatchingResult(String mobileId);
}

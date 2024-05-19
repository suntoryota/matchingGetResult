package com.example.matchingresult.controller;

import com.example.matchingresult.dto.MatchingResultDTO;
import com.example.matchingresult.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/matching")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @GetMapping("/{mobileId}")
    public ResponseEntity<MatchingResultDTO> getMatchingResult(@PathVariable String mobileId) {
        MatchingResultDTO result = matchingService.getMatchingResult(mobileId);
        return ResponseEntity.ok(result);
    }
}


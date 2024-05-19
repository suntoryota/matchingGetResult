package com.example.matchingresult.util;

import com.example.matchingresult.dto.MatchingData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<MatchingData> parseMatchingData(String jsonData) {
        try {
            return objectMapper.readValue(jsonData, new TypeReference<List<MatchingData>>(){});
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON data", e);
        }
    }
}

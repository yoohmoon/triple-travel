package com.triple.travelLog.controller;

import com.triple.travelLog.dto.ApiResponse;
import com.triple.travelLog.dto.GooglePlaceResponse;
import com.triple.travelLog.service.GooglePlacesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/google-places")
public class GooglePlacesController {
    private final GooglePlacesService googlePlacesService;

    public GooglePlacesController(GooglePlacesService googlePlacesService) {
        this.googlePlacesService = googlePlacesService;
    }

    @GetMapping("/search")
    public ApiResponse<Map> search(@RequestParam String query){
        return ApiResponse.success(googlePlacesService.search(query));
    }

    @GetMapping("/search/places")
    public ApiResponse<List<GooglePlaceResponse>> searchPlaces(@RequestParam String query){
        return ApiResponse.success(googlePlacesService.searchPlaces(query));
    }
}

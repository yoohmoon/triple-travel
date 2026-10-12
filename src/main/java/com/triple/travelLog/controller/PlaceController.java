package com.triple.travelLog.controller;

import com.triple.travelLog.dto.ApiResponse;
import com.triple.travelLog.dto.PlaceCreateRequest;
import com.triple.travelLog.entity.Place;
import com.triple.travelLog.service.PlaceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/trip/{tripId}")
    public ApiResponse<Page<Place>> getPlacesByTrip(@PathVariable Long tripId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ApiResponse.success(placeService.findByTripId(tripId,pageable));
    }

    @GetMapping("/trip/{tripId}/category/{category}")
    public ApiResponse<List<Place>> getPlacesByTripAndCategory(@PathVariable Long tripId, @PathVariable String category){
        return ApiResponse.success(placeService.findByTripIdAndCategory(tripId, category));
    }

    @PostMapping
    public ApiResponse<Place> createPlace(@RequestBody PlaceCreateRequest request){
        return ApiResponse.success(placeService.save(request.getName(), request.getDayNumber(), request.getTripId(), request.getCategory(),
                request.getAddress(), request.getRating(), request.getLat(), request.getLng()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePlace(@PathVariable Long id){

        placeService.deleteById(id);
        return ApiResponse.success(null);
    }
}

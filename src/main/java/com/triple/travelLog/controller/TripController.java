package com.triple.travelLog.controller;

import com.triple.travelLog.dto.ApiResponse;
import com.triple.travelLog.dto.TripCreateRequest;
import com.triple.travelLog.dto.TripUpdateRequest;
import com.triple.travelLog.entity.Trip;
import com.triple.travelLog.service.TripService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/{id}")
    public ApiResponse<Trip> getTrip(@PathVariable Long id){
        return ApiResponse.success(tripService.findById(id));
    }

    @GetMapping
    public ApiResponse<Page<Trip>> getMyTrips(@AuthenticationPrincipal Long memberId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue ="10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ApiResponse.success(tripService.findByMemberId(memberId, pageable));
    }

    @PostMapping
    public ApiResponse<Trip> createTrip(@Valid @RequestBody TripCreateRequest request, @AuthenticationPrincipal Long memberId){
        return ApiResponse.success(tripService.save(request.getTitle(), request.getCityId(), request.getStartDate(), request.getEndDate(), memberId));
    }

    @PutMapping("/{id}")
    public ApiResponse<Trip> updateTrip(@PathVariable Long id, @Valid @RequestBody TripUpdateRequest request,@AuthenticationPrincipal Long memberId){
        return ApiResponse.success(tripService.update(id, request.getTitle(), request.getStartDate(), request.getEndDate(), memberId));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteTrip(@PathVariable Long id){

        tripService.deleteById(id);
        return ApiResponse.success(null);
    }
}

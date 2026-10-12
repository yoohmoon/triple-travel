package com.triple.travelLog.controller;

import com.triple.travelLog.dto.ApiResponse;
import com.triple.travelLog.dto.CityCreateRequest;
import com.triple.travelLog.entity.City;
import com.triple.travelLog.service.CityService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ApiResponse<Page<City>> getAllCities(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return ApiResponse.success(cityService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ApiResponse<City> getCity(@PathVariable Long id){
        return ApiResponse.success(cityService.findById(id));
    }

    @PostMapping
    public ApiResponse<City> createCity(@Valid @RequestBody CityCreateRequest request){
        return ApiResponse.success(cityService.save(request.getName(), request.getCountry()));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCity(@PathVariable Long id){
        cityService.deleteById(id);
        return ApiResponse.success(null);
    }
}

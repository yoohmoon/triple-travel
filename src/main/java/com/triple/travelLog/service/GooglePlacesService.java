package com.triple.travelLog.service;

import com.triple.travelLog.dto.GooglePlaceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class GooglePlacesService {

    private final RestTemplate restTemplate;

    @Value("${google.places.api-key}")
    private String apiKey;

    public GooglePlacesService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map search(String query){
        String url = "https://maps.googleapis.com/maps/api/place/textsearch/json"
                + "?query=" + query
                + "&language=ko"
                + "&key=" + apiKey;

        return restTemplate.getForObject(url, Map.class);
    }

    public List<GooglePlaceResponse> searchPlaces(String query){
        Map response = search(query);
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

        List<GooglePlaceResponse> places = new ArrayList<>();

        if(results != null){
            for (Map<String, Object> result : results){
                String name = (String) result.get("name");
                String address = (String) result.get("formatted_address");

                double rating = 0.0;
                if(result.get("rating")!=null){
                    rating = ((Number) result.get("rating")).doubleValue();
                }

                Map<String, Object> geometry = (Map<String, Object>) result.get("geometry");
                Map<String, Object> location = (Map<String, Object>) geometry.get("location");
                double lat = ((Number) location.get("lat")).doubleValue();
                double lng = ((Number) location.get("lng")).doubleValue();

                places.add(new GooglePlaceResponse(name, address, rating, lat, lng));
            }
        }

        return places;
    }
}

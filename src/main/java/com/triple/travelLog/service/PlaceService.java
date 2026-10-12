package com.triple.travelLog.service;

import com.triple.travelLog.entity.Place;
import com.triple.travelLog.entity.PlaceCategory;
import com.triple.travelLog.entity.Trip;
import com.triple.travelLog.exception.ResourceNotFoundException;
import com.triple.travelLog.repository.PlaceRepository;
import com.triple.travelLog.repository.TripRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final TripRepository tripRepository;

    public PlaceService(PlaceRepository placeRepository, TripRepository tripRepository) {
        this.placeRepository = placeRepository;
        this.tripRepository = tripRepository;
    }

    public Page<Place> findByTripId(Long tripId, Pageable pageable){
        return placeRepository.findByTripId(tripId, pageable);
    }

    public List<Place> findByTripIdAndCategory(Long tripId, String category){
        PlaceCategory placeCategory = PlaceCategory.valueOf(category);
        return placeRepository.findByTripIdAndCategory(tripId, placeCategory);
    }

    public Place save(String name, int dayNumber, Long tripId, String category,
                      String address, double rating, double lat, double lng){
        Optional<Trip> optional = tripRepository.findById(tripId);

        if(optional.isPresent()){
            Trip trip = optional.get();
            PlaceCategory placeCategory = PlaceCategory.valueOf(category);
            Place place = new Place(name, dayNumber, trip, placeCategory, address, rating, lat, lng);
            return placeRepository.save(place);
        }else {
            throw new ResourceNotFoundException("Trip not found");
        }
    }

    public void deleteById(Long id){
        placeRepository.deleteById(id);
    }
}

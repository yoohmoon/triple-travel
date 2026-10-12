package com.triple.travelLog.repository;

import com.triple.travelLog.entity.Place;
import com.triple.travelLog.entity.PlaceCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    @EntityGraph(attributePaths = {"trip"})
    Page<Place> findByTripId(Long tripId, Pageable pageable);

    @EntityGraph(attributePaths = {"trip"})
    List<Place> findByTripIdAndCategory(Long tripId, PlaceCategory category);
}

package com.triple.travelLog.repository;

import com.triple.travelLog.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip,Long> {

    @EntityGraph(attributePaths = {"city", "member"})
    Page<Trip> findByMemberId(Long memberId, Pageable pageable);
}

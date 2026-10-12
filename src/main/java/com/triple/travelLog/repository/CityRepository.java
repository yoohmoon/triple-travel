package com.triple.travelLog.repository;

import com.triple.travelLog.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}

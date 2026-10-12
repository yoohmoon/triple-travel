package com.triple.travelLog.service;

import com.triple.travelLog.entity.City;
import com.triple.travelLog.exception.ResourceNotFoundException;
import com.triple.travelLog.repository.CityRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Page<City> findAll(Pageable pageable){
        return cityRepository.findAll(pageable);
    }

    @Cacheable(value = "city", key = "#id")
    public City findById(Long id){
        Optional<City> optional = cityRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new ResourceNotFoundException("City Not Found");
        }
    }

    public City save(String name, String country){
        City city = new City(name, country);
        return cityRepository.save(city);
    }

    @CacheEvict(value = "city", key = "#id")
    public void deleteById(Long id){
        cityRepository.deleteById(id);
    }
}

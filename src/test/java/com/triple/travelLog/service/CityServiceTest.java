package com.triple.travelLog.service;

import com.triple.travelLog.entity.City;
import com.triple.travelLog.exception.ResourceNotFoundException;
import com.triple.travelLog.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    void findById_존재하는도시_정상리턴(){
        City city = new City("서울", "한국");
        Mockito.when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        City result = cityService.findById(1L);

        Assertions.assertEquals("서울", result.getName());
        Assertions.assertEquals("한국", result.getCountry());

    }

    @Test
    void findById_존재하지않는도시_예외발생(){
        Mockito.when(cityRepository.findById(999L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            cityService.findById(999L);
        });
    }
}

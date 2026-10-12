package com.triple.travelLog.service;

import com.triple.travelLog.entity.City;
import com.triple.travelLog.entity.Member;
import com.triple.travelLog.entity.Trip;
import com.triple.travelLog.exception.ResourceNotFoundException;
import com.triple.travelLog.repository.CityRepository;
import com.triple.travelLog.repository.MemberRepository;
import com.triple.travelLog.repository.TripRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final CityRepository cityRepository;
    private final MemberRepository memberRepository;

    public TripService(TripRepository tripRepository, CityRepository cityRepository, MemberRepository memberRepository) {
        this.tripRepository = tripRepository;
        this.cityRepository = cityRepository;
        this.memberRepository = memberRepository;
    }

    public List<Trip> findAll(){
        return tripRepository.findAll();
    }

    public Trip findById(Long id){
        Optional<Trip> optional = tripRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("Trip not found");
        }
    }

    public Page<Trip> findByMemberId(Long memberId, Pageable pageable){
        return tripRepository.findByMemberId(memberId, pageable);
    }

    public Trip save(String title, Long cityId, String startDate, String endDate, Long memberId){
        Optional<City> optional = cityRepository.findById(cityId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if(optional.isPresent()){
            City city = optional.get();
            Member member = memberOptional.get();
            Trip trip = new Trip(title, city, LocalDate.parse(startDate), LocalDate.parse(endDate), member);
            return tripRepository.save(trip);
        }else{
            throw new ResourceNotFoundException("City not found");
        }
    }

    public Trip update(Long id, String title, String startDate, String endDate, Long memberId){
        Optional<Trip> optional = tripRepository.findById(id);

        if(optional.isPresent()){
            Trip trip = optional.get();

            if(!trip.getMember().getId().equals(memberId)){
                throw new RuntimeException("본인의 여행만 수정할 수 있습니다");
            }

            trip.update(title, LocalDate.parse(startDate), LocalDate.parse(endDate));
            return tripRepository.save(trip);
        }else{
            throw new ResourceNotFoundException("Trip not found");
        }

    }

    public void deleteById(Long id){
        tripRepository.deleteById(id);
    }


}

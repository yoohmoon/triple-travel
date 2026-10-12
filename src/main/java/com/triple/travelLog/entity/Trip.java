package com.triple.travelLog.entity;

import com.triple.travelLog.service.MemberService;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    protected Trip(){
    }

    public Trip(String title, City city, LocalDate startDate, LocalDate endDate, Member member) {
        this.title = title;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public City getCity() {
        return city;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Member getMember() {
        return member;
    }

    public void update(String title, LocalDate startDate, LocalDate endDate){
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

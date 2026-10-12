package com.triple.travelLog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlaceCreateRequest {

    @NotBlank(message = "장소 이름은 필수입니다")
    private String name;

    @NotNull(message = "일자 정보는 필수입니다")
    private Integer dayNumber;

    @NotNull(message = "여행 선택은 필수입니다")
    private Long tripId;

    @NotBlank(message = "카테고리는 필수입니다")
    private String category;

    private String address;
    private double rating;
    private double lat;
    private double lng;

    public String getName() {
        return name;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public Long getTripId() {
        return tripId;
    }

    public String getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}

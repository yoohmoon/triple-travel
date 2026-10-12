package com.triple.travelLog.dto;

import jakarta.validation.constraints.NotBlank;

public class CityCreateRequest {

    @NotBlank(message = "도시 이름은 필수입니다")
    private String name;

    @NotBlank(message = "국가는 필수입니다")
    private String country;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}

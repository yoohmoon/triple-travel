package com.triple.travelLog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TripCreateRequest {

    @NotBlank(message = "여행 제목은 필수입니다")
    private String title;

    @NotNull(message = "도시 선택은 필수입니다")
    private Long cityId;

    @NotBlank(message = "시작 날짜는 필수입니다")
    private String startDate;

    @NotBlank(message = "종료 날짜는 필수입니다")
    private String endDate;

    public String getTitle() {
        return title;
    }

    public Long getCityId() {
        return cityId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}

package com.triple.travelLog.dto;

import jakarta.validation.constraints.NotBlank;

public class TripUpdateRequest {
    @NotBlank(message = "여행 제목은 필수입니다")
    private String title;

    @NotBlank(message = "시작 날짜는 필수입니다")
    private String startDate;

    @NotBlank(message = "종료 날짜는 필수입니다")
    private String endDate;

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}

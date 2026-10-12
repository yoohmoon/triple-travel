package com.triple.travelLog.dto;

public class ApiResponse <T>{

    private String status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> success(T data){
        ApiResponse<T> response = new ApiResponse<>();
        response.status = "success";
        response.data = data;
        return response;
    }

    public static <T> ApiResponse<T> error(T data){
        ApiResponse<T> response = new ApiResponse<>();
        response.status = "error";
        response.data = data;
        return response;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}

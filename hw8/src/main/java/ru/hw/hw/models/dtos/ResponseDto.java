package ru.hw.hw.models.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseDto {
    private int status;
    private String message;
    private Date timestamp;

    public ResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}

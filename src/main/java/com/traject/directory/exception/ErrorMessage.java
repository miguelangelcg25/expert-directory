package com.traject.directory.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    private String message;
    private String path;
    private int status;
    private Date timestamp;

}

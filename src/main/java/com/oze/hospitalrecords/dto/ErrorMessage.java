package com.oze.hospitalrecords.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage
{
    private String responseCode;
    private String responseDescription;
}

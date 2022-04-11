package com.oze.hospitalrecords.dto;

import lombok.Data;

import java.util.List;
@Data
public class PatientFinalResponse
{
    long count;
    List<PatientResponse> data;
}

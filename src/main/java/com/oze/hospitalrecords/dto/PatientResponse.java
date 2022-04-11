package com.oze.hospitalrecords.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse
{
    private int id;
    private String name;
    private int age;
    private Date lastVisitDate;

}

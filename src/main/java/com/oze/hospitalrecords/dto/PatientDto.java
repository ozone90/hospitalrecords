package com.oze.hospitalrecords.dto;

import com.oze.hospitalrecords.model.Patient;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class PatientDto
{
    @NotBlank(message ="Name cannot be empty")
    private String name;

    private int age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastVisitDate;

    public Patient toPatient()
    {
        Patient patient =  new Patient();
        patient.setAge(this.age);
        patient.setName(this.name);
        patient.setLastVisitDate(this.lastVisitDate);
        return patient;
    }
}

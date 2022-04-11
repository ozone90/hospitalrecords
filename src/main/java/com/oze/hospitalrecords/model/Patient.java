package com.oze.hospitalrecords.model;

import com.oze.hospitalrecords.dto.PatientResponse;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name="last_visit_date")
    private Date lastVisitDate;

    public PatientResponse toPatientResponse()
    {
        PatientResponse patientResponse =  new PatientResponse();
        patientResponse.setAge(this.age);
        patientResponse.setId(this.id);
        patientResponse.setName(this.name);
        patientResponse.setLastVisitDate(this.lastVisitDate);
        return patientResponse;
    }

}
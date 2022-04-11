package com.oze.hospitalrecords.model;

import com.oze.hospitalrecords.dto.StaffResponse;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "uuid")
    private String uuid;
    @Column(name="registration_date")
    private Date registrationDate;


    public StaffResponse toStaffResponse()
    {
        StaffResponse staffResponse =  new StaffResponse();
        staffResponse.setName(this.name);
        staffResponse.setId(this.id);
        staffResponse.setRegistrationDate(this.registrationDate);
        staffResponse.setUuid(this.uuid);

        return staffResponse;
    }
}
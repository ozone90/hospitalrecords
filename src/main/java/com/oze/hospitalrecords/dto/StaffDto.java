package com.oze.hospitalrecords.dto;

import com.oze.hospitalrecords.model.Staff;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
public class StaffDto
{
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    public Staff toStaff()
    {
        Staff staff =  new Staff();
        staff.setName(this.name);
        staff.setRegistrationDate(this.registrationDate);
        staff.setUuid(UUID.randomUUID().toString());

        return staff;
    }
}

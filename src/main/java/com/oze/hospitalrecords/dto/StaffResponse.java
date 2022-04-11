package com.oze.hospitalrecords.dto;

import lombok.Data;
import java.util.Date;

@Data
public class StaffResponse
{
    private Integer id;
    private String name;
    private String uuid;
    private Date registrationDate;
}

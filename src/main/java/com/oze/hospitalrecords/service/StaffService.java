package com.oze.hospitalrecords.service;

import com.oze.hospitalrecords.dto.*;
import com.oze.hospitalrecords.exception.ConflictException;
import com.oze.hospitalrecords.model.Staff;
import com.oze.hospitalrecords.repository.StaffRepository;
import com.oze.hospitalrecords.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@Slf4j
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffResponse addStaff(StaffDto staffDto)
    {
        if(findByName(staffDto.getName())!=null)
        {
            throw new ConflictException(Constants.STAFF_ALREADY_EXISTS);
        }

        Staff staff =  staffDto.toStaff();
        staff = staffRepository.save(staff);
        return staff.toStaffResponse();
    }

    public StaffResponse findByName(String name)
    {
        Staff staff =  staffRepository.findByName(name);
        return staff==null?null:staff.toStaffResponse();
    }


    public int updateStaff(int id, StaffDto staffDto)
    {
        Staff staff =  staffDto.toStaff();
       return  staffRepository.updateStaff(id,staff.getName());

    }
}

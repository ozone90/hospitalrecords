package com.oze.hospitalrecords.controller;

import com.oze.hospitalrecords.dto.*;
import com.oze.hospitalrecords.enums.ResponseCodes;
import com.oze.hospitalrecords.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
public class StaffController {

  private final StaffService staffService;

  /**
   * Method to create staff
   * @param staffDto  contains the fields needed to create a staff
   * @return returns a StaffResponse
   */
  @PostMapping
  public ResponseEntity<StaffResponse> createStaff(@Valid @RequestBody StaffDto staffDto) {
    StaffResponse staffResponse = staffService.addStaff(staffDto);
    return new ResponseEntity<>(staffResponse, new HttpHeaders(), HttpStatus.OK);
  }

  /**
   * Method to update staff
   * @param id the id to be updated
   * @param staffDto request details updated
   * @return returns a StaffResponse
   */
  @PutMapping("/{id}")
  public ResponseEntity<GenericResponse> updateStaff(@PathVariable int id, @Valid @RequestBody StaffDto staffDto) {

    staffService.updateStaff(id,staffDto);
    GenericResponse genericResponse =  new GenericResponse();
    genericResponse.setMessage(ResponseCodes.SUCCESSFUL.getDescription());
    genericResponse.setCode(ResponseCodes.SUCCESSFUL.getCode());
    return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
  }

}

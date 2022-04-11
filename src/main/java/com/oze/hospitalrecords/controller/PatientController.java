package com.oze.hospitalrecords.controller;


import com.oze.hospitalrecords.dto.GenericResponse;
import com.oze.hospitalrecords.dto.PatientDto;
import com.oze.hospitalrecords.dto.PatientFinalResponse;
import com.oze.hospitalrecords.dto.PatientResponse;
import com.oze.hospitalrecords.exception.ForbiddenException;
import com.oze.hospitalrecords.exception.NotFoundException;
import com.oze.hospitalrecords.service.CsvExportService;
import com.oze.hospitalrecords.service.PatientService;
import com.oze.hospitalrecords.service.StaffService;
import com.oze.hospitalrecords.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;


@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;
  private final CsvExportService csvExportService;
  private final StaffService staffService;

  /**
   * Method to view all patients
   * @param pageNum pageNum is the page number to display in the result set
   * @param pageSize pageSize is the number of records per page
   * @param sortBy sortBy is the default column to sort by in the database
   * @param age age is used to filter result set by ACTIVE/INACTIVE
   * @return returns a list of Patients
   */
  @GetMapping
  public ResponseEntity<PatientFinalResponse> getAllPatients(@RequestParam(defaultValue = "0") int pageNum,
                                                                       @RequestParam(defaultValue = "10") int pageSize,
                                                                       @RequestParam(defaultValue = "age") String sortBy,
                                                                       @RequestParam int age,
                                                             @RequestHeader("staffUuid") @NotBlank(message = "Staffuuid needed") String uuid
  )
  {
    if(staffService.findByUUID(uuid) == null){throw new ForbiddenException(Constants.INVALID_STAFF_ID);}
    PatientFinalResponse patientFinalResponse = patientService.getAllPatients(pageNum,pageSize,sortBy,age);
    return new ResponseEntity<>(patientFinalResponse, new HttpHeaders(), HttpStatus.OK);
  }

  /**
   * Method to create patient
   * @param patientDto  contains the fields needed to create a patient
   * @return returns a PatientResponse
   */
  @PostMapping
  public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientDto patientDto, @RequestHeader("staffUuid") @NotBlank(message = "Staffuuid needed") String uuid) {
    if(staffService.findByUUID(uuid) == null){throw new ForbiddenException(Constants.INVALID_STAFF_ID);}
    PatientResponse patientResponse = patientService.addPatient(patientDto);
    return new ResponseEntity<>(patientResponse, new HttpHeaders(), HttpStatus.OK);
  }

  /**
   * Method to update patient
   * @param id the id to be updated
   * @param patientDto request details updated
   * @return returns a PatientResponse
   */
  @PutMapping("/{id}")
  public ResponseEntity<PatientResponse> updatePatient(@RequestHeader("staffUuid") @NotBlank(message = "Staffuuid needed") String uuid, @PathVariable int id, @Valid @RequestBody PatientDto patientDto) {
    if(staffService.findByUUID(uuid) == null){throw new ForbiddenException(Constants.INVALID_STAFF_ID);}
    PatientResponse patientResponse = patientService.updatePatient(id,patientDto);
    return new ResponseEntity<>(patientResponse, new HttpHeaders(), HttpStatus.OK);
  }


  /**
   * Method to write to csv
   * @param id the id of the patient

   * @return returns a CSV
   */
  @PutMapping("/writetocsv/{id}")
  public void getAllEmployeesInCsv(HttpServletResponse servletResponse,@RequestHeader("staffUuid") @NotBlank(message = "Staffuuid needed") String uuid, @PathVariable  int id) throws IOException {
    if(staffService.findByUUID(uuid) == null){throw new ForbiddenException(Constants.INVALID_STAFF_ID);}
    if(patientService.findById(id) == null)
    {
      throw new NotFoundException(Constants.PATIENT_NOT_FOUND);
    }

    servletResponse.setContentType("text/csv");
    servletResponse.addHeader("Content-Disposition","attachment; filename=\"patients.csv\"");
    csvExportService.writeEmployeesToCsv(servletResponse.getWriter(),id);
  }

  /**
   * Method to write to csv
   * @param startDate the startDate
   * @param endDate the endDate
   */
  @DeleteMapping("/removePatient/startDate/{startDate}/endDate/{endDate}")
  public ResponseEntity<GenericResponse> removePatients(@RequestHeader("staffUuid") @NotBlank(message = "Staffuuid needed") String uuid, @PathVariable String startDate, @PathVariable String endDate) throws ParseException {
    if(staffService.findByUUID(uuid) == null){throw new ForbiddenException(Constants.INVALID_STAFF_ID);}
    GenericResponse genericResponse = patientService.removePatients(startDate,endDate);
    return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.OK);
  }


}

package com.oze.hospitalrecords.service;

import com.oze.hospitalrecords.dto.GenericResponse;
import com.oze.hospitalrecords.dto.PatientDto;
import com.oze.hospitalrecords.dto.PatientFinalResponse;
import com.oze.hospitalrecords.dto.PatientResponse;
import com.oze.hospitalrecords.enums.ResponseCodes;
import com.oze.hospitalrecords.exception.ConflictException;
import com.oze.hospitalrecords.model.Patient;
import com.oze.hospitalrecords.repository.PatientRepository;
import com.oze.hospitalrecords.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;

  public PatientResponse addPatient(PatientDto patientDto)
  {
    if(findByName(patientDto.getName())!=null)
    {
      throw new ConflictException(Constants.PATIENT_ALREADY_EXISTS);
    }

    Patient patient =  patientDto.toPatient();
    patient = patientRepository.save(patient);
    return patient==null?null:patient.toPatientResponse();
  }

  public PatientResponse findByName(String name)
  {
    Patient patient =  patientRepository.findByName(name);
    return patient==null?null:patient.toPatientResponse();
  }


    public PatientResponse updatePatient(int id, PatientDto patientDto)
    {
      Patient patient =  patientDto.toPatient();
      patientRepository.updatePatient(id,patient.getAge(),patient.getLastVisitDate(),patient.getName());
      return patient.toPatientResponse();
    }

  /**
   * This method is used to find all product codes
   * @param pageNum page num to filter response by
   * @param pageSize size of page returned
   * @param sortBy database column to sort by
   * @param age age of the patient
   * @return return a list of ProductCodeResponse
   */
  public PatientFinalResponse getAllPatients(Integer pageNum, Integer pageSize, String sortBy, int age)
  {


    Page<Patient> pagedResult =  null;
    Pageable paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
    pagedResult = patientRepository.findByAgeGreaterThan(age, paging);

    List<Patient> patientList = pagedResult.getContent();
    List<PatientResponse> patientResponseList = null;
    if(!patientList.isEmpty()) {
      patientResponseList = patientList.stream()
              .map(patient -> new PatientResponse(patient.getId(),patient.getName(),patient.getAge(),patient.getLastVisitDate()))
              .collect(Collectors.toList());
    }

    PatientFinalResponse patientFinalResponse =  new PatientFinalResponse();
    patientFinalResponse.setCount(pagedResult.getTotalElements());
    patientFinalResponse.setData(patientResponseList);

    return patientFinalResponse;
  }

  public Patient findById(int id)
  {
    return patientRepository.findPatientById(id);
  }

  public GenericResponse removePatients(String startDate, String endDate) throws ParseException {
    Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
    Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

    patientRepository.deletePatientsInPeriod(sDate,eDate);

    GenericResponse genericResponse = new GenericResponse();
    genericResponse.setCode(ResponseCodes.SUCCESSFUL.getCode());
    genericResponse.setMessage(ResponseCodes.SUCCESSFUL.getDescription());

    return genericResponse;
  }
}

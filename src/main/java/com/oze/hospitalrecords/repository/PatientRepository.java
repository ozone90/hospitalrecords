package com.oze.hospitalrecords.repository;


import com.oze.hospitalrecords.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.name =?1")
    Patient findByName(String name);

    /**
     * This method is used to update a patient
     * @param id id to be updated
     * @param age age of patient
     * @param lastVisitDate  user that modified record
     * @param name name of patient

     * @return returns 1 if successful, 0 if failed
     */
   @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.age=?2, p.lastVisitDate=?3, p.name=?4 WHERE p.id=?1")
    int updatePatient(int id, int age, Date lastVisitDate, String name);

    Page<Patient> findByAgeGreaterThan(int age, Pageable paging);

    @Query("SELECT p FROM Patient p WHERE p.id =?1")
    Patient findPatientById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Patient p WHERE p.lastVisitDate BETWEEN :startDate AND :endDate")
    public void deletePatientsInPeriod(@Param("startDate") Date timeStamp, @Param("endDate") Date endDate);
}

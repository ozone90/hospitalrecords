package com.oze.hospitalrecords.service;

import com.oze.hospitalrecords.model.Patient;
import com.oze.hospitalrecords.repository.PatientRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;

@Service
@Data
@Slf4j
public class CsvExportService {



    private final PatientRepository patientRepository;

    public CsvExportService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void writeEmployeesToCsv(Writer writer, int id) {

        Patient patient = patientRepository.findPatientById(id);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
          csvPrinter.printRecord(patient.getId(), patient.getName(), patient.getAge(),patient.getLastVisitDate());

        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}


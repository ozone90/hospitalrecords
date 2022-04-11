package com.oze.hospitalrecords.service;

import com.oze.hospitalrecords.model.Patient;
import com.oze.hospitalrecords.model.Staff;
import com.oze.hospitalrecords.repository.PatientRepository;
import com.oze.hospitalrecords.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    StaffRepository staffRepository;



    @Override
    public void run(String... args) throws Exception {
        loadPatientData();
        loadStaffData();
    }

    private void loadPatientData() {
        if (patientRepository.count() == 0) {
            Patient patient1 = new Patient(1,"John Doe",2,new Date());
            Patient patient2 = new Patient(2,"James Doe",2,new Date());
            Patient patient3 = new Patient(3,"Sue Doe",3,new Date());
            Patient patient4 = new Patient(4,"Hank Doe",4,new Date());
            Patient patient5 = new Patient(5,"Hart Doe",5,new Date());
            Patient patient6 = new Patient(6,"Alphonso Doe",6,new Date());
            Patient patient7 = new Patient(7,"Zee Doe",7,new Date());
            Patient patient8 = new Patient(8,"Kola Doe",8,new Date());
            Patient patient9 = new Patient(9,"Dome Doe",9,new Date());
            Patient patient10 = new Patient(10,"Fat Doe",1,new Date());
            Patient patient11 = new Patient(11,"Gayle Doe",1,new Date());
            Patient patient12 = new Patient(12,"Zoe Doe",1,new Date());
            Patient patient13 = new Patient(13,"Halifax Doe",1,new Date());
            Patient patient14 = new Patient(14,"Wilberforce Doe",2,new Date());
            Patient patient15 = new Patient(15,"William Doe",3,new Date());
            Patient patient16 = new Patient(16,"Amy Doe",4,new Date());
            Patient patient17 = new Patient(17,"George Doe",5,new Date());
            patientRepository.save(patient1);
            patientRepository.save(patient2);
            patientRepository.save(patient3);
            patientRepository.save(patient4);
            patientRepository.save(patient5);
            patientRepository.save(patient6);
            patientRepository.save(patient7);
            patientRepository.save(patient8);
            patientRepository.save(patient9);
            patientRepository.save(patient10);
            patientRepository.save(patient11);
            patientRepository.save(patient12);
            patientRepository.save(patient13);
            patientRepository.save(patient14);
            patientRepository.save(patient15);
            patientRepository.save(patient16);
            patientRepository.save(patient17);
        }
        System.out.println("Total patients seeded: "+patientRepository.count());
    }

    private void loadStaffData() {
        if (staffRepository.count() == 0) {
            Staff staff1 = new Staff(1,"Ade Laoye", UUID.randomUUID().toString(),new Date());
            Staff staff2 = new Staff(2,"Pamilerin Mayowa", UUID.randomUUID().toString(),new Date());
            Staff staff3 = new Staff(3,"Zoe Sands", UUID.randomUUID().toString(),new Date());
            Staff staff4 = new Staff(4,"Bernie Cook", UUID.randomUUID().toString(),new Date());
            Staff staff5 = new Staff(5,"Cornelius Finch", UUID.randomUUID().toString(),new Date());

            staffRepository.save(staff1);
            staffRepository.save(staff2);
            staffRepository.save(staff3);
            staffRepository.save(staff4);
            staffRepository.save(staff5);

        }
        System.out.println("Total staff seeded : " +staffRepository.count());
    }


}

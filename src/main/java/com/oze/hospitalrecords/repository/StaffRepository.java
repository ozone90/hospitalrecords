package com.oze.hospitalrecords.repository;


import com.oze.hospitalrecords.model.Patient;
import com.oze.hospitalrecords.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;


public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query("SELECT s FROM Staff s WHERE s.name =?1")
    Staff findByName(String name);

    /**
     * This method is used to update a staff
     * @param id id to be updated
     * @param name name of staff
     * @return returns 1 if successful, 0 if failed
     */
    @Transactional
    @Modifying
    @Query("UPDATE Staff s SET s.name=?2 WHERE s.id=?1")
    int updateStaff(int id, String name);


    @Query("SELECT s FROM Staff s WHERE s.id =?1")
    Staff findStaffById(int id);
}

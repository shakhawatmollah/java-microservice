package com.shakhawat.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllBySchoolId(Integer schoolId);

    @Modifying
    @Query("DELETE FROM Student s WHERE s.schoolId = :schoolId")
    int deleteAllBySchoolId(@Param("schoolId") Integer schoolId);

    List<Student> deleteBySchoolId(Integer schoolId);

}

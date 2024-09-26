package com.shakhawat.student;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }

    @Transactional
    public int deleteStudentBySchoolId(Integer schoolId){
        return repository.deleteAllBySchoolId(schoolId);
    }

    @Transactional
    public List<Student> deleteStudentsBySchoolId(Integer schoolId){
        return repository.deleteBySchoolId(schoolId);
    }

}

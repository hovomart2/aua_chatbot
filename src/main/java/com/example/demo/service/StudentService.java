package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(MultipartFile file) {
        String line = "";
        String splitBy = ";";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                Student student = new Student();
                student.setFirstName(data[0]);
                student.setLastName(data[1]);
                student.setAuaId(data[2]);
                student.setMajor(data[3]);
                student.setAdmissionYear(Integer.parseInt(data[4]));
                studentRepository.save(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import com.mysql.cj.x.protobuf.MysqlxCursor;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public UserService(UserRepository userRepository, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    public void save(MultipartFile file) throws NoSuchAlgorithmException {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                User user = new User();
                Optional<Student> oStudent = studentRepository.findById(Long.parseLong(data[0]));
                if(oStudent.isEmpty()){
                    continue;
                }
                Student student = oStudent.get();
                user.setStudent(student);
                user.setUsername(data[1]);
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data[2].getBytes(StandardCharsets.UTF_8));
                BigInteger number = new BigInteger(1, hash);
                StringBuilder hexString = new StringBuilder(number.toString(16));
                while (hexString.length() < 64) {
                    hexString.insert(0, '0');
                }
                user.setPassword(hexString.toString());
                userRepository.save(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.demo;

import com.example.demo.model.User;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        SpringApplication.run(DemoApplication.class, args);

        //        givenPythonScript_whenPythonProcessExecuted_thenSuccess();
    }


    public static void givenPythonScript_whenPythonProcessExecuted_thenSuccess()
            throws ExecuteException, IOException {
        String line = "python C:/Users/FNULNU/Desktop/pyt.py";
        CommandLine cmdLine = CommandLine.parse(line);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);

        int exitCode = executor.execute(cmdLine);
    }
}


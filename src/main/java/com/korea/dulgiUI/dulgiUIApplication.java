package com.korea.dulgiUI;

import com.korea.dulgiUI.global.OSType;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class dulgiUIApplication {
    @Getter
    private static OSType osType;

    public static void main(String[] args) {
        osType = OSType.getInstance();
        if(osType!=null)
            SpringApplication.run(dulgiUIApplication.class, args);
    }

}

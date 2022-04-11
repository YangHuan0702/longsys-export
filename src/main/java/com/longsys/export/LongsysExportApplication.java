package com.longsys.export;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author huan.yang
 */
@SpringBootApplication
@EnableAsync
public class LongsysExportApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongsysExportApplication.class, args);
    }

}

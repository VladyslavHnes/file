package com.eco.monitor.file.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Application Runner.
 *
 * @author Vladyslav Hnes
 */
@SpringBootApplication(scanBasePackages = {"com.eco.monitor.file"})
public class Application {

    protected Application() {
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
        new SpringApplicationBuilder(Application.class).run(args);
    }
}

package com.gerimedica.codelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/***
 * @author M.Tugra Er
 *     18.09.2022
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.gerimedica.codelist"})
public class CodeListApp {

    public static void main(String[] args) {
        SpringApplication.run(CodeListApp.class, args);
    }
}

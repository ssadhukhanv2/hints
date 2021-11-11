package com.ssadhukhanv2.hints;

import com.ssadhukhanv2.hints.model.Hint;
import com.ssadhukhanv2.hints.model.Information;
import com.ssadhukhanv2.hints.repo.HintRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class HintsApplication implements CommandLineRunner {
    /*
     * @Slf4j
     * Logback with Lombok
     * https://stackoverflow.com/questions/50379488/using-logback-with-lombok
     * */

    @Autowired
    HintRepository hintRepository;

    public static void main(String[] args) {
        SpringApplication.run(HintsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            String hintTitle = "Hint Title " + (i + 1);
            String hintDescription = "Hint Description " + (i + 1);
            Hint hint = new Hint(null, hintTitle, hintDescription, null, null);
            System.out.println(hint);
            hintRepository.save(hint);
            System.out.println("Find All " + hintRepository.findAll());
        }
        hintRepository.playWithEntityManager();
    }
}

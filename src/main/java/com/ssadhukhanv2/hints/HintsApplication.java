package com.ssadhukhanv2.hints;

import com.ssadhukhanv2.hints.model.Content;
import com.ssadhukhanv2.hints.model.Hint;
import com.ssadhukhanv2.hints.model.Information;
import com.ssadhukhanv2.hints.repo.HintRepository;
import com.ssadhukhanv2.hints.repo.InformationJPARepository;
import com.ssadhukhanv2.hints.repo.InformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;


@Slf4j
@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.ssadhukhanv2.hints.repo"})
//@EntityScan(basePackages = {"com.ssadhukhanv2.hints.model"})
public class HintsApplication implements CommandLineRunner {
    /*
     * @Slf4j
     * Logback with Lombok
     * https://stackoverflow.com/questions/50379488/using-logback-with-lombok
     * */

    @Autowired
    EntityManager em;

    @Autowired
    HintRepository hintRepository;

    @Autowired
    InformationRepository informationRepository;

    @Autowired
    InformationJPARepository informationJPARepository;

    public static void main(String[] args) {
        SpringApplication.run(HintsApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            String hintTitle = "Hint Title " + (i + 1);
            String hintDescription = "Hint Description " + (i + 1);
            Hint hint = new Hint(null, hintTitle, hintDescription, null, null);
            System.out.println(hint);
            hintRepository.save(hint);
            System.out.println("Find All " + hintRepository.findAll());
        }
        //hintRepository.playWithEntityManager();


        Long tempId = 0L;
        for (int i = 0; i < 5; i++) {
            String informationTitle = "Information Title " + (i + 1);
            String informationDescription = "Information Description " + (i + 1);
            String url = "www.google.com";
            Information information = new Information();
            information.setInformationTitle(informationTitle);
            information.setInformationDescription(informationDescription);
            information.setInformationUrl(url);
            Content content = new Content();
            String staticContent = "This is a very long" + repeatChars('g', i + 20) + " string";
            content.setStaticContent(staticContent);
            information.setContent(content);
            informationRepository.save(information);

            System.out.println("------------------------" + information.getInformationId() + "-------------------------");
            //printInformationWithContent(information.getInformationId());
            em.persist(information);
            tempId = information.getInformationId();

        }
        Information information = informationRepository.findById(tempId);
        informationRepository.findAll().forEach((info) -> {
            System.out.println(info.toString() + "" + info.getContent().toString());
        });
        informationJPARepository.findAll().forEach((info) -> {
            System.out.println(info.toString() + "" + info.getContent().toString());
        });

    }

    public void printInformationWithContent(Long id) {
        Information information = em.find(Information.class, id);
        Content content = information.getContent();
        log.info("information -> {}", information);
        System.out.println(information);

        log.info("content -> {}", information.getContent());
    }

    public String repeatChars(char c, int reps) {
        String repeatedChars = "";
        while (reps-- > 0) {
            repeatedChars += c;
        }
        return repeatedChars;
    }
}

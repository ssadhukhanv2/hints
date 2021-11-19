package com.ssadhukhanv2.hints;

import com.ssadhukhanv2.hints.mail.EmailServiceImpl;
import com.ssadhukhanv2.hints.model.*;
import com.ssadhukhanv2.hints.repo.HintRepository;
import com.ssadhukhanv2.hints.repo.InformationJPARepository;
import com.ssadhukhanv2.hints.repo.InformationRepository;
import com.ssadhukhanv2.hints.repo.UserRepository;
import com.zaxxer.hikari.HikariDataSource;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ScrollableResults;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
import javax.servlet.FilterChain;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @Value("${admin.username}")
    @Autowired
    String adminUserName;
    @Value("${admin.password}")
    @Autowired
    String adminPassword;
    @Value("${admin.email}")
    @Autowired
    String adminEmail;
    @Value("${admin.role.name}")
    @Autowired
    String adminRoleName;


    @Value("${sudo.username}")
    @Autowired
    String sudoUserName;
    @Value("${sudo.password}")
    @Autowired
    String sudoPassword;
    @Value("${sudo.email}")
    @Autowired
    String sudoEmail;
    @Value("${sudo.role.name}")
    @Autowired
    String sudoRoleName;


    @Autowired
    EntityManager em;

    @Autowired
    HintRepository hintRepository;

    @Autowired
    InformationRepository informationRepository;

    @Autowired
    InformationJPARepository informationJPARepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailServiceImpl emailService;


    @Autowired
//    @Qualifier("encoder")
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HintsApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //createData();
//        String userName = adminUserName;
//        String userEmail = adminEmail;
//        String userPassword = adminPassword;

        //Admin User
        User user = new User();
        user.setUserName(adminUserName);
        user.setUserEmail(adminEmail);
        user.setUserPassword(passwordEncoder.encode(adminPassword));
        List<Authority> authorityList = new ArrayList<>();
        Authority authority = new Authority();
        authority.setUser(user);
        authority.setName(adminRoleName);
        user.getAuthorityList().add(authority);
        userRepository.save(user);

        //Sudo User
        user = new User();
        user.setUserName(sudoUserName);
        user.setUserEmail(sudoEmail);
        user.setUserPassword(passwordEncoder.encode(sudoPassword));
        authorityList = new ArrayList<>();
        authority = new Authority();
        authority.setUser(user); //User needs to be set or else it doesn't save the user primary key
        authority.setName(sudoRoleName);
        user.getAuthorityList().add(authority);
        userRepository.save(user);


        //emailService.sendSimpleMessage("senderemail@gmail.com", "Server Started", "Hint Server Started");
        //emailService.sendMessageWithAttachment("senderemail@gmail.com", "Server Started", "Hint Server Started","HELP.md");
    }

    @Transactional
    public void createData() {
        int numberOfUsers = 10;

        for (int u = 1; u <= numberOfUsers; u++) {

            String userName = "User " + u;
            String userEmail = "user" + u + "@emaildomain.com";
            String userPassword = "user" + u + "password";
            User user = new User();
            user.setUserName(userName);
            user.setUserEmail(userEmail);
            String password = passwordEncoder.encode("password");
            user.setUserPassword(password);
            userRepository.save(user);

            for (int i = 0; i < 5; i++) {
                String informationTitle = "Information Title " + (i + 1);
                String informationDescription = "Information Description " + (i + 1);
                String url = "www.google.com";
                Information information = new Information();
                information.setInformationTitle(informationTitle);
                information.setInformationDescription(informationDescription);
                information.setInformationUrl(url);

                information.setUser(user);


                Content content = new Content();
                String staticContent = "This is a very long" + repeatChars('g', i + 20) + " string";
                content.setStaticContent(staticContent);

                content.setUser(user);


                information.setContent(content);
                //printInformationWithContent(information.getInformationId());
                String hintTitle = "Hint Title " + (i + 1);
                String hintDescription = "Hint Description " + (i + 1);
                Hint hint = new Hint();
                hint.setHintTitle(hintTitle);
                hint.setHintDescription(hintDescription);
                hint.addInformation(information);
                hint.addInformation(information);


                hint.setUser(user);

//                System.out.println(hint);
                hintRepository.save(hint);
//                System.out.println("------------------------" + hint.getHintId() + "-------------------------");
                //System.out.println("Find All " + hintRepository.findAll());
            }
            hintRepository.findAll().forEach((hint) -> {
//                log.info("------------------");
//                log.info("Hint->{}", hint);
//                log.info("Hint.User->{}", hint.getUser());
                hint.getInformationList().forEach((information -> {
//                    log.info("Information->{}", information);
//                    log.info("Information.user->{}", information.getUser());
//                    log.info("Content->{}", information.getContent());
//                    log.info("Content.user->{}", information.getContent().getUser());
                }));
            });

        }
        User user = userRepository.findByUserNameOrEmail("user10@emaildomain.com");
        log.info(user.toString());

    }

    public String repeatChars(char c, int reps) {
        String repeatedChars = "";
        while (reps-- > 0) {
            repeatedChars += c;
        }
        return repeatedChars;

    }


    public void scratchPad() {

//        EntityManagerFactory em;
//
//        Persistence p;
//
//        SessionFactoryImpl s;
//
//        JdbcTemplate jdbcTemplate;
//
//        DataSource dataSource;
//
//        HikariDataSource hikariDataSource;
//
//        RestTemplate restTemplate;
//
//        FilterChain filterChain;
//        FilterChainProxy filterChainProxy;
//        AbstractJdbcConfiguration abstractJdbcConfiguration;
//


        //https://www.fatalerrors.org/a/review-of-eight-classic-design-patterns-in-spring-security-framework.html

        //1. Template method mode
        AbstractUserDetailsAuthenticationProvider abstractUserDetailsAuthenticationProvider;
        ProviderManager providerManager;
    }
}

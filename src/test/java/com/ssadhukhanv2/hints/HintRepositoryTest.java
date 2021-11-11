package com.ssadhukhanv2.hints;

import com.ssadhukhanv2.hints.repo.HintRepository;

import static org.junit.jupiter.api.Assertions.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class HintRepositoryTest {


    HintRepository hintRepository;

    /*
    * Constructor based Dependency Injection
    * */
    @Autowired
    public HintRepositoryTest(HintRepository hintRepository) {
        this.hintRepository = hintRepository;
    }

    @Test
    public void testFetchAll() {
        final int size = 6;
        log.info("Testing testFetchAll");
        assertEquals(size,hintRepository.findAll().size());
    }

    @Test
    public void testFindById() {
        log.info("Testing testFetchAll");
        final long id = 2;
        assertEquals(id,hintRepository.findById(id).getHintId());
    }

    //Write Test Cases for other methods
}

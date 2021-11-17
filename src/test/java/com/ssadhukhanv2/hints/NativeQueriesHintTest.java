//package com.ssadhukhanv2.hints;
//
//import com.ssadhukhanv2.hints.model.Hint;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.persistence.EntityManager;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import java.util.List;
//
//@Slf4j
//@SpringBootTest
//public class NativeQueriesHintTest {
//    @Autowired
//    EntityManager em;
//
//    @Test
//    public void nativeQuery() {
//        Query query = em.createNativeQuery("SELECT * FROM HINT", Hint.class);
//        List<Hint> hintList
//                = query.getResultList();
//        log.info("SELECT * FROM HINT -> ", hintList);
//        Assertions.assertTrue(hintList.size() > 0);
//    }
//
//    @Test
//    public void nativeQueryWithParameter() {
//        Query query = em.createNativeQuery("SELECT * FROM HINT where HINT_ID=?", Hint.class);
//        query.setParameter(1, 1);
//        List<Hint> hintList
//                = query.getResultList();
//        log.info("SELECT * FROM HINT where HINT_ID=?", hintList);
//        Assertions.assertTrue(hintList.size() > 0);
//    }
//
//    @Test
//    public void nativeQueryWithNamedParameter() {
//        Query query = em.createNativeQuery("SELECT * FROM HINT where HINT_ID= :id", Hint.class);
//        query.setParameter("id", 1);
//        List<Hint> hintList
//                = query.getResultList();
//        log.info("SELECT * FROM HINT where HINT_ID= :id -> {}", hintList);
//        Assertions.assertTrue(hintList.size() > 0);
//    }
//
//    @Transactional
//    @Test
//    @DirtiesContext
//    public void nativeQueryUpdate() {
//        Query query = em.createNativeQuery("UPDATE HINT SET HINT_DESCRIPTION='This is new update' WHERE HINT_ID= :id", Hint.class);
//        query.setParameter("id", 1);
//        int rowsUpdated = query.executeUpdate();
//        log.info("UPDATE HINT SET HINT_DESCRIPTION='This is new update' WHERE HINT_ID= :id -> ", rowsUpdated);
//        Assertions.assertTrue(rowsUpdated > 0);
//    }
//}

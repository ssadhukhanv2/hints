package com.ssadhukhanv2.hints.repo;


import com.ssadhukhanv2.hints.model.Content;
import com.ssadhukhanv2.hints.model.Hint;
import com.ssadhukhanv2.hints.model.Information;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Slf4j
@Repository
public class InformationRepository {
    private EntityManager entityManager;

    @Autowired
    InformationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //save & update
    public Information save(Information information) {
        if (information.getInformationId() == null) {
//            Belo lines has been removed by using cascade = CascadeType.ALL inside Information
//            Extremely bad design, need to change the below line
//            if (information.getContent() != null) {
//                entityManager.persist(information.getContent());
//            }
            entityManager.persist(information);
        } else {
            entityManager.merge(information);
        }
        return information;
    }

    //findById
    public Information findById(Long id) {
        Information information = entityManager.find(Information.class, id);
        //information.setContent(information.getContent());
        return information;
    }

    //findAll
    public List<Information> findAll() {
        /*
         * Query written in JPQL
         * */
        return entityManager.createNativeQuery("SELECT * FROM CONTENT,INFORMATION WHERE CONTENT.CONTENT_ID =INFORMATION.CONTENT_CONTENT_ID ", Information.class).getResultList();
    }

    public void printInformationWithContent(Long id) {
        Information information = entityManager.find(Information.class, id);
        Content content = information.getContent();
        log.info("information -> {}", information);
        System.out.println(information);

        log.info("content -> {}", information.getContent());
    }
}

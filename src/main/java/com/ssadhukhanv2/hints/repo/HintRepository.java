package com.ssadhukhanv2.hints.repo;

import com.ssadhukhanv2.hints.model.Hint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@Repository
public class HintRepository {

    private EntityManager entityManager;

    @Autowired
    HintRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //save & update
    public Hint save(Hint hint) {
        //If id of hint is null persist(insert)
        //else merge(update)
        if (hint.getHintId() == null) {
            entityManager.persist(hint);
        } else {
            entityManager.merge(hint);
        }
        return hint;
    }

    //findById
    public Hint findById(Long id) {
        return entityManager.find(Hint.class, id);
    }

    //deleteById
    public Hint deleteById(Long id) {
        Hint hint = findById(id);
        return entityManager.find(Hint.class, id);
    }


    //findAll
    public List<Hint> findAll() {
        /*
         * Query written in JPQL
         * */
        return entityManager.createQuery("SELECT h FROM Hint h", Hint.class).getResultList();
    }

    public void playWithEntityManager() throws InterruptedException {

        /*
         * Entity Manager Tracks changes to the object it's attached to
         * For example after the next two statements are executed
         * it will track changes on hint1 & hint2
         * */

        Hint hint1 = entityManager.find(Hint.class, Long.valueOf(2));
        Hint hint2 = entityManager.find(Hint.class, Long.valueOf(5));


        log.info("1st Update in progress....");
        Thread.sleep(1000);

        /*
         * Since Entity Manager tracks changes, the description for both hint1 & hint2
         * are updated in Database once the flush method is called
         * */
        hint1.setHintDescription("First Update to Hint 2.");
        hint2.setHintDescription("First Update to Hint 5.");
        //Thread.sleep(2000);
        entityManager.flush();
        log.info("After 1st Update Hints: {}", findAll());


        log.info("2nd Update in progress....");
        Thread.sleep(1000);
        log.info("entityManager.detach(hint1)");

        /*
         * Here we detach hint1 from Entity Manager
         * */
        entityManager.detach(hint1);


        /*
         * Since hint1 is now detached from the Entity Manager,
         * it will only track changes for hint2
         * and in this case "Only hint2 will be updated"
         * */
        hint1.setHintDescription("Second Update to Hint 2.");
        hint2.setHintDescription("Second Update to Hint 5.");
        entityManager.flush();
        log.info("Updated Hints: {}", findAll());


        log.info("Second Update to Hint 2 reverted");
        /*
         * Here we refresh the hint2 object
         * This means the description which was changed in previous lines will be
         * reverted to what was initially saved in the database
         * */
        entityManager.refresh(hint2);
        log.info("entityManager.refresh(hint2)");
        log.info("Updated Hints: {}", findAll());


        log.info("entityManager.clear()");
        /*
         * Here we clear the Entity Manager
         * This means all the existing objects will be detached
         * Thus NO further changes will be tracked for hint1 or hint2
         * */
        entityManager.clear();
        log.info("3rd Update in progress but no rows updated....");
        Thread.sleep(1000);
        hint1.setHintDescription("Third Update to Hint 2.");
        hint2.setHintDescription("Third Update to Hint 5.");
        entityManager.flush();
        log.info("Updated Hints: {}", findAll());

    }

}

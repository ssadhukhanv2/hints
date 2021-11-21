//package com.ssadhukhanv2.hints.repo;
//
//import com.ssadhukhanv2.hints.model.Hint;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//public class ContentRepository {
//
//    private EntityManager entityManager;
//
//    @Autowired
//    ContentRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
////
////    //save & update
////    public Hint save(Hint hint) {
////        //If id of hint is null persist(insert)
////        //else merge(update)
////        if (hint.getHintId() == null) {
////            entityManager.persist(hint);
////        } else {
////            entityManager.merge(hint);
////        }
////        return hint;
////    }
////
////    //findById
////    public Hint findById(Long id) {
////        return entityManager.find(Hint.class, id);
////    }
////
////    //deleteById
////    public Hint deleteById(Long id) {
////        Hint hint = findById(id);
////        return entityManager.find(Hint.class, id);
////    }
////
////
////    //findAll
////    public List<Hint> findAll() {
////        /*
////         * Query written in JPQL
////         * */
////        return entityManager.createQuery("SELECT h FROM Hint h", Hint.class).getResultList();
////    }
//}

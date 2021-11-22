package com.ssadhukhanv2.hints.repo;

import com.ssadhukhanv2.hints.model.Information;
import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.model.NodeCategory;
import com.ssadhukhanv2.hints.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Slf4j
@Repository
public class UserRepository{
    private EntityManager entityManager;

    @Autowired
    UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public User save(User user) {
        if (user.getUserId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
        return user;
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }


    public void deleteById(Long userId) {
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
    }


    //findAll
    public List<Node> findRootNodes(User user) {

        TypedQuery<Node> query = entityManager.createQuery(
                "SELECT n FROM Node n JOIN User u ON u.userId=n.user.userId where u.userId=:userId and n.nodeCategory=:category", Node.class);
        query.setParameter("userId", user.getUserId());
        query.setParameter("category", NodeCategory.ROOT);

        return query.getResultList();
    }


    //findAll
    public List<User> findAll() {
        // Used Native Query here
        // return entityManager.createNativeQuery("SELECT * FROM CONTENT,INFORMATION WHERE CONTENT.CONTENT_ID =INFORMATION.CONTENT_CONTENT_ID ", Information.class).getResultList();
        return entityManager.createNativeQuery("SELECT * FROM USER ", User.class).getResultList();
    }

    //findByUserName
    public User findByUserNameOrEmail(String userNameOrEmail) {
        User user = entityManager.createQuery("select u from User u WHERE u.userName= :USERNAME OR u.userEmail= :USEREMAIL", User.class)
                .setParameter("USERNAME", userNameOrEmail)
                .setParameter("USEREMAIL", userNameOrEmail).getSingleResult();
        System.out.println(user.getNodeList());
        return user;

    }
}
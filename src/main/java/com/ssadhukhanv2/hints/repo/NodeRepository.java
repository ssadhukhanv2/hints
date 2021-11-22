package com.ssadhukhanv2.hints.repo;

import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@Slf4j
@Repository
public class NodeRepository {
    private EntityManager entityManager;

    @Autowired
    public NodeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //save & update
    public Node save(Node node) {
        if (node.getNodeId() == null) {
            entityManager.persist(node);
        } else {
            entityManager.merge(node);
        }
        return node;
    }

    //findById
    public Node findById(Long id) {
        Node node = entityManager.find(Node.class, id);
        return node;
    }

    //findAll
    public List<Node> findAll() {
        return entityManager.createNativeQuery("SELECT * FROM NODE ", Node.class).getResultList();
    }


    public void deleteById(Long nodeId) {
        Node node = entityManager.find(Node.class, nodeId);
        entityManager.remove(node);
    }

}

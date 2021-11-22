package com.ssadhukhanv2.hints.service;

import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.repo.NodeRepository;
import com.ssadhukhanv2.hints.repo.UserRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class NodeServiceImpl implements NodeService<Node, Long> {
    @Autowired
    NodeRepository nodeRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public Node createNode(Node node) {
        return nodeRepository.save(node);
    }

    @Override
    public Node updateNode(Node node) {
        // First Fetch the Node based on the is of the id of the node
        // Check if the user_id of the fetched node matches the user_id in the request payload
        // If yes, then save, else throw exception
        return nodeRepository.save(node);
    }

    @Override
    public void deleteNode(Long nodeId) {
        nodeRepository.deleteById(nodeId);
    }

    @Override
    public Node getNode(Long nodeId) {
        return nodeRepository.findById(nodeId);
    }
}

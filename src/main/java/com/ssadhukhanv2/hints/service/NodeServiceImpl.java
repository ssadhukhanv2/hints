package com.ssadhukhanv2.hints.service;

import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.repo.NodeRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class NodeServiceImpl implements NodeService<Node, Long> {
    NodeRepository nodeRepository;

    @Override
    public Node createNode(Node node) {
        return nodeRepository.save(node);
    }

    @Override
    public Node updateNode(Node node) {
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

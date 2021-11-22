package com.ssadhukhanv2.hints.service;

import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.model.User;

public interface NodeService<T extends Node, S extends Number> {
    public T createNode(T node);

    public T updateNode(T node);

    public void deleteNode(S nodeId);

    public T getNode(S nodeId);
}

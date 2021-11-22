package com.ssadhukhanv2.hints.controller;

import com.ssadhukhanv2.hints.model.Node;
import com.ssadhukhanv2.hints.repo.NodeRepository;
import com.ssadhukhanv2.hints.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    //API DESIGN
    //https://www.mscharhag.com/p/rest-api-design
    /*
     * https://www.mscharhag.com/p/rest-api-design
     * https://www.mscharhag.com/api-design/http-post-put-patch
     * POST requests create child resources at a server defined URI. POST is also used as general processing operation
     * PUT requests create or replace the resource at the client defined URI
     * PATCH requests update parts of the resource at the client defined URI
     * */
    private NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    // POST
    // node/
    @PostMapping("/")
    public @ResponseBody
    Node createNode(@RequestBody Node node) {
        return nodeService.createNode(node);
    }

    // GET
    // node/{nodeId}
    @GetMapping("/{nodeIdentifier}")
    public @ResponseBody
    Node getNode(@PathVariable(name = "nodeIdentifier") Long id) {
        return nodeService.getNode(id);
    }


    // PUT
    // node/{nodeId}
    @PutMapping("/{nodeIdentifier}")
    public @ResponseBody
    Node createOrUpdateNode(@PathVariable(name = "nodeIdentifier") Long id, @RequestBody Node node) {
        node.setNodeId(id);
        return nodeService.updateNode(node);
    }

    // PATCH
    // node/{nodeId}
    //https://www.mscharhag.com/api-design/rest-partial-updates-patch
    @PatchMapping("/{nodeIdentifier}")
    public @ResponseBody
    Node updateNode(@PathVariable(name = "nodeIdentifier") Long id, @RequestBody Node node) {
        node.setNodeId(id);
        return nodeService.updateNode(node);
    }




}

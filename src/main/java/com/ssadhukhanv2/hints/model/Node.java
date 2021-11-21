package com.ssadhukhanv2.hints.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nodeId;
    private String nodeTitle;
    private String nodeDescription;

    @Enumerated(EnumType.STRING)
    private NodeCategory nodeCategory;//If Node Category is not ROOT then referenceNodeList should not be null

    @CreationTimestamp
    LocalDateTime createdDate;
    // @UpdateTimestamp is modified on update
    @UpdateTimestamp
    LocalDateTime lastUpdatedDate;


    @ToString.Exclude
    //https://stackoverflow.com/questions/3113885/difference-between-one-to-many-many-to-one-and-many-to-many
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "NODE_REFERENCE", joinColumns = {@JoinColumn(
            name = "NODE_ID"
    )}, inverseJoinColumns = {@JoinColumn(name = "CHILD_NODE_ID")})
    List<Node> referenceNodeList = new ArrayList<>();


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "NODE_TAG", joinColumns = {@JoinColumn(name = "NODE_ID")}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    List<Tag> tagList = new ArrayList<>();


    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "NODE_INFORMATION", joinColumns = {@JoinColumn(
            name = "NODE_ID"
    )}, inverseJoinColumns = {@JoinColumn(name = "INFORMATION_ID")})
    List<Information> informationList = new ArrayList<Information>();


    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void addTag(Tag tag) {
        tagList.add(tag);
    }

    public void addInformation(Information information) {
        this.informationList.add(information);
    }

    public void addChildNode(Node node) {
        referenceNodeList.add(node);
    }

}

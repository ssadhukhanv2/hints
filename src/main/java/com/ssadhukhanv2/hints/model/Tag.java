package com.ssadhukhanv2.hints.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tagId;
    private String tagString;
    @CreationTimestamp
    LocalDateTime createdDate;

    //This is defined as child but could be defined as parent, Not sure
    //https://stackoverflow.com/questions/21985308/how-is-the-owning-side-of-this-many-to-many-relationship-determined
    @ToString.Exclude
    @ManyToMany(mappedBy = "tagList", fetch = FetchType.LAZY)
    List<Node> nodeList = new ArrayList<>();


}

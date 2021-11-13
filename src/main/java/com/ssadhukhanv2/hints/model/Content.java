package com.ssadhukhanv2.hints.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Content {
    /*
     * This class is created to explain one to one mapping, so this may be removed later
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contentId;

    //This will generally have the web page details, so we may need to change the datatype later
    private String staticContent;

    // @OneToOne Here we are basically telling to the JPA that
    // "Information" is the Parent Class & "Content" is having OneToOne Mapping with it
    // (mappedBy = "content") specifies that the parent class("Information")
    // is having a field called "content"
    // so essentially, table for Information will have the column for contentId
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "content")
    private Information information;
    @CreationTimestamp
    LocalDateTime createdDate;
    @UpdateTimestamp
    LocalDateTime lastUpdatedDate;


    @ToString.Exclude
    @ManyToOne
    private User user;


}

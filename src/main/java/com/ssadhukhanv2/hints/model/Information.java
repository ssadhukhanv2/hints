package com.ssadhukhanv2.hints.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long informationId;
    private String informationTitle;
    private String informationDescription;
    private String informationUrl;
    @CreationTimestamp
    LocalDateTime createdDate;
    @UpdateTimestamp
    LocalDateTime lastUpdatedDate;

    //@ToString.Exclude is used here other wise circular between both objects result in stackoverflowexception
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Content content;

    @ToString.Exclude
    //@ManyToMany(mappedBy = "informationList") //This results in a table INFORMATION_HINT_LIST
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "informationList")
    //This suggest that mapping for the below
    // list is availale in an object informationList so table INFORMATION_HINT_LIST will not be created
    // this will make Hint Parent, and HINT_INFORMATION_LIST which will be created by HINT would be used for Mapping!!
    private List<Hint> hintList = new ArrayList<Hint>();

    @ToString.Exclude
    @ManyToOne
    private User user;

    public void addHint(Hint hint) {
        this.hintList.add(hint);
    }

}

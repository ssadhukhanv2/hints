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
//@Table(name = "HINT")
public class Hint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long hintId;
    String hintTitle;
    //@Column(name = "HINT_DESCRIPTION")
    String hintDescription;
    //https://stackoverflow.com/questions/42366763/hibernate-creationtimestamp-updatetimestamp-for-calendar
    // @CreationTimestamp is not modified on update
    @CreationTimestamp
    LocalDateTime createdDate;
    // @UpdateTimestamp is modified on update
    @UpdateTimestamp
    LocalDateTime lastUpdatedDate;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //This results in a table HINT_INFORMATION_LIST
    // Unlike Information.hintList which is (mappedBy = "informationList")
    // informationList is not referenced by anything, making Hint the Parent
    // as a result the JOIN_TABLE by default is HINT_INFORMATION_LIST
    // To customize the Join Table @JoinTable annotation is used
    @JoinTable(name = "HINT_INFORMATION", joinColumns = {@JoinColumn(
            name = "HINT_ID"
    )}, inverseJoinColumns = {@JoinColumn(name = "INFORMATION_ID")})
    List<Information> informationList = new ArrayList<Information>();

    public void addInformation(Information information) {
        this.informationList.add(information);
    }

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}

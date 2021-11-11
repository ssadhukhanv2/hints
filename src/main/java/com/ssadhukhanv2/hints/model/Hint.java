package com.ssadhukhanv2.hints.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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


}

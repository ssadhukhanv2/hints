package com.ssadhukhanv2.hints.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Content content;

}

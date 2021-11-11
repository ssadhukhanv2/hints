package com.ssadhukhanv2.hints.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long informationId;
    String informationTitle;
    String informationDescription;
    String informationUrl;
}

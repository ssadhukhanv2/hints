package com.ssadhukhanv2.hints.repo;

import com.ssadhukhanv2.hints.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationJPARepository extends JpaRepository<Information,Long> {
}

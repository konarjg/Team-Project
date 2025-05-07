package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    
}

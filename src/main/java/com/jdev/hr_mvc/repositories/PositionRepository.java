package com.jdev.hr_mvc.repositories;

import com.jdev.hr_mvc.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    boolean existsByTitle(String title);
}

package com.example.mynavi.direction.repository;

import com.example.mynavi.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {

}

package com.example.mynavi.randomNavi.direction.repository;

import com.example.mynavi.randomNavi.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {

}

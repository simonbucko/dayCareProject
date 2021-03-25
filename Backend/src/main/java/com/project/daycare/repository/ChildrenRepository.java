package com.project.daycare.repository;

import com.project.daycare.module.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface ChildrenRepository extends JpaRepository<Children,Integer> {
    @Override
    List<Children> findAll();
}

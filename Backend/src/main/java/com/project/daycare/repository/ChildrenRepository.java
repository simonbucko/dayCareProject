package com.project.daycare.repository;

import com.project.daycare.module.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildrenRepository extends JpaRepository<Child,Integer> {
    @Override
    List<Child> findAll();
}

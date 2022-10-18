package com.pthttt.Phone_AI.repository;

import com.pthttt.Phone_AI.model.entity.CPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CPURepository extends JpaRepository<CPU, Long>, QuerydslPredicateExecutor<CPU> {
}

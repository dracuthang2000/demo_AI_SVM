package com.pthttt.Phone_AI.repository;

import com.pthttt.Phone_AI.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>, QuerydslPredicateExecutor<Phone> {
    List<Phone> findByLabelOrderByPrice(String label);
}

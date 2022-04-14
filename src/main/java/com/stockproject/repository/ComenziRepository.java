package com.stockproject.repository;


import com.stockproject.model.Comenzi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComenziRepository extends JpaRepository<Comenzi, Integer> {
}

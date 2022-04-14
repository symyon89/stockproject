package com.stockproject.repository;

import com.stockproject.model.Produse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduseRepository extends JpaRepository<Produse, Integer> {
}

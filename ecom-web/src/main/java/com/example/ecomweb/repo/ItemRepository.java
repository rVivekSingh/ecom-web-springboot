package com.example.ecomweb.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomweb.models.Items;

public interface ItemRepository extends JpaRepository<Items, Long>{
	
	Optional<Items> findById(Long id);
     
}

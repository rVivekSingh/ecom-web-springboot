package com.example.ecomweb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecomweb.models.Orders;


public interface OrderRepository extends JpaRepository<Orders, Integer>{

}

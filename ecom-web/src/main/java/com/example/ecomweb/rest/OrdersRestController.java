package com.example.ecomweb.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomweb.models.Items;
import com.example.ecomweb.models.MultipleOrders;
import com.example.ecomweb.models.Orders;
import com.example.ecomweb.repo.ItemRepository;
import com.example.ecomweb.repo.OrderRepository;

@RestController
public class OrdersRestController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ItemRepository itemRepository;

	@PostMapping("/order")
	public ResponseEntity saveOrder(@RequestBody MultipleOrders order) {

		System.out.println(order);
		List<Orders> orderList = order.getOrder();
		List<Long> successfullOrders = new ArrayList<Long>();
		List<Long> unSuccessfullOrders = new ArrayList<Long>();
		for (Orders eachOrder : orderList) {
			try {
				if (eachOrder.getItemId() != null) {
					Items itemExists = itemRepository.findById(eachOrder.getItemId()).get();
					if (itemExists.getQuantity() != 0) {
						Orders newOrder = orderRepository.save(eachOrder);
						if (newOrder != null) {
							successfullOrders.add(eachOrder.getItemId());
							itemExists.setQuantity(itemExists.getQuantity() - eachOrder.getNumOfItems());
							itemRepository.save(itemExists);
						}
					} else {
						unSuccessfullOrders.add(eachOrder.getItemId());
					}
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				unSuccessfullOrders.add(eachOrder.getItemId());
			}
		}
		return new ResponseEntity<>("Order is successfull for items = " + successfullOrders + 
				" and order was not successfull for items = " + unSuccessfullOrders , HttpStatus.OK);
	}

	@GetMapping("/order")
	public ResponseEntity getOrder(@RequestParam("id") Integer id) {
		if (id != null) {
			Orders order = orderRepository.findById(id).get();
			return new ResponseEntity<>(order, HttpStatus.OK);
		}
		return new ResponseEntity<>("Order Fetched", HttpStatus.OK);
	}

	@DeleteMapping("/order")
	public ResponseEntity deleteOrder(@RequestParam("id") Integer id) {

		try {
			orderRepository.deleteById(id);
			return new ResponseEntity<>("Order Deleted", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Order is not Deleted", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/orders")
	public ResponseEntity getAllItems() {

		List<Orders> orders = orderRepository.findAll();

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

}

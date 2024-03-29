package com.vihan.usecase.trader.ui.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vihan.usecase.trader.ui.model.Trader;
import com.vihan.usecase.trader.ui.repository.TraderRepository;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TraderController {

	@Autowired
	private TraderRepository repository;
	
	@GetMapping("/trading/traders/all")
	public ResponseEntity<List<Trader>> all() {

		try {
			List<Trader> traderList = new ArrayList<Trader>();
			
			repository.findAll().forEach(traderList::add);

			if (traderList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(traderList, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/trading/traders")
	public ResponseEntity<Trader> one(@RequestParam String email) {

		try {
			Trader trader = repository.findByEmail(email);

			if (trader == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(trader, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/trading/traders/register")
	public ResponseEntity<Trader> createTrader(@RequestBody Trader trader) {
		try {

			if (repository.findByEmail(trader.getEmail()) != null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			Trader _trader = repository.save(new Trader(trader.getName(), trader.getEmail(), trader.getBalance(), new Timestamp(new Date().getTime())));
			return new ResponseEntity<>(_trader, HttpStatus.CREATED) ;
 
		} catch(Exception e) { 
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/trading/traders")
	public ResponseEntity<Trader> updateName(@RequestBody Map<String, String> payload) {

		try {
			
			Trader trader = repository.findByEmail(payload.get("email"));

			if (trader == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			trader.setName(payload.get("name"));
			trader.setUpdatedAt(new Timestamp(new Date().getTime()));
			repository.save(trader);

			return new ResponseEntity<>(trader, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/trading/traders/add")
	public ResponseEntity<Trader> updateBalance(@RequestBody Map<String, String> payload) {

		try {
			Trader trader = repository.findByEmail(payload.get("email"));

			if (trader == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			Long curr_balance = trader.getBalance();
			trader.setBalance(curr_balance + Long.parseLong(payload.get("amount")));
			trader.setUpdatedAt(new Timestamp(new Date().getTime()));
			repository.save(trader);
			
			return new ResponseEntity<>(trader, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}

package com.example.InvestobullFintech.Controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.InvestobullFintech.Entity.Candles;
import com.example.InvestobullFintech.Implementation.Implementation;

@RestController
public class CandlesController {
	@Autowired
	Implementation implementation;
	//Post mapping to save the entities into DB
	@PostMapping("/save/bulk")
	public Object saveAllCandles(@RequestBody List<Candles> candles) {
		Collections.reverse(candles);
		for(Candles i : candles)
			implementation.saveJson(i);
		return "Saved Sussefully";
	}

	//GetMapping to get all the Candle Entities
	@GetMapping("/get/all/candles/details")
	public List<Candles> getAllCandles(){
		return implementation.getAllCandelDetails();
	}
	
	//GetMApping to get the Opening Range Breakout Signel
	@GetMapping("/get/all/candles/details/time/{minutes}")
	public Object getAllCandlesTime(@PathVariable int minutes) throws ParseException{
		return  implementation.getORB(minutes);
	}
	
	//GetMapping To get the details of the candles after comined based on the given intervals 
	@GetMapping("get/combined/candles/{minutes}")
	List<Candles> getCombinedCandles(@PathVariable int minutes){
		return implementation.getAllCombinedCandles(minutes);
	}

}

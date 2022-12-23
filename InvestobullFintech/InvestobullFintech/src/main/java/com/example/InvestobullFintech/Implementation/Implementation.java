package com.example.InvestobullFintech.Implementation;

import java.net.MulticastSocket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.InvestobullFintech.Entity.Candles;
import com.example.InvestobullFintech.Repo.CandlesRepository;


@Service
public class Implementation {
	@Autowired
	CandlesRepository candlesRepository;

	//To save all the candles in BD
	public List<Candles> getAllCandelDetails(){
		return candlesRepository.findAll();
	}
	//To get all the Candles
	public Candles saveJson(Candles i) {
		return candlesRepository.save(i);

	}
	//To get ORB
	public Object  getORB(int minutes) throws ParseException {
		List<Candles> allCandles=candlesRepository.findAll();
		String pattern = "dd-MM-yyyy HH:mm:ss";
		Candles firstCandle=allCandles.get(0);
		Date comparissionTime = new SimpleDateFormat(pattern).parse(firstCandle.getLastTradeTime());
		comparissionTime=DateUtils.addMinutes(comparissionTime, minutes);
		for(Candles i:allCandles) {
			Date date= new SimpleDateFormat(pattern).parse(i.getLastTradeTime());
			if(date.compareTo(comparissionTime) <= 0) {
				if(Double.valueOf(firstCandle.getLow())>Double.valueOf(i.getLow())) {
					firstCandle.setLow(i.getLow());
				}
				if(Double.valueOf(firstCandle.getHigh())<Double.valueOf(i.getHigh())) {
					firstCandle.setHigh(i.getHigh());
				}

			} 
		}
		for(Candles i:allCandles) {
			Date date= new SimpleDateFormat(pattern).parse(i.getLastTradeTime());			
			if(comparissionTime.compareTo(date) < 0) {
				if(Double.valueOf(firstCandle.getLow())>Double.valueOf(i.getLow())) {
					return "ORB Candle generated at "+i.getLastTradeTime().toString();
				}
				if(Double.valueOf(firstCandle.getHigh())<Double.valueOf(i.getHigh())) {
					return "ORB Candle generated at "+i.getLastTradeTime().toString();

				}
			}	
		}
		return firstCandle;

	}

	//To get Combined Candles
	public List<Candles> getAllCombinedCandles(int minutes){
		List<Candles> allCandles=candlesRepository.findAll();
		Candles firstCandle=allCandles.get(0);
		Long totalTradeQty=(long) 0;
		int lengthOfCombinedCandelSize=minutes/5;
		ArrayList<Candles> combinedCandleList=new ArrayList<>();
		int count=1;
		int id=0;
		for(int i=1;i<=allCandles.size()-1;i++) {
				if(count<lengthOfCombinedCandelSize) {
					totalTradeQty=(Long.parseLong(firstCandle.getTradedQty())+Long.parseLong(allCandles.get(i).getTradedQty()));
					firstCandle.setTradedQty(totalTradeQty.toString());
					if(Double.valueOf(firstCandle.getLow())>Double.valueOf(allCandles.get(i).getLow())) {
						firstCandle.setLow(allCandles.get(i).getLow());
					}
					if(Double.valueOf(firstCandle.getHigh())<Double.valueOf(allCandles.get(i).getHigh())) {
						firstCandle.setHigh(allCandles.get(i).getHigh());
					}
					firstCandle.setClose((allCandles.get(i).getClose()));
					firstCandle.setId(id);
					count+=1;
				}			
				
				if(count==lengthOfCombinedCandelSize) {
					combinedCandleList.add(firstCandle);
					totalTradeQty=(long) 0;
					id+=1;
					i+=1;
					if(i!=allCandles.size())					
						firstCandle=allCandles.get(i);
					count=1;
				}
				else if (i==allCandles.size()-1) {
					combinedCandleList.add(firstCandle);
					totalTradeQty=(long) 0;
				}
		}

		return combinedCandleList;


	}
}

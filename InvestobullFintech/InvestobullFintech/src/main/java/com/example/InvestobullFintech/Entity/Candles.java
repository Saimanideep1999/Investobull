package com.example.InvestobullFintech.Entity;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="candles_tbl")
public class Candles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private int id;
	private String lastTradeTime;
	private String	quotationLot;
	private String	tradedQty;
	private String	openInterest;
	private String	open;
	private String	high;
	private String	low;
	private String	close;

//    {
//        "LastTradeTime": "02-19-2021 15:25:00",
//        "QuotationLot": "1",
//        "TradedQty": "226385",
//        "OpenInterest": "0",
//        "Open": "672.85",
//        "High": "673.65",
//        "Low": "669.4",
//        "Close": "669.4"
//     },
}

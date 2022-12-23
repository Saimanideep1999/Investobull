package com.example.InvestobullFintech.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InvestobullFintech.Entity.Candles;

@Repository
public interface CandlesRepository extends JpaRepository<Candles, Number>{

}

package com.vihan.usecase.trader.ui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vihan.usecase.trader.ui.model.Trader;

public interface TraderRepository extends JpaRepository<Trader, Long> {

    Trader findByEmail(String email);

}

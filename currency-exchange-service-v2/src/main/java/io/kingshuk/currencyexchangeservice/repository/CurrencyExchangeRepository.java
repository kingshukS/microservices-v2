package io.kingshuk.currencyexchangeservice.repository;

import io.kingshuk.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue,Long> {

    @Query("SELECT e FROM ExchangeValue e WHERE e.fromCurrency = ?1 AND e.toCurrency = ?2")
    List<ExchangeValue> findExchangeValue(String fromCurrency, String toCurrency);

    ExchangeValue findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}

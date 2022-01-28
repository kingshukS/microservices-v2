package io.kingshuk.currencyexchangeservice.service;

import io.kingshuk.currencyexchangeservice.exception.ExchangeNotFoundException;
import io.kingshuk.currencyexchangeservice.model.ExchangeValue;
import io.kingshuk.currencyexchangeservice.repository.CurrencyExchangeRepository;
import io.kingshuk.currencyexchangeservice.resource.CurrencyExchangeResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    private static Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);

    public ExchangeValue getExchangeValue(String fromCurr, String toCurr)
    {
       List<ExchangeValue> exchangeValues = currencyExchangeRepository.findExchangeValue(fromCurr,toCurr);
       if(CollectionUtils.isEmpty(exchangeValues)) {
           logger.error("--CurrencyExchangeService.getExchangeValue(): Unable to find the exchange value for currency={} to currency={}",fromCurr,toCurr);
           throw new ExchangeNotFoundException(fromCurr, toCurr);
       }
       logger.info("--CurrencyExchangeService.getExchangeValue(): exchange value={}", exchangeValues.stream().findFirst().orElse(null));
       return exchangeValues.stream().findFirst().orElse(null);
    }

    public ExchangeValue getExchangeValueByFromAndToCurrency(String fromCurr, String toCurr)
    {
        ExchangeValue exchangeValue = currencyExchangeRepository.findByFromCurrencyAndToCurrency(fromCurr,toCurr);
        if(null == exchangeValue) {
            logger.error("--CurrencyExchangeService.getExchangeValueByFromAndToCurrency(): Unable to find the exchange value for currency={} to currency={}",fromCurr,toCurr);
            throw new ExchangeNotFoundException(fromCurr, toCurr);
        }
        logger.info("--CurrencyExchangeService.getExchangeValueByFromAndToCurrency(): exchange value={}", exchangeValue);
        return exchangeValue;
    }
}

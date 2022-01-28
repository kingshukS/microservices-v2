package io.kingshuk.currencyconversionservice.resource;

import io.kingshuk.currencyconversionservice.model.CurrencyConversionBean;
import io.kingshuk.currencyconversionservice.model.ExchangeValue;
import io.kingshuk.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency-conversion-v2")
public class CurrencyConversionResource {

    private static Logger logger = LoggerFactory.getLogger(CurrencyConversionResource.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable("from") String from,
                                                  @PathVariable("to") String to,
                                                  @PathVariable("quantity") BigDecimal quantity)
    {
        CurrencyConversionBean exchangeValue = restTemplate.getForObject("http://api-gateway-service-v2/currency-exchange-v2/from/"+from+"/to/"+ to, CurrencyConversionBean.class);
        logger.info("--CurrencyConversionResource.convertCurrency(): exchange value={}",exchangeValue);
        BigDecimal conversionMultiple = exchangeValue.getConversionMultiple();
        BigDecimal conversionValue = conversionMultiple.multiply(quantity);
        exchangeValue.setConversionValue(conversionValue);
        exchangeValue.setQuantity(quantity);
        return exchangeValue;
    }

    @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyWithFeign(@PathVariable("from") String from,
                                                           @PathVariable("to") String to,
                                                           @PathVariable("quantity") BigDecimal quantity)
    {
        ExchangeValue exchangeValue = currencyExchangeServiceProxy.retrieveExchangeValue(from,to);
        logger.info("--CurrencyConversionResource.convertCurrencyWithFeign(): exchange value={}",exchangeValue);
        CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean();
        currencyConversionBean.setFromCurrency(exchangeValue.getFromCurrency());
        currencyConversionBean.setToCurrency(exchangeValue.getToCurrency());
        currencyConversionBean.setQuantity(quantity);
        currencyConversionBean.setConversionMultiple(exchangeValue.getConversionMultiple());
        currencyConversionBean.setInstanceId(exchangeValue.getInstanceId());
        BigDecimal conversionMultiple = exchangeValue.getConversionMultiple();
        BigDecimal conversionValue = conversionMultiple.multiply(quantity);
        currencyConversionBean.setConversionValue(conversionValue);

        return currencyConversionBean;
    }
}

package io.kingshuk.currencyconversionservice.proxy;

import io.kingshuk.currencyconversionservice.model.ExchangeValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-gateway-service-v2")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange-v2/from/{fromCurr}/to/{toCurr}")
    ExchangeValue retrieveExchangeValue(@PathVariable("fromCurr") String fromCurr, @PathVariable("toCurr") String toCurr);
}

package io.kingshuk.currencyexchangeservice.exception;

public class ExchangeNotFoundException extends RuntimeException {

    public ExchangeNotFoundException(String fromCurr, String toCurr) {
        super("Exchange not found for currencies: "+fromCurr+" to: "+toCurr);
    }
}

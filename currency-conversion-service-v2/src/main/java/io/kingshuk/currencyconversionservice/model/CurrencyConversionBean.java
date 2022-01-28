package io.kingshuk.currencyconversionservice.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.math.BigDecimal;

public class CurrencyConversionBean {

    private String fromCurrency;
    private String toCurrency;
    private BigDecimal quantity;
    private BigDecimal conversionMultiple;
    private BigDecimal conversionValue;
    private String instanceId;

    public CurrencyConversionBean() {
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public BigDecimal getConversionValue() {
        return conversionValue;
    }

    public void setConversionValue(BigDecimal conversionValue) {
        this.conversionValue = conversionValue;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fromCurrency", fromCurrency)
                .append("toCurrency", toCurrency)
                .append("quantity", quantity)
                .append("conversionMultiple", conversionMultiple)
                .append("conversionValue", conversionValue)
                .append("instanceId", instanceId)
                .toString();
    }
}

package com.trading.input;

import java.math.BigDecimal;
import java.util.Currency;


/**
 * @author vikramjitsingh
 *
 */
public class TradingRequest {

   private final BigDecimal agreedFx;

   private final int units;

   private final BigDecimal pricePerUnit;

   private final BigDecimal tradeAmount;

   private final Currency currency;


   /**
   * @param currency
   * @param agreedFx
   * @param units
   * @param pricePerUnit
   */
   public TradingRequest(Currency currency, BigDecimal agreedFx, int units, BigDecimal pricePerUnit) {
      this.currency = currency;
      this.agreedFx = agreedFx;
      this.units = units;
      this.pricePerUnit = pricePerUnit;
      this.tradeAmount = calculateAmount(this);
   }

   /**
   * @param ins
   * @return
   */
   private static BigDecimal calculateAmount(TradingRequest ins) {
      return ins.getPricePerUnit().multiply(BigDecimal.valueOf(ins.getUnits())).multiply(ins.getAgreedFx());
   }

   /**
   * @return
   */
   public BigDecimal getAgreedFx() {
      return agreedFx;
   }

   /**
   * @return
   */
   public int getUnits() {
      return units;
   }

   /**
   * @return
   */
   public BigDecimal getPricePerUnit() {
      return pricePerUnit;
   }

   /**
   * @return
   */
   public BigDecimal getTradeAmount() {
      return tradeAmount;
   }

   /**
   * @return
   */
   public Currency getCurrency() {
      return currency;
   }
}

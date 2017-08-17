package com.trading.input;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import com.trading.utils.TradingConstants;


/**
 * @author vikramjitsingh
 *
 */
public class SampleClientRequests {

   public static Set<TradingInstruction> getSampleRequests() {
      return new HashSet<>(Arrays.asList(

            new TradingInstruction(TradingConstants.ENTITY_FOO, TradeAction.BUY, LocalDate.of(2017, 8, 11),
                  LocalDate.of(2017, 8, 21), new TradingRequest(Currency.getInstance(TradingConstants.EUROCURRENCY),
                        BigDecimal.valueOf(0.50), 200, BigDecimal.valueOf(100.25))),

            new TradingInstruction(TradingConstants.ENTITY_BAR, TradeAction.BUY, LocalDate.of(2017, 8, 11),
                  LocalDate.of(2017, 8, 20),
                  new TradingRequest(Currency.getInstance("AED"), BigDecimal.valueOf(0.22), 450, BigDecimal.valueOf(150.5))),

            new TradingInstruction(TradingConstants.ENTITY_EJ, TradeAction.SELL, LocalDate.of(2017, 8, 11),
                  LocalDate.of(2017, 8, 19),
                  new TradingRequest(Currency.getInstance("SAR"), BigDecimal.valueOf(0.27), 150, BigDecimal.valueOf(400.8))),

            new TradingInstruction(TradingConstants.ENTITY_HCL, TradeAction.SELL, LocalDate.of(2017, 8, 11),
                  LocalDate.of(2017, 8, 22), new TradingRequest(Currency.getInstance(TradingConstants.EUROCURRENCY),
                        BigDecimal.valueOf(0.34), 50, BigDecimal.valueOf(500.6))),

            new TradingInstruction(TradingConstants.ENTITY_SAP, TradeAction.BUY, LocalDate.of(2017, 8, 11),
                  LocalDate.of(2017, 8, 22), new TradingRequest(Currency.getInstance(TradingConstants.EUROCURRENCY),
                        BigDecimal.valueOf(0.34), 20, BigDecimal.valueOf(40.6))),

            new TradingInstruction(TradingConstants.ENTITY_GL, TradeAction.BUY, LocalDate.of(2017, 8, 11),
                  LocalDate.of(2017, 8, 22), new TradingRequest(Currency.getInstance(TradingConstants.EUROCURRENCY),
                        BigDecimal.valueOf(0.34), 20, BigDecimal.valueOf(40.6))),

            new TradingInstruction(TradingConstants.ENTITY_PTR, TradeAction.SELL, LocalDate.of(2017, 8, 11),
                  LocalDate.of(2017, 8, 22), new TradingRequest(Currency.getInstance(TradingConstants.EUROCURRENCY),
                        BigDecimal.valueOf(0.34), 1000, BigDecimal.valueOf(160.6))),

            new TradingInstruction(TradingConstants.ENTITY_JP, TradeAction.SELL, LocalDate.of(2017, 8, 11),
                  LocalDate.of(2017, 8, 22), new TradingRequest(Currency.getInstance(TradingConstants.EUROCURRENCY),
                        BigDecimal.valueOf(0.34), 120, BigDecimal.valueOf(500.6)))));
   }
}

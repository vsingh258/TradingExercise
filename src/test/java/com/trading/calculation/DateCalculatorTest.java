package com.trading.calculation;


import static junit.framework.TestCase.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import com.trading.input.TradeAction;
import com.trading.input.TradingInstruction;
import com.trading.input.TradingRequest;
import com.trading.utils.TradingConstants;


public class DateCalculatorTest {


   @Test
   public void calculateSettlementDate_default_Friday() throws Exception {
      final LocalDate initialSettlementDate = LocalDate.of(2017, 8, 24);

      final TradingInstruction sampleInstruction = new TradingInstruction(TradingConstants.ENTITY_FOO, TradeAction.BUY, LocalDate.of(2017, 8, 10),
            initialSettlementDate, new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY), BigDecimal.valueOf(0.50), 200,
                  BigDecimal.valueOf(150.50)));

      DateCalculator.calculateSettlementDate(sampleInstruction);
      assertEquals(initialSettlementDate, sampleInstruction.getSettlementDate());
   }

   @Test
   public void calculateSettlementDate_default_Sunday() throws Exception {
      final LocalDate initialSettlementDate = LocalDate.of(2017, 8, 27);

      final TradingInstruction sampleInstruction = new TradingInstruction(TradingConstants.ENTITY_FOO, TradeAction.BUY, LocalDate.of(2017, 8, 10),
            initialSettlementDate,
            new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY), BigDecimal.valueOf(1), 200, BigDecimal.valueOf(150.50)));

      DateCalculator.calculateSettlementDate(sampleInstruction);


      assertEquals(LocalDate.of(2017, 8, 28), sampleInstruction.getSettlementDate());
   }

   @Test
   public void calculateSettlementDate_arabia_Friday() throws Exception {
      final LocalDate initialSettlementDate = LocalDate.of(2017, 8, 27);

      final TradingInstruction sampleInstruction = new TradingInstruction(TradingConstants.ENTITY_FOO, TradeAction.BUY, LocalDate.of(2017, 8, 10),
            initialSettlementDate,
            new TradingRequest(Currency.getInstance(TradingConstants.ARABICCURRENCY), BigDecimal.valueOf(0.50), 200, BigDecimal.valueOf(150.50)));

      DateCalculator.calculateSettlementDate(sampleInstruction);

      assertEquals(LocalDate.of(2017, 8, 27), sampleInstruction.getSettlementDate());
   }

   @Test
   public void calculateSettlementDate_arabia_Sunday() throws Exception {
      final LocalDate initialSettlementDate = LocalDate.of(2017, 8, 27);

      final TradingInstruction sampleInstruction = new TradingInstruction(TradingConstants.ENTITY_FOO, TradeAction.BUY, LocalDate.of(2017, 8, 10),
            initialSettlementDate, new TradingRequest(Currency.getInstance(TradingConstants.SAUDIARABIACURRENCY), BigDecimal.valueOf(0.50), 200,
                  BigDecimal.valueOf(150.50)));


      DateCalculator.calculateSettlementDate(sampleInstruction);

      assertEquals(initialSettlementDate, sampleInstruction.getSettlementDate());
   }
}

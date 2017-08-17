package com.trading.calculation;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.Test;

import com.trading.data.Rank;
import com.trading.input.TradeAction;
import com.trading.input.TradingInstruction;
import com.trading.input.TradingRequest;
import com.trading.utils.TradingConstants;


/**
 * @author vikramjitsingh
 *
 */
public class TradingCalculatorTest {


   private static Set<TradingInstruction> getSampleSetOfInstructions() {
      final Set<TradingInstruction> instructions = new HashSet<>();

      instructions.add(new TradingInstruction(TradingConstants.ENTITY_FOO, TradeAction.BUY, TradingConstants.MONDAY,
            TradingConstants.MONDAY, new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY),
                  TradingConstants.DEFAULT_AGREED_FX, 100, TradingConstants.DEFAULT_PRICE_PER_UNIT)));

      instructions.add(new TradingInstruction(TradingConstants.ENTITY_BAR, TradeAction.BUY, TradingConstants.MONDAY,
            TradingConstants.MONDAY, new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY),
                  TradingConstants.DEFAULT_AGREED_FX, 200, TradingConstants.DEFAULT_PRICE_PER_UNIT)));

      instructions.add(new TradingInstruction(TradingConstants.ENTITY_EJ, TradeAction.BUY, TradingConstants.MONDAY,
            TradingConstants.SATURDAY, new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY),
                  TradingConstants.DEFAULT_AGREED_FX, 300, TradingConstants.DEFAULT_PRICE_PER_UNIT)));

      instructions.add(new TradingInstruction(TradingConstants.ENTITY_HCL, TradeAction.SELL, TradingConstants.MONDAY,
            TradingConstants.SUNDAY, new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY),
                  TradingConstants.DEFAULT_AGREED_FX, 200, TradingConstants.DEFAULT_PRICE_PER_UNIT)));


      instructions.add(new TradingInstruction(TradingConstants.ENTITY_SAP, TradeAction.BUY, TradingConstants.MONDAY,
            TradingConstants.TUESDAY, new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY),
                  TradingConstants.DEFAULT_AGREED_FX, 400, TradingConstants.DEFAULT_PRICE_PER_UNIT)));

      instructions.add(new TradingInstruction(TradingConstants.ENTITY_GL, TradeAction.SELL, TradingConstants.MONDAY,
            TradingConstants.TUESDAY, new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY),
                  TradingConstants.DEFAULT_AGREED_FX, 1000, TradingConstants.DEFAULT_PRICE_PER_UNIT)));


      instructions.add(new TradingInstruction(TradingConstants.ENTITY_PTR, TradeAction.BUY, TradingConstants.MONDAY,
            TradingConstants.WEDNESDAY, new TradingRequest(Currency.getInstance(TradingConstants.SINGAPORECURRENCY),
                  TradingConstants.DEFAULT_AGREED_FX, 7000, TradingConstants.DEFAULT_PRICE_PER_UNIT)));
   
      DateCalculator.calculateSettlementDates(instructions);

      return instructions;
   }

   @Test
   public void testDailyIncomingAmount() throws Exception {
      final Map<LocalDate, BigDecimal> dailyIncomingAmount = TradingCalculator
            .calculateDailyIncomingAmount(getSampleSetOfInstructions());

      assertEquals(2, dailyIncomingAmount.size());
      assertTrue(Objects.equals(dailyIncomingAmount.get(TradingConstants.TUESDAY),
            BigDecimal.valueOf(1000.00).setScale(TradingConstants.DEFAULT_SCALE, BigDecimal.ROUND_HALF_EVEN)));
   }

   @Test
   public void testDailyOutgoingAmount() throws Exception {
      final Map<LocalDate, BigDecimal> dailyOutgoingAmount = TradingCalculator
            .calculateDailyOutgoingAmount(getSampleSetOfInstructions());

      assertEquals(4, dailyOutgoingAmount.size());
      assertTrue(Objects.equals(dailyOutgoingAmount.get(TradingConstants.TUESDAY),
            BigDecimal.valueOf(400.00).setScale(TradingConstants.DEFAULT_SCALE, BigDecimal.ROUND_HALF_EVEN)));
   }

   @Test
   public void testDailyIncomingRanking() throws Exception {
      final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking = TradingCalculator
            .calculateDailyIncomingRanking(getSampleSetOfInstructions());

      assertEquals(2, dailyIncomingRanking.size());

      //assertEquals(1, dailyIncomingRanking.get(TradingConstants.MONDAY).size());
      assertEquals(1, dailyIncomingRanking.get(TradingConstants.TUESDAY).size());

//      assertTrue(dailyIncomingRanking.get(TradingConstants.MONDAY)
//            .contains(new Rank(1, TradingConstants.ENTITY_HCL, TradingConstants.MONDAY)));
      assertTrue(dailyIncomingRanking.get(TradingConstants.TUESDAY)
            .contains(new Rank(1, TradingConstants.ENTITY_GL, TradingConstants.TUESDAY)));

   }

   @Test
   public void testDailyOutgoingRanking() throws Exception {
      final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking = TradingCalculator
            .calculateDailyOutgoingRanking(getSampleSetOfInstructions());

      assertEquals(4, dailyOutgoingRanking.size());

      assertEquals(2, dailyOutgoingRanking.get(TradingConstants.MONDAY).size());
      assertEquals(1, dailyOutgoingRanking.get(TradingConstants.TUESDAY).size());
      assertEquals(1, dailyOutgoingRanking.get(TradingConstants.WEDNESDAY).size());

      assertTrue(dailyOutgoingRanking.get(TradingConstants.MONDAY)
            .contains(new Rank(2, TradingConstants.ENTITY_FOO, TradingConstants.MONDAY)));
//      assertTrue(dailyOutgoingRanking.get(TradingConstants.MONDAY)
//            .contains(new Rank(3, TradingConstants.ENTITY_BAR, TradingConstants.MONDAY)));

      assertTrue(dailyOutgoingRanking.get(TradingConstants.TUESDAY)
            .contains(new Rank(1, TradingConstants.ENTITY_SAP, TradingConstants.TUESDAY)));

      assertTrue(dailyOutgoingRanking.get(TradingConstants.WEDNESDAY)
            .contains(new Rank(1, TradingConstants.ENTITY_PTR, TradingConstants.WEDNESDAY)));
   }
}

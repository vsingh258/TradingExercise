package com.trading.calculation;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toSet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import com.trading.data.Rank;
import com.trading.input.TradeAction;
import com.trading.input.TradingInstruction;

import static java.util.stream.Collectors.*;


/**
 * @author vikramjitsingh
 *
 */
public class TradingCalculator {


   private static final Predicate<TradingInstruction> outgoingInstructionsPredicate = instruction -> instruction.getAction()
         .equals(TradeAction.BUY);
   private static final Predicate<TradingInstruction> incomingInstructionsPredicate = instruction -> instruction.getAction()
         .equals(TradeAction.SELL);

   /**
   * @param instructions
   * @return
   */
   public static Map<LocalDate, BigDecimal> calculateDailyOutgoingAmount(Set<TradingInstruction> instructions) {
      return calculateDailyAmount(instructions, outgoingInstructionsPredicate);
   }

   /**
   * @param instructions
   * @return
   */
   public static Map<LocalDate, BigDecimal> calculateDailyIncomingAmount(Set<TradingInstruction> instructions) {
      return calculateDailyAmount(instructions, incomingInstructionsPredicate);
   }


   /**
   * @param instructions
   * @return
   */
   public static Map<LocalDate, LinkedList<Rank>> calculateDailyOutgoingRanking(Set<TradingInstruction> instructions) {
      return calculateRanking(instructions, outgoingInstructionsPredicate);
   }


   /**
   * @param instructions
   * @return
   */
   public static Map<LocalDate, LinkedList<Rank>> calculateDailyIncomingRanking(Set<TradingInstruction> instructions) {
      return calculateRanking(instructions, incomingInstructionsPredicate);
   }

   /**
   * @param instructions
   * @param predicate
   * @return
   */
   private static Map<LocalDate, BigDecimal> calculateDailyAmount(Set<TradingInstruction> instructions,
         Predicate<TradingInstruction> predicate) {
      return instructions.stream().filter(predicate).collect(groupingBy(TradingInstruction::getSettlementDate,
            mapping(TradingInstruction::getTradeAmount, reducing(BigDecimal.ZERO, BigDecimal::add))));
   }

   /**
   * @param instructions
   * @param predicate
   * @return
   */
   private static Map<LocalDate, LinkedList<Rank>> calculateRanking(Set<TradingInstruction> instructions,
         Predicate<TradingInstruction> predicate) {
      final Map<LocalDate, LinkedList<Rank>> ranking = new HashMap<>();

      instructions.stream().filter(predicate).collect(groupingBy(TradingInstruction::getSettlementDate, toSet()))
            .forEach((date, dailyInstructionSet) -> {
               final AtomicInteger rank = new AtomicInteger(1);

               final LinkedList<Rank> ranks = dailyInstructionSet.stream()
                     .sorted((ins1, ins2) -> ins2.getTradeAmount().compareTo(ins1.getTradeAmount()))
                     .map(instruction -> new Rank(rank.getAndIncrement(), instruction.getEntity(), date))
                     .collect(toCollection(LinkedList::new));

               ranking.put(date, ranks);
            });

      return ranking;
   }
}

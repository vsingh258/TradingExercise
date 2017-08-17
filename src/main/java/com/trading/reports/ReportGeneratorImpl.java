package com.trading.reports;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.trading.calculation.DateCalculator;
import com.trading.calculation.TradingCalculator;
import com.trading.data.Rank;
import com.trading.input.TradingInstruction;


/**
 * @author vikramjitsingh
 *
 */
public class ReportGeneratorImpl implements ReportGenerator {
   private StringBuilder reportOutPut = new StringBuilder();

   @Override
   public String generateInstructionsReport(Set<TradingInstruction> instructions) {
      DateCalculator.calculateSettlementDates(instructions);

      return generateDailyOutgoingRanking(instructions,
            generateDailyIncomingRanking(instructions,
                  generateDailyIncomingAmount(instructions, generateDailyOutgoingAmount(instructions, reportOutPut))))
                        .toString();
   }

   /**
   * @param instructions
   * @param reportOutPut
   * @return
   */
   private static StringBuilder generateDailyOutgoingAmount(Set<TradingInstruction> instructions, StringBuilder reportOutPut) {
      final Map<LocalDate, BigDecimal> dailyOutgoingAmount = TradingCalculator.calculateDailyOutgoingAmount(instructions);

      reportOutPut.append("\n----------------------------------------\n").append("         Outgoing Daily Amount          \n")
            .append("----------------------------------------\n").append("      Date       |    Trade Amount      \n")
            .append("----------------------------------------\n");

      for (LocalDate date : dailyOutgoingAmount.keySet()) {
         reportOutPut.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
      }

      return reportOutPut;
   }

   /**
   * @param instructions
   * @param reportOutPut
   * @return
   */
   private static StringBuilder generateDailyIncomingAmount(Set<TradingInstruction> instructions, StringBuilder reportOutPut) {
      final Map<LocalDate, BigDecimal> dailyOutgoingAmount = TradingCalculator.calculateDailyIncomingAmount(instructions);

      reportOutPut.append("\n----------------------------------------\n").append("         Incoming Daily Amount          \n")
            .append("----------------------------------------\n").append("      Date       |    Trade Amount      \n")
            .append("----------------------------------------\n");

      for (LocalDate date : dailyOutgoingAmount.keySet()) {
         reportOutPut.append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
      }

      return reportOutPut;
   }

   /**
   * @param instructions
   * @param reportOutPut
   * @return
   */
   private static StringBuilder generateDailyOutgoingRanking(Set<TradingInstruction> instructions, StringBuilder reportOutPut) {
      final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking = TradingCalculator.calculateDailyOutgoingRanking(instructions);

      reportOutPut.append("\n----------------------------------------\n").append("         Outgoing Daily Ranking          \n")
            .append("----------------------------------------\n").append("     Date    |     Rank   |   Entity     \n")
            .append("----------------------------------------\n");

      for (LocalDate date : dailyOutgoingRanking.keySet()) {
         for (Rank rank : dailyOutgoingRanking.get(date)) {
            reportOutPut.append(date).append("   |      ").append(rank.getRank()).append("     |    ").append(rank.getEntity())
                  .append("\n");
         }
      }

      return reportOutPut;
   }

   /**
   * @param instructions
   * @param reportOutPut
   * @return
   */
   private static StringBuilder generateDailyIncomingRanking(Set<TradingInstruction> instructions, StringBuilder reportOutPut) {
      final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking = TradingCalculator.calculateDailyIncomingRanking(instructions);

      reportOutPut.append("\n----------------------------------------\n").append("         Incoming Daily Ranking          \n")
            .append("----------------------------------------\n").append("     Date    |     Rank   |   Entity     \n")
            .append("----------------------------------------\n");

      for (LocalDate date : dailyIncomingRanking.keySet()) {
         for (Rank rank : dailyIncomingRanking.get(date)) {
            reportOutPut.append(date).append("   |      ").append(rank.getRank()).append("     |    ").append(rank.getEntity())
                  .append("\n");
         }
      }

      return reportOutPut;
   }
}

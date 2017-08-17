package com.trading.calculation;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import com.trading.input.TradingInstruction;
import com.trading.utils.TradingConstants;
import com.trading.utils.date.DefaultWorkingDays;
import com.trading.utils.date.ArabiaWorkingDays;
import com.trading.utils.date.WorkingDays;


/**
 * @author vikramjitsingh
 *
 */
public class DateCalculator {
   

   /**
   * @param instructions
   */
   public static void calculateSettlementDates(Set<TradingInstruction> instructions) {
      instructions.forEach(DateCalculator::calculateSettlementDate);
   }

   /**
   * @param instruction
   */
   public static void calculateSettlementDate(TradingInstruction instruction) {
      final WorkingDays workingDays = getWorkingDaysStrategy(instruction.getCurrency());

      final LocalDate newSettlementDate = workingDays.getFirstWorkingDate(instruction.getSettlementDate());

      if (newSettlementDate != null) {
         instruction.setSettlementDate(newSettlementDate);
      }
   }

   /**
   * @param currency
   * @return
   */
   private static WorkingDays getWorkingDaysStrategy(Currency currency) {
      if ((currency.getCurrencyCode().equals(TradingConstants.ARABICCURRENCY)) || (currency.getCurrencyCode().equals(TradingConstants.SAUDIARABIACURRENCY))) {
         return ArabiaWorkingDays.getInstance();
      }
      return DefaultWorkingDays.getInstance();
   }
}

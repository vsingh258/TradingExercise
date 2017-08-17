package com.trading.utils.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/**
 * @author vikramjitsingh
 *
 */
public abstract class WorkingDaysImpl implements WorkingDays {
   protected Map<DayOfWeek, Boolean> isWorkingDay = new HashMap<>();
   
   /**
   * 
   */
   public WorkingDaysImpl() {
      setupWorkingDays();
   }


   protected abstract void setupWorkingDays();

   /* (non-Javadoc)
    * @see com.trading.utils.date.WorkingDays#findFirstWorkingDate(java.time.LocalDate)
    */
   public LocalDate getFirstWorkingDate(LocalDate date) {

      if (isWorkingDay.values().stream().noneMatch(day -> day)) {
         return null;
      }

      return searchFirstWorkingDate(date);
   }


   /**
    * @param date
    * @return
    */
   private LocalDate searchFirstWorkingDate(LocalDate date) {
      final DayOfWeek inputDay = date.getDayOfWeek();
      if (isWorkingDay.get(inputDay)) {
         return date;
      }
      else {
         return searchFirstWorkingDate(date.plusDays(1));
      }
   }
}

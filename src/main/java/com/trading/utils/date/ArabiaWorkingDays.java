package com.trading.utils.date;

import java.time.DayOfWeek;


/**
 * @author vikramjitsingh
 *
 */
public class ArabiaWorkingDays extends WorkingDaysImpl {

   private static ArabiaWorkingDays instance = null;
   /**
   * 
   */
   private ArabiaWorkingDays() {
      super();
   }

   /**
   * @return
   */
   public static ArabiaWorkingDays getInstance() {
      if (instance == null) {
         instance = new ArabiaWorkingDays();
      }
      return instance;
   }



   @Override
   protected void setupWorkingDays() {
      this.isWorkingDay.put(DayOfWeek.SUNDAY, true);
      this.isWorkingDay.put(DayOfWeek.MONDAY, true);
      this.isWorkingDay.put(DayOfWeek.TUESDAY, true);
      this.isWorkingDay.put(DayOfWeek.WEDNESDAY, true);
      this.isWorkingDay.put(DayOfWeek.THURSDAY, true);
      this.isWorkingDay.put(DayOfWeek.FRIDAY, false); 
      this.isWorkingDay.put(DayOfWeek.SATURDAY, false); 
   }
}

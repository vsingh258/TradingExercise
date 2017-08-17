package com.trading.utils.date;

import java.time.DayOfWeek;


/**
 * @author vikramjitsingh
 *
 */
public class DefaultWorkingDays extends WorkingDaysImpl {

   private static DefaultWorkingDays instance = null;

   /**
   * 
   */
   private DefaultWorkingDays() {
      super();
   }

   /**
   * @return
   */
   public static DefaultWorkingDays getInstance() {
      if (instance == null) {
         instance = new DefaultWorkingDays();
      }
      return instance;
   }

   @Override
   protected void setupWorkingDays() {
      this.isWorkingDay.put(DayOfWeek.MONDAY, true);
      this.isWorkingDay.put(DayOfWeek.TUESDAY, true);
      this.isWorkingDay.put(DayOfWeek.WEDNESDAY, true);
      this.isWorkingDay.put(DayOfWeek.THURSDAY, true);
      this.isWorkingDay.put(DayOfWeek.FRIDAY, true);
      this.isWorkingDay.put(DayOfWeek.SATURDAY, false);
      this.isWorkingDay.put(DayOfWeek.SUNDAY, false);
   }
}

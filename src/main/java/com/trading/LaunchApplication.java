package com.trading;

import java.util.Set;

import com.trading.input.SampleClientRequests;
import com.trading.input.TradingInstruction;
import com.trading.reports.ReportGenerator;
import com.trading.reports.ReportGeneratorImpl;


/**
 * @author vikramjitsingh
 *
 */
public class LaunchApplication {

   public static void main(String[] args) {
      final Set<TradingInstruction> instructions = SampleClientRequests.getSampleRequests();
      final ReportGenerator reportGenerator = new ReportGeneratorImpl();
      System.out.println(reportGenerator.generateInstructionsReport(instructions));
   }
}

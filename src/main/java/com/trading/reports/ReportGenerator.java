package com.trading.reports;

import java.util.Set;

import com.trading.input.TradingInstruction;


/**
 * @author vikramjitsingh
 *
 */
public interface ReportGenerator {
   String generateInstructionsReport(Set<TradingInstruction> instructions);
}

package com.trading.input;

/**
 * @author vikramjitsingh
 *
 */
public enum TradeAction {
   BUY("B"), SELL("S");

   private String name;

   TradeAction(String name) {
      this.name = name;
   }

   public String getText() {
      return this.name;
   }


}

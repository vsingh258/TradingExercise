package com.trading.utils;

import java.math.BigDecimal;
import java.time.LocalDate;


public class TradingConstants {

   public static final String ARABICCURRENCY = "AED";

   public static final String SAUDIARABIACURRENCY = "SAR";
   public static final String SINGAPORECURRENCY = "SGD";
   public static final String EUROCURRENCY = "EUR";

   public static final String ENTITY_FOO = "foo";
   public static final String ENTITY_BAR = "bar";

   public static final String ENTITY_EJ = "EJ";
   public static final String ENTITY_HCL = "HCL";
   public static final String ENTITY_SAP = "SAP";
   public static final String ENTITY_GL = "GL";
   public static final String ENTITY_PTR = "PTR";
   public static final String ENTITY_JP = "JP";

   public static final LocalDate MONDAY = LocalDate.of(2017, 8, 14);
   public static final LocalDate TUESDAY = LocalDate.of(2017, 8, 15);
   public static final LocalDate WEDNESDAY = LocalDate.of(2017, 8, 16);
   public static final LocalDate SATURDAY = LocalDate.of(2017, 8, 17);
   public static final LocalDate SUNDAY = LocalDate.of(2017, 8, 18);

   public static final int DEFAULT_SCALE = 2;

   public static final BigDecimal DEFAULT_PRICE_PER_UNIT = BigDecimal.valueOf(1);
   public static final BigDecimal DEFAULT_AGREED_FX = BigDecimal.valueOf(1);

}

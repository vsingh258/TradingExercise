package com.trading.utils.date;

import java.time.LocalDate;

public interface WorkingDays {
    LocalDate getFirstWorkingDate(LocalDate date);
}

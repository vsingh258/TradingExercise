package com.trading.days;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.trading.utils.date.ArabiaWorkingDays;
import com.trading.utils.date.WorkingDays;


/**
 * @author vikramjitsingh
 *
 */
public class ArabiaWorkingDaysTest {
    private WorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = ArabiaWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate sunday = LocalDate.of(2017, 8, 27);

        assertEquals(sunday, workingDays.getFirstWorkingDate(sunday));
    }

    @Test
    public void testFindFirstWorkingDate_Thursday() throws Exception {
        final LocalDate thursday = LocalDate.of(2017, 8, 24);

        assertEquals(thursday, workingDays.getFirstWorkingDate(thursday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate friday = LocalDate.of(2017, 8, 25);

     }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate saturday = LocalDate.of(2017, 8, 27);
        assertEquals(LocalDate.of(2017, 8, 27), workingDays.getFirstWorkingDate(saturday));
    }

}
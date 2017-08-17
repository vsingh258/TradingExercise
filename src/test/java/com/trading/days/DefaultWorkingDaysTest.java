package com.trading.days;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.trading.utils.date.DefaultWorkingDays;
import com.trading.utils.date.WorkingDays;

/**
 * @author vikramjitsingh
 *
 */
public class DefaultWorkingDaysTest {

    private WorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = DefaultWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Monday() throws Exception {
        final LocalDate monday = LocalDate.of(2017, 8, 21);

        assertEquals(monday, workingDays.getFirstWorkingDate(monday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate friday = LocalDate.of(2017, 8, 25);

        assertEquals(friday, workingDays.getFirstWorkingDate(friday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate saturday = LocalDate.of(2017, 8, 26);
        assertEquals(LocalDate.of(2017, 8, 28), workingDays.getFirstWorkingDate(saturday));
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate sunday = LocalDate.of(2017, 8, 27);

        assertEquals(LocalDate.of(2017, 8, 28), workingDays.getFirstWorkingDate(sunday));
    }
}
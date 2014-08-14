package com.tieto.it2014.domain;

import com.tieto.it2014.domain.Util.Util;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestUtil {

    @Test
    public void test_0_seconds_to_duration_string() {
        assertEquals(Util.getDurationString(0), "00 h. 00 min.");
    }

    @Test
    public void test_minus_seconds_to_duration_string() {
        assertEquals(Util.getDurationString(-1), "00 h. 00 min.");
    }

    @Test
    public void test_positive_seconds_to_duration_string() {
        assertEquals(Util.getDurationString(1), "00 h. 01 min.");
    }

    @Test
    public void rounds_correctly() {
        assertEquals(Util.getDurationString(59), "00 h. 01 min.");
        assertEquals(Util.getDurationString(60), "00 h. 01 min.");
        assertEquals(Util.getDurationString(61), "00 h. 02 min.");
        assertEquals(Util.getDurationString(3600 - 1), "01 h. 00 min.");
    }
}

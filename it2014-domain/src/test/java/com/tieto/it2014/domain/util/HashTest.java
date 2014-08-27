package com.tieto.it2014.domain.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashTest {

    @Test
    public void generatesHashCorrectly() {
        String test = "testhash";
        String correct = "4bc75035d73f6083683e040fc31f28e0ec6d1cbce5cb0a5e2611eb89bceb6c16";
        test = Hash.sha256(test);
        assertEquals(test, correct);
    }

}

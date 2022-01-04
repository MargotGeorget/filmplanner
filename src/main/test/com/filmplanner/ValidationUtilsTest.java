package com.filmplanner;

import org.junit.jupiter.api.Test;
import utils.ValidationUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilsTest {

    @Test
    void isEmailTest(){
        boolean test1 = ValidationUtils.isEmail("aa");
        assertTrue(!test1);
        boolean test2 = ValidationUtils.isEmail("nathan@gmail.com");
        assertTrue(test2);
        boolean test3 = ValidationUtils.isEmail("aakk@gam");
        assertTrue(!test3);
        boolean test4 = ValidationUtils.isEmail("nat-han@gmail.com");
        assertTrue(test4);
     }

    @Test
    void isPhoneNumberTest(){
        boolean test1 = ValidationUtils.isPhoneNumber("aa");
        assertTrue(!test1);
        boolean test2 = ValidationUtils.isPhoneNumber("1234567891");
        assertTrue(test2);
        boolean test3 = ValidationUtils.isPhoneNumber("34");
        assertTrue(!test3);
    }

    @Test
    void isStringWithoutSpecialSymbolTest(){
        boolean test1 = ValidationUtils.isStringWithoutSpecialSymbol("aaaa[aa");
        assertTrue(!test1);
        boolean test2 = ValidationUtils.isStringWithoutSpecialSymbol("ohdmvdf");
        assertTrue(test2);
        boolean test3 = ValidationUtils.isStringWithoutSpecialSymbol("aakk@gam");
        assertTrue(!test3);
        boolean test4 = ValidationUtils.isStringWithoutSpecialSymbol("nm-fgf");
        assertTrue(test4);
    }

    @Test
    void isDateTest(){
        boolean test1 = ValidationUtils.isDate("aa");
        assertTrue(!test1);
        boolean test2 = ValidationUtils.isDate("12/12/2022");
        assertTrue(test2);
        boolean test3 = ValidationUtils.isDate("34");
        assertTrue(!test3);
        boolean test4 = ValidationUtils.isDate("34/12/2022");
        assertTrue(!test4);
        boolean test5 = ValidationUtils.isDate("1/23/2023");
        assertTrue(!test5);
    }


}

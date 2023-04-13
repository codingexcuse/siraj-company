package com.cloverbaytechnologies.companies.controller;

//public class IdValidator {
//}
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;

public class IdValidator  {
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final int ID_LENGTH = 6;
    private static final int ALPHA_CAPS_COUNT = 3;
    private static final int NUMBERS_COUNT = 3;


    public static boolean isValidId(String id) {
        if (id == null || id.length() != ID_LENGTH) {
            return false;
        }

        int alphaCapsCount = 0;
        int numbersCount = 0;

        for (char c : id.toCharArray()) {
            if (ALPHA_CAPS.indexOf(c) != -1) {
                alphaCapsCount++;
            } else if (NUMBERS.indexOf(c) != -1) {
                numbersCount++;
            } else {
                return false; // non-alphanumeric character found
            }
        }

        return alphaCapsCount == ALPHA_CAPS_COUNT && numbersCount == NUMBERS_COUNT;
    }
}

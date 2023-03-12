package com.lionword.mainservice.entity.util;

import com.lionword.mainservice.apierror.ExceptionTemplates;

import java.time.LocalDateTime;
import java.util.Arrays;

public class InputValidator {

    public static void checkDateInput(String...datesToParse) {
        try {
            Arrays.stream(datesToParse)
                    .map(s -> LocalDateTime.parse(s, TimeFormatter.DEFAULT))
                    .toArray();
        } catch (RuntimeException e) {
            ExceptionTemplates.dateParse();
        }

    }

}


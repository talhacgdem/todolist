package com.talhacgdem.todolist.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class Util {

    public static class DateUtil{
        public static String getLocatedDateString(LocalDate date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy, EEEE");
            return date.format(formatter);
        }

        public static LocalDate getFirstDayOfWeekFromThisWeek(){
            LocalDate today = LocalDate.now();
            return today.with(ChronoField.DAY_OF_WEEK, 1);
        }

        public static LocalDate getLastDayOfWeekFromThisWeek(){
            return getFirstDayOfWeekFromThisWeek().plusDays(6);
        }
    }

}

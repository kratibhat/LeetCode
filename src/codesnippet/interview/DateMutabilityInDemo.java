package codesnippet.interview;

import jdk.jshell.JShell;

import java.util.Date;
import java.util.concurrent.Flow;

public class DateMutabilityInDemo {
    public static void main(String[] args) {
        java.util.Date date = new java.util.Date();
        System.out.println("Original Date: " + date);


        // Modifying the date object
        date.setTime(date.getTime() + 1000000000L);
        // Add 1 billion milliseconds
        System.out.println("Modified Date: " + date);

        // Demonstrating immutability with java.time.LocalDate =======
        java.time.LocalDate localDate = java.time.LocalDate.now();
        System.out.println("Original LocalDate: " + localDate);

        // Attempting to modify LocalDate (it returns a new instance)
        java.time.LocalDate modifiedLocalDate = localDate.plusDays(10);
        System.out.println("Modified LocalDate: " + modifiedLocalDate);
        System.out.println("Original LocalDate after modification attempt: " + localDate);
    }
}

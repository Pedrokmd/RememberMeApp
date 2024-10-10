package com.example.remembermesetup;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Utils {
    private static final String dateFormat = "dd/MM/yyyy";

    /**
     * Check if a string is a number
     *
     * @param number the string to check
     * @return true if the string is a number, false otherwise
     */
    public static boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if a number is within a range of [min, max], inclusive
     *
     * @param number the number to check
     * @param min    the minimum value
     * @param max    the maximum value
     * @return true if the number is within the range, false otherwise
     */
    public static boolean inRange(double number, double min, double max) {
        return number >= min && number <= max;
    }

    /**
     * Show a popup message
     *
     * @param context      the context to display the popup
     * @param errorMessage the message to display
     */
    public static void showPopup(Context context, String errorMessage) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public static boolean isStringValid(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getId() {
        // 4 digit random number
        return String.valueOf((int) (Math.random() * 9000) + 1000);
    }

}

package ru.startandroid.simplecalculator.exception;

public class DivideByZeroException extends Exception {
    public static final String DIVIDE_BY_ZERO_EXCEPTION = "Невозможно разделить  на ноль";
    public DivideByZeroException(String message) {
        super(message);
    }
}
package ru.startandroid.simplecalculator.exception;

public class BracketsMissmatchException extends Exception {
    public static final String BRACKETS_MISSMATCH_EXCEPTION = "Количество скобок '(' не совпадает с ')";
    public BracketsMissmatchException(String message) {
        super(message);
    }
}
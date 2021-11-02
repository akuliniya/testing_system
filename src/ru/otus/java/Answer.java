package ru.otus.java;

import java.util.Scanner;

public class Answer {
    private final int answerNumber;
    private String answerText;

    public Answer(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Answer(int answerNumber, String answerText) {
        this.answerNumber = answerNumber;
        this.answerText = answerText;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public String getAnswerText() {
        return answerText;
    }
}

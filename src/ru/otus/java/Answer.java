package ru.otus.java;

import java.util.Scanner;

public class Answer {
    public static final String INCORRECT_ANSWER_NUMBER = "Вы ввели несуществующий номер ответа. Начните заново";
    public static final String INCORRECT_INPUT = "Введено некорректное значение";

    private final int answerNumber;
    private String answerText;

    public Answer(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Answer(int answerNumber, String answerText) {
        this.answerNumber = answerNumber;
        this.answerText = answerText;
    }

    public static boolean isAnswerInRange(int selectedAnswerNumber, int answersArrayLength) {
        return (selectedAnswerNumber <= answersArrayLength) && (selectedAnswerNumber > 0);
    }

    public static int readAnswer() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public String getAnswerText() {
        return answerText;
    }
}

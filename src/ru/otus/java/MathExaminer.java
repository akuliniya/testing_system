package ru.otus.java;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MathExaminer {
    private static final String[] QUESTIONS_FOR_TEST = {"5 + 5 =", "7 * 8 =", "153 / 9 = "};
    private static final int[][] ANSWERS_FOR_QUESTIONS = {
            {15, 10, 15},
            {72, 63, 56, 54, 65},
            {16, 17, 18}
    };
    private static final int[] CORRECT_ANSWERS_INDEXES = {1, 2, 1,};
    private static final String INCORRECT_INPUT = "Введено некорректное значение";
    private static final String INCORRECT_ANSWER_NUMBER = "Вы ввели несуществующий номер ответа.";

    public static void main(String[] args) {
        int[] selectedAnswersIndexes = new int[QUESTIONS_FOR_TEST.length];
        int score;

        System.out.println("Введите номер варианта ответа и нажмите Enter.");

        for (int i = 0; i < QUESTIONS_FOR_TEST.length; i++) {
            System.out.println(QUESTIONS_FOR_TEST[i]);

            for (int j = 0; j < ANSWERS_FOR_QUESTIONS[i].length; j++) {
                System.out.println((j + 1) + ". " + ANSWERS_FOR_QUESTIONS[i][j]);
            }
            System.out.println("Ответ: ");

            int selectedAnswerNumber;
            try {
                selectedAnswerNumber = readAnswer();
                int selectedAnswerIndex = convertNumToIndex(selectedAnswerNumber);
                if (isAnswerInRange(selectedAnswerIndex, ANSWERS_FOR_QUESTIONS[i]))
                    selectedAnswersIndexes[i] = selectedAnswerIndex;
                else
                    System.out.println(INCORRECT_ANSWER_NUMBER);
            }catch (InputMismatchException e) {
                printException(e);
                System.exit(1);
            }
        }

        score = countScore(CORRECT_ANSWERS_INDEXES, selectedAnswersIndexes);

        switch (score) {
            case 3 -> printResult(Result.PERFECT, score);
            case 2 -> printResult(Result.GOOD, score);
            case 1 -> printResult(Result.SATISFACTORY, score);
            case 0 -> printResult(Result.BAD, score);
            default -> System.out.println("Неизвестная ошибка. Такого количества баллов вы не могли набрать ["+ score + "]./n" +
                        "Попробуйте пройти тест еще раз.");
        }

        if (isTestPassed(CORRECT_ANSWERS_INDEXES, selectedAnswersIndexes))
            System.out.println("Тест пройден. Вы ответили верно на все вопросы!");
        else {
            System.out.println("В ответах есть ошибки. Попробуйте еще раз.");
            System.out.println("Показать детали? (1 - 'Да', 0 - 'Нет')");
            try {
                int isNeedMoreDetails = readAnswer();
                switch (isNeedMoreDetails) {
                    case 1 -> showDetails(CORRECT_ANSWERS_INDEXES, selectedAnswersIndexes);
                    case 0 -> System.exit(0);
                    default -> System.out.println(INCORRECT_INPUT);
                }
            } catch (InputMismatchException e) {
                printException(e);
                System.exit(1);
            }
        }
    }

    private static int readAnswer() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

     private static void showDetails(int[] correctAnswersIndexes, int[] selectedAnswersIndexes) {
        for (int i = 0; i < correctAnswersIndexes.length; i++){
            System.out.println("Правильный ответ: "  +  convertIndexToNum(correctAnswersIndexes[i])
                    + ". Ваш ответ: " + convertIndexToNum(selectedAnswersIndexes[i]) + ".");
        }
    }

    private static void printResult(Result result, int score) {
       System.out.println("Вы набрали баллов: " + score + ". Ваша оценка: " + result);
    }

    private static int countScore(int[] correctAnswersIndexes, int[] selectedAnswersIndexes){
        int score = 0;

        for (int i = 0; i < correctAnswersIndexes.length; i++) {
                if (correctAnswersIndexes[i] == selectedAnswersIndexes[i])
                    score++;
        }
        return score;
    }

    private static boolean isTestPassed(int[] correctAnswersIndexes, int[] selectedAnswersIndexes) {
        return Arrays.equals(correctAnswersIndexes, selectedAnswersIndexes);
    }

    private static int convertIndexToNum(int index) {
        return ++index;
    }

    private static int convertNumToIndex(int number) {
        return --number;
    }

    private static boolean isAnswerInRange(int answerIndex, int[] answersForQuestion) {
        return (answerIndex < answersForQuestion.length) && (answerIndex >= 0);
    }

    private static void printException(InputMismatchException e) {
        System.out.println("Кажется вы ввели не число. Возникла ошибка " + e);
        e.printStackTrace();
    }

}

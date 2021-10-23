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

    public static void main(String[] args) {
        int score;
        int[] selectedVariantsIndexes = new int[QUESTIONS_FOR_TEST.length];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < QUESTIONS_FOR_TEST.length; i++) {
            System.out.println("Введите номер варианта ответа и нажмите Enter.");
            System.out.println(QUESTIONS_FOR_TEST[i]);
            for (int j = 0; j < ANSWERS_FOR_QUESTIONS[i].length; j++) {
                System.out.println((j + 1) + ". " + ANSWERS_FOR_QUESTIONS[i][j]);
            }
            System.out.println("Ответ: ");
            try {
                selectedVariantsIndexes[i] = scanner.nextInt() - 1;
                // TODO: Добавить проверку на неверный номер ответап
            }catch (InputMismatchException e) {
                System.out.println("Кажется вы ввели не число. Возникла ошибка " + e);
                e.printStackTrace();
                System.exit(1);
            }

        }

        score = countScore(CORRECT_ANSWERS_INDEXES, selectedVariantsIndexes);

        switch (score) {
            case 3:
                printResult(Result.PERFECT, score);
                break;
            case 2:
                printResult(Result.GOOD, score);
                break;
            case 1:
                printResult(Result.SATISFACTORY, score);
                break;
            case 0:
                printResult(Result.BAD, score);
                break;
            default:
                System.out.println("Неизвестная ошибка. Такого количества баллов вы не могли набрать ["+ score + "]./n" +
                        "Попробуйте пройти тест еще раз.");
                break;
        }

        if (isTestPassed(CORRECT_ANSWERS_INDEXES, selectedVariantsIndexes))
            System.out.println("Тест пройден. Вы ответили верно на все вопросы!");
        else {
            System.out.println("В ответах есть ошибки. Попробуйте еще раз.");
            System.out.println("Показать детали? (1 - 'Да', 0 - 'Нет')");
            try {
                int moreDetails = scanner.nextInt();
                if (moreDetails == 1)
                    showDetails(CORRECT_ANSWERS_INDEXES, selectedVariantsIndexes);
                else {
                    if (moreDetails == 0)
                        System.exit(0);
                    else
                        System.out.println("Введено некорректное значение");
                }
            } catch(InputMismatchException e) {
                System.out.println("Кажется вы ввели не число. Возникла ошибка " + e);
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    private static void showDetails(int[] correctAnswersIndexes, int[] selectedVariantsIndexes) {
        for (int i = 0; i < correctAnswersIndexes.length; i++){
            System.out.println("Правильный ответ: "  +  indexToNum(correctAnswersIndexes[i]) + ". Ваш ответ: " + indexToNum(selectedVariantsIndexes[i])+ ".");;
        }
    }

    private static void printResult(Result result, int score) {
       System.out.println("Вы набрали баллов: " + score + ". Ваша оценка: " + result);
    }

    private static int countScore(int[] correctAnswersIndexes, int[] selectedVariantsIndexes){
        int score = 0;

        for (int i = 0; i < correctAnswersIndexes.length; i++) {
                if (correctAnswersIndexes[i] == selectedVariantsIndexes[i])
                    score++;
        }
        return score;
    }

    private static boolean isTestPassed(int[] correctAnswersIndexes, int[] selectedVariantsIndexes) {
        return Arrays.equals(correctAnswersIndexes, selectedVariantsIndexes);
    }

    private static int indexToNum(int index) {
        return index+1;
    }

}

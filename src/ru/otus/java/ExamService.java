package ru.otus.java;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ru.otus.java.Result.countScore;
import static ru.otus.java.Result.printResult;

public class ExamService {
    public static final String EXAM_START_MESSAGE = "%s начинается.%nВведите номер варианта ответа и нажмите Enter.%n";
    public static final String ANSWER_EXPECTATION_MESSAGE = "Ответ: ";
    public static final String EXAM_PASSED_MESSAGE = "Тест пройден. Вы ответили верно на все вопросы!";
    public static final String EXAM_NOT_PASSED_MESSAGE = "В ответах есть ошибки.";
    public static final String ASK_FOR_DETAILS_MESSAGE = "Показать детали? (1 - 'Да', 0 - 'Нет')";
    public static final String EXAM_DETAILS_MESSAGE = "Правильный ответ: %d. Ваш ответ: %d.%n";
    public static final String EXAM_RESULT_MESSAGE = "Вы набрали баллов: %d. Ваша оценка: %s.%n";

    public static final String PRINT_QUESTION_FORMAT = "%d. %s%n";
    public static final String PRINT_ANSWER_FORMAT = "%5d. %s%n";

    public static final String NOT_NUMBER_ENTERED_ERROR = "Кажется вы ввели не число. Возникла ошибка: ";
    public static final String INCORRECT_ANSWER_NUMBER_ERROR = "Вы ввели несуществующий номер ответа. Начните заново";
    public static final String INCORRECT_INPUT_ERROR = "Введено некорректное значение";
    public static final String ESTIMATION_SET_ERROR = "Неизвестная ошибка. Такого количества баллов вы не могли набрать %s!%nПопробуйте пройти тест еще раз.";

    public static void startQuizService(Exam exam) {
        System.out.printf(EXAM_START_MESSAGE, exam.getExamName());

        for (Question question : exam.getQuestionsSet()) {
            System.out.printf(PRINT_QUESTION_FORMAT, question.getQuestionNumber(), question.getQuestionText());

            for (int i = 0; i < question.getAnswersForQuestion().length; i++) {
                System.out.printf(PRINT_ANSWER_FORMAT, question.getAnswersForQuestion()[i].getAnswerNumber(), question.getAnswersForQuestion()[i].getAnswerText());
            }
            System.out.println(ANSWER_EXPECTATION_MESSAGE);

            saveSelectedAnswer(question);
        }
    }

    private static void saveSelectedAnswer(Question question) {
        Answer selectedAnswer;
        try {
            selectedAnswer = new Answer(readSelectedOption());
            if (isAnswerInRange(selectedAnswer.getAnswerNumber(), question.getAnswersForQuestion().length)) {
                question.setSelectedAnswer(selectedAnswer);
            } else{
                System.out.println(INCORRECT_ANSWER_NUMBER_ERROR);
                System.exit(1);
            }
        } catch (InputMismatchException e) {
            printException(e);
            System.exit(1);
        }
    }

    public static void checkResult(Exam exam) {
        Result mathTestResult = new Result();
        mathTestResult.setScore(countScore(exam.getQuestionsSet()));
        mathTestResult.setEstimation(mathTestResult.getScore());
        exam.setExamResult(mathTestResult);
        if (isExamPassed(exam)) {
            System.out.println(EXAM_PASSED_MESSAGE);
            printResult(exam.getExamResult());
        } else {
            printResult(exam.getExamResult());
            System.out.println(EXAM_NOT_PASSED_MESSAGE + " " + ASK_FOR_DETAILS_MESSAGE);
            try {
                int isNeedMoreDetails = readSelectedOption();
                switch (isNeedMoreDetails) {
                    case 1 -> showDetails(exam);
                    case 0 -> System.exit(0);
                    default -> System.err.println(INCORRECT_INPUT_ERROR);
                }
            } catch (InputMismatchException e) {
                printException(e);
                System.exit(1);
            }
        }
    }

    public static int readSelectedOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static boolean isAnswerInRange(int selectedAnswerNumber, int answersArrayLength) {
        return (selectedAnswerNumber <= answersArrayLength) && (selectedAnswerNumber > 0);
    }

    public static void printException(InputMismatchException e) {
        System.err.println(NOT_NUMBER_ENTERED_ERROR + e);
        e.printStackTrace();
    }

    public static boolean isExamPassed(Exam exam) {
        return exam.getQuestionsSet().length == exam.getExamResult().getScore();
    }

    public static void showDetails(Exam exam) {
        for (Question question : exam.getQuestionsSet()) {
            System.out.printf(EXAM_DETAILS_MESSAGE, question.getCorrectAnswer().getAnswerNumber(), question.getSelectedAnswer().getAnswerNumber());
        }
    }
}

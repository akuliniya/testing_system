package ru.otus.java;

import java.util.InputMismatchException;

import static ru.otus.java.Result.countScore;
import static ru.otus.java.Result.isTestPassed;

public class Examiner {
    public static void main(String[] args) {
        Question[] questionsFotMathTest = new Question[3];
        questionsFotMathTest[0] = new Question(
                1,
                "5 + 5 =",
                new Answer[]{
                        new Answer(1, "15"),
                        new Answer(2, "10"),
                        new Answer(3, "5")},
                new Answer(3));
        questionsFotMathTest[1] = new Question(
                2,
                "7 * 8 =",
                new Answer[]{
                        new Answer(1, "72"),
                        new Answer(2, "63"),
                        new Answer(3, "56"),
                        new Answer(4, "54"),
                        new Answer(5, "65"),},
                new Answer(3));
        questionsFotMathTest[2] = new Question(
                3,
                "153 / 9 = ",
                new Answer[]{
                        new Answer(1, "16"),
                        new Answer(2, "17"),
                        new Answer(3, "18")},
                new Answer(2));

        Test mathTest = new Test("Тест по математике", questionsFotMathTest);

        beginTesting(mathTest);
        checkResult(mathTest);
    }

    private static void beginTesting(Test test) {
        System.out.println("Начинаем " + test.getTestName() + ". Введите номер варианта ответа и нажмите Enter.");

        for (Question question : test.getQuestionsSet()) {
            System.out.println(question.getQuestionNumber() + ". " + question.getQuestionText());

            for (int i = 0; i < question.getAnswersForQuestion().length; i++) {
                System.out.println("   " + question.getAnswersForQuestion()[i].getAnswerNumber() + ". "
                        + question.getAnswersForQuestion()[i].getAnswerText());
            }
            System.out.println("Ответ: ");

            saveSelectedAnswer(question);
        }
    }

    public static void saveSelectedAnswer(Question question) {
        Answer selectedAnswer;
        try {
            selectedAnswer = new Answer(Answer.readAnswer());
            if (Answer.isAnswerInRange(selectedAnswer.getAnswerNumber(), question.getAnswersForQuestion().length)) {
                question.setSelectedAnswer(selectedAnswer);
            } else{
                System.out.println(Answer.INCORRECT_ANSWER_NUMBER);
                System.exit(1);
            }
        } catch (InputMismatchException e) {
            printException(e);
            System.exit(1);
        }
    }

    private static void checkResult(Test test) {
        Result mathTestResult = new Result();
        test.setTestResult(mathTestResult);
        test.getTestResult().setScore(countScore(test.getQuestionsSet()));
        test.getTestResult().setEstimation(test.getTestResult().getScore());
        if (isTestPassed(test)) {
            System.out.println("Тест пройден. Вы ответили верно на все вопросы!");
            mathTestResult.printResult(test.getTestResult());
        } else {
            mathTestResult.printResult(test.getTestResult());
            System.out.println("В ответах есть ошибки.");
            System.out.println("Показать детали? (1 - 'Да', 0 - 'Нет')");
            try {
                int isNeedMoreDetails = Answer.readAnswer();
                switch (isNeedMoreDetails) {
                    case 1 -> test.getTestResult().showDetails(test);
                    case 0 -> System.exit(0);
                    default -> System.out.println(Answer.INCORRECT_INPUT);
                }
            } catch (InputMismatchException e) {
                printException(e);
                System.exit(1);
            }
        }
    }

    private static void printException(InputMismatchException e) {
        System.out.println("Кажется вы ввели не число. Возникла ошибка " + e);
        e.printStackTrace();
    }
}

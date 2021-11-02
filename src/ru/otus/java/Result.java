package ru.otus.java;

public class Result {
    private int score;
    private Estimations estimation;

    public static int countScore(Question[] questions) {
        int score = 0;
        for (Question question : questions) {
            if (question.getCorrectAnswer().getAnswerNumber() == question.getSelectedAnswer().getAnswerNumber())
                score++;
        }
        return score;
    }

    public static boolean isTestPassed(Test test) {
        return test.getQuestionsSet().length == test.getTestResult().getScore();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Estimations getEstimation() {
        return estimation;
    }

    public void setEstimation(int score) {
        switch (score) {
            case 3 -> estimation = Estimations.PERFECT;
            case 2 -> estimation = Estimations.GOOD;
            case 1 -> estimation = Estimations.SATISFACTORY;
            case 0 -> estimation = Estimations.BAD;
            default -> System.out.println("Неизвестная ошибка. Такого количества баллов вы не могли набрать [" + score + "]./n" +
                    "Попробуйте пройти тест еще раз.");
        }
    }

    public void showDetails(Test test) {
        for (Question question : test.getQuestionsSet()) {
            System.out.println("Правильный ответ: " + question.getCorrectAnswer().getAnswerNumber()
                    + ". Ваш ответ: " + question.getSelectedAnswer().getAnswerNumber() + ".");
        }
    }

    public void printResult(Result testResult) {
        System.out.println("Вы набрали баллов: " + testResult.getScore() + ". Ваша оценка: " + testResult.getEstimation());
    }
}

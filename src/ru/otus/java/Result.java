package ru.otus.java;

public class Result {
    private int score;
    private Estimations estimation;

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
            default -> System.err.printf(ExamService.ESTIMATION_SET_ERROR, score);
        }
    }

    public static int countScore(Question[] questions) {
        int score = 0;
        for (Question question : questions) {
            if (question.getCorrectAnswer().getAnswerNumber() == question.getSelectedAnswer().getAnswerNumber())
                score++;
        }
        return score;
    }

    public static void printResult(Result examResult) {
        System.out.printf(ExamService.EXAM_RESULT_MESSAGE, examResult.getScore(), examResult.getEstimation());
    }
}

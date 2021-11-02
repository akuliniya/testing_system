package ru.otus.java;

public class Exam {
    private final String examName;
    private final Question[] questionsSet;
    private Result examResult;

    public Exam(String examName, Question[] questionsSet) {
        this.examName = examName;
        this.questionsSet = questionsSet;
    }

    public String getExamName() {
        return examName;
    }

    public Result getExamResult() {
        return examResult;
    }

    public void setExamResult(Result examResult) {
        this.examResult = examResult;
    }

    public Question[] getQuestionsSet() {
        return questionsSet;
    }
}

package ru.otus.java;

public class Test {
    private final String testName;
    private final Question[] questionsSet;
    private Result testResult;

    public Test(String testName, Question[] questionsSet) {
        this.testName = testName;
        this.questionsSet = questionsSet;
    }

    public String getTestName() {
        return testName;
    }

    public Result getTestResult() {
        return testResult;
    }

    public void setTestResult(Result testResult) {
        this.testResult = testResult;
    }

    public Question[] getQuestionsSet() {
        return questionsSet;
    }
}

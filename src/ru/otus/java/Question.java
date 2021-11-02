package ru.otus.java;

import java.util.InputMismatchException;

import static ru.otus.java.ExamService.*;

public class Question {
    private final int questionNumber;
    private final String questionText;
    private final Answer[] answersForQuestion;
    private final Answer correctAnswer;
    private Answer selectedAnswer;

    public Question(int questionNumber, String questionText, Answer[] answersForQuestion, Answer correctAnswer) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.answersForQuestion = answersForQuestion;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public Answer[] getAnswersForQuestion() {
        return answersForQuestion;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}

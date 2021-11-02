package ru.otus.java;

public class ExaminerApp {
    public static void main(String[] args) {
        Question[] questionsFotMathExam = new Question[3];
        questionsFotMathExam[0] = new Question(
                1,
                "5 + 5 =",
                new Answer[]{
                        new Answer(1, "15"),
                        new Answer(2, "10"),
                        new Answer(3, "5")},
                new Answer(3));
        questionsFotMathExam[1] = new Question(
                2,
                "7 * 8 =",
                new Answer[]{
                        new Answer(1, "72"),
                        new Answer(2, "63"),
                        new Answer(3, "56"),
                        new Answer(4, "54"),
                        new Answer(5, "65"),},
                new Answer(3));
        questionsFotMathExam[2] = new Question(
                3,
                "153 / 9 = ",
                new Answer[]{
                        new Answer(1, "16"),
                        new Answer(2, "17"),
                        new Answer(3, "18")},
                new Answer(2));

        String mathExamName = "Тест по математике";
        Exam mathExam = new Exam(mathExamName, questionsFotMathExam);

        ExamService.startQuizService(mathExam);
        ExamService.checkResult(mathExam);
    }
}

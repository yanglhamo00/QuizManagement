package quiz.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/quizdb";
    private static final String USER = "root";
    private static final String PASSWORD = "YU_oppdivide"; // Ensure this is correct

    public static List<String[]> getQuestionsByQuizId(int quizId) throws SQLException {
        List<String[]> questions = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT question, option1, option2, option3, option4, answer FROM questions WHERE quiz_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, quizId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String[] question = new String[6];
            question[0] = rs.getString("question");
            question[1] = rs.getString("option1");
            question[2] = rs.getString("option2");
            question[3] = rs.getString("option3");
            question[4] = rs.getString("option4");
            question[5] = rs.getString("answer");
            questions.add(question);
        }

        rs.close();
        stmt.close();
        conn.close();

        return questions;
    }

    public static void saveScore(String name, int score, int quizId) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "INSERT INTO scores (name, score, quiz_id) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setInt(2, score);
        stmt.setInt(3, quizId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public static List<String[]> getScores() throws SQLException {
        List<String[]> scores = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT name, score FROM scores";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String[] score = new String[2];
            score[0] = rs.getString("name");
            score[1] = rs.getString("score");
            scores.add(score);
        }

        rs.close();
        stmt.close();
        conn.close();

        return scores;
    }

    public static void addQuestion(int quizId, String question, String option1, String option2, String option3, String option4, String answer) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "INSERT INTO questions (quiz_id, question, option1, option2, option3, option4, answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, quizId);
        stmt.setString(2, question);
        stmt.setString(3, option1);
        stmt.setString(4, option2);
        stmt.setString(5, option3);
        stmt.setString(6, option4);
        stmt.setString(7, answer);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public static List<String[]> getQuizzes() throws SQLException {
        List<String[]> quizzes = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "SELECT id, name FROM quizzes";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String[] quiz = new String[2];
            quiz[0] = rs.getString("id");
            quiz[1] = rs.getString("name");
            quizzes.add(quiz);
        }

        rs.close();
        stmt.close();
        conn.close();

        return quizzes;
    }

    public static void deleteQuiz(int quizId) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String query = "DELETE FROM quizzes WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, quizId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
}

package quiz.app;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class InstructorProfile extends JFrame {

    String name;

    InstructorProfile(String name) {
        this.name = name;
        setBounds(200, 100, 800, 600);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        GradientPanel gradientPanel = new GradientPanel(Color.WHITE, new Color(173, 216, 230));
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel heading = new JLabel("Welcome, " + name + " (Instructor)");
        heading.setBounds(50, 50, 700, 30);
        heading.setFont(new Font("Mongolian Baiti", Font.BOLD, 24));
        heading.setBorder(new LineBorder(Color.BLACK, 2));
        gradientPanel.add(heading);

        JButton addQuizButton = new JButton("Add New Quiz");
        addQuizButton.setBounds(300, 100, 200, 30);
        addQuizButton.setBackground(new Color(22, 99, 54));
        addQuizButton.setForeground(Color.WHITE);
        addQuizButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addQuizButton.setBorder(new LineBorder(Color.BLACK, 2));
        addQuizButton.addActionListener(e -> {
            // Pass both the name and a dummy quizId (e.g., 1)
            setVisible(false);
            new CreateQuiz(name, 1);
        });
        gradientPanel.add(addQuizButton);

        JLabel subHeading = new JLabel("Existing Quizzes:");
        subHeading.setBounds(300, 150, 200, 30);
        subHeading.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
        gradientPanel.add(subHeading);

        try {
            List<String[]> quizzes = DatabaseUtil.getQuizzes();
            int yOffset = 200;
            for (String[] quiz : quizzes) {
                JPanel quizPanel = new JPanel();
                quizPanel.setLayout(new BorderLayout());
                quizPanel.setBounds(200, yOffset, 400, 40);
                quizPanel.setBackground(new Color(255, 255, 255, 150));
                quizPanel.setBorder(new LineBorder(Color.BLACK, 1));

                JLabel quizLabel = new JLabel("Quiz: " + quiz[1]);
                quizLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
                quizPanel.add(quizLabel, BorderLayout.WEST);

                JButton deleteButton = new JButton("Delete");
                deleteButton.setBackground(new Color(255, 99, 71));
                deleteButton.setForeground(Color.WHITE);
                deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                int quizId = Integer.parseInt(quiz[0]);
                deleteButton.addActionListener(e -> {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "Are you sure you want to delete this quiz?",
                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            DatabaseUtil.deleteQuiz(quizId);
                            JOptionPane.showMessageDialog(this, "Quiz deleted successfully!");
                            setVisible(false);
                            new InstructorProfile(name).setVisible(true);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(this, "Error deleting quiz: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                quizPanel.add(deleteButton, BorderLayout.EAST);

                gradientPanel.add(quizPanel);
                yOffset += 50;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading quizzes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JButton logout = new JButton("Logout");
        logout.setBounds(300, 500, 200, 30);
        logout.setBackground(new Color(22, 99, 54));
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("Tahoma", Font.PLAIN, 18));
        logout.setBorder(new LineBorder(Color.BLACK, 2));
        logout.addActionListener(e -> {
            setVisible(false);
            new Login();
        });
        gradientPanel.add(logout);

        setVisible(true);
    }

    public static void main(String[] args) {
        new InstructorProfile("Instructor");
    }
}


package quiz.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CreateQuiz extends JFrame implements ActionListener {
    String instructorName;
    int quizId;
    JTextField questionField, option1Field, option2Field, option3Field, option4Field, answerField;
    JButton addQuestion, finish;

    CreateQuiz(String instructorName, int quizId) {
        this.instructorName = instructorName;
        this.quizId = quizId;
        setBounds(200, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel gradientPanel = new GradientPanel(Color.WHITE, new Color(173, 216, 230)); // Light blue gradient
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel heading = new JLabel("Create a New Quiz");
        heading.setBounds(200, 20, 400, 45);
        heading.setFont(new Font("Mongolian Baiti", Font.BOLD, 40));
        heading.setForeground(new Color(148, 199, 245));
        gradientPanel.add(heading);

        JLabel questionLabel = new JLabel("Question:");
        questionLabel.setBounds(50, 100, 100, 30);
        questionLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        gradientPanel.add(questionLabel);

        questionField = new JTextField();
        questionField.setBounds(150, 100, 600, 30);
        questionField.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        gradientPanel.add(questionField);

        JLabel option1Label = new JLabel("Option 1:");
        option1Label.setBounds(50, 150, 100, 30);
        option1Label.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        gradientPanel.add(option1Label);

        option1Field = new JTextField();
        option1Field.setBounds(150, 150, 600, 30);
        option1Field.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        gradientPanel.add(option1Field);

        JLabel option2Label = new JLabel("Option 2:");
        option2Label.setBounds(50, 200, 100, 30);
        option2Label.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        gradientPanel.add(option2Label);

        option2Field = new JTextField();
        option2Field.setBounds(150, 200, 600, 30);
        option2Field.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        gradientPanel.add(option2Field);

        JLabel option3Label = new JLabel("Option 3:");
        option3Label.setBounds(50, 250, 100, 30);
        option3Label.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        gradientPanel.add(option3Label);

        option3Field = new JTextField();
        option3Field.setBounds(150, 250, 600, 30);
        option3Field.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        gradientPanel.add(option3Field);

        JLabel option4Label = new JLabel("Option 4:");
        option4Label.setBounds(50, 300, 100, 30);
        option4Label.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        gradientPanel.add(option4Label);

        option4Field = new JTextField();
        option4Field.setBounds(150, 300, 600, 30);
        option4Field.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        gradientPanel.add(option4Field);

        JLabel answerLabel = new JLabel("Correct Answer:");
        answerLabel.setBounds(50, 350, 150, 30);
        answerLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        gradientPanel.add(answerLabel);

        answerField = new JTextField();
        answerField.setBounds(200, 350, 550, 30);
        answerField.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        gradientPanel.add(answerField);

        addQuestion = new JButton("Add Question");
        addQuestion.setBounds(150, 400, 150, 30);
        addQuestion.setBackground(new Color(22, 99, 54));
        addQuestion.setForeground(Color.WHITE);
        addQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addQuestion.addActionListener(this);
        gradientPanel.add(addQuestion);

        finish = new JButton("Finish");
        finish.setBounds(500, 400, 150, 30);
        finish.setBackground(new Color(22, 99, 54));
        finish.setForeground(Color.WHITE);
        finish.setFont(new Font("Tahoma", Font.PLAIN, 18));
        finish.addActionListener(this);
        gradientPanel.add(finish);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addQuestion) {
            String question = questionField.getText().trim();
            String option1 = option1Field.getText().trim();
            String option2 = option2Field.getText().trim();
            String option3 = option3Field.getText().trim();
            String option4 = option4Field.getText().trim();
            String answer = answerField.getText().trim();

            // Ensure all fields are filled
            if (question.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insert the question into the database
            try {
                DatabaseUtil.addQuestion(quizId, question, option1, option2, option3, option4, answer);
                JOptionPane.showMessageDialog(this, "Question added successfully!");
                questionField.setText("");
                option1Field.setText("");
                option2Field.setText("");
                option3Field.setText("");
                option4Field.setText("");
                answerField.setText("");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding question to the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == finish) {
            setVisible(false);
            new InstructorProfile(instructorName).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new CreateQuiz("Instructor", 1); // Example usage with quizId = 1
    }
}

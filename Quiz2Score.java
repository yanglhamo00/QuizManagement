package quiz.app;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz2Score extends JFrame {
    Quiz2Score(String name, int correctAnswers, int totalQuestions) {
        setBounds(200, 100, 800, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel gradientPanel = new GradientPanel(Color.WHITE, new Color(173, 216, 230));
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel thankYouLabel = new JLabel("Thank you " + name + " for playing the Quiz!");
        thankYouLabel.setBounds(50, 50, 700, 30);
        thankYouLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 24));
        gradientPanel.add(thankYouLabel);

        JLabel scoreLabel = new JLabel("Your Score: " + correctAnswers + " out of " + totalQuestions + " questions");
        scoreLabel.setBounds(50, 150, 700, 30);
        scoreLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 24));
        scoreLabel.setBorder(new LineBorder(Color.BLACK, 2));
        gradientPanel.add(scoreLabel);

        JButton backToProfileButton = new JButton("Back to Profile");
        backToProfileButton.setBounds(300, 250, 200, 50);
        backToProfileButton.setBackground(new Color(22, 99, 54));
        backToProfileButton.setForeground(Color.WHITE);
        backToProfileButton.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        backToProfileButton.setBorder(new LineBorder(Color.BLACK, 2));
        backToProfileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new StudentProfile(name).setVisible(true);
            }
        });
        gradientPanel.add(backToProfileButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Quiz2Score("User", 3, 10);
    }
}
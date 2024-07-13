package quiz.app;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score extends JFrame implements ActionListener {
    Score(String name, int correctAnswers, int totalQuestions) {
        setBounds(600, 150, 750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel gradientPanel = new GradientPanel(Color.WHITE, new Color(173, 216, 230));
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel heading = new JLabel("Thank you " + name + " for playing the Quiz!");
        heading.setBounds(50, 50, 650, 50);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(heading);

        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(50, 150, 650, 100);
        scorePanel.setLayout(null);
        scorePanel.setBackground(new Color(255, 255, 255, 150));
        scorePanel.setBorder(new LineBorder(Color.BLACK, 2));
        gradientPanel.add(scorePanel);

        JLabel lblScore = new JLabel("Your Score: " + correctAnswers + " out of " + totalQuestions + " questions");
        lblScore.setBounds(50, 25, 550, 50);
        lblScore.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblScore.setHorizontalAlignment(SwingConstants.CENTER);
        scorePanel.add(lblScore);

        JButton backToProfileButton = new JButton("Back to Profile");
        backToProfileButton.setBounds(300, 300, 150, 50);
        backToProfileButton.setBackground(new Color(30, 144, 255));
        backToProfileButton.setForeground(Color.WHITE);
        backToProfileButton.addActionListener(this);
        gradientPanel.add(backToProfileButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
        new StudentProfile("User").setVisible(true);
    }

    public static void main(String[] args) {
        new Score("User", 0, 10);
    }
}

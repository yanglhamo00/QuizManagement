package quiz.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {
    JButton start, back;
    String name;
    int quizId;

    Rules(String name, int quizId) {
        this.name = name;
        this.quizId = quizId;

        GradientPanel gradientPanel = new GradientPanel(Color.WHITE, new Color(173, 216, 230)); // Light blue gradient
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel heading = new JLabel("Welcome " + name + " to QUIZ TEST");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Mongolian Baiti", Font.BOLD, 28));
        heading.setForeground(new Color(22, 99, 54));
        gradientPanel.add(heading);

        JLabel rules = new JLabel();
        rules.setBounds(70, 60, 700, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setForeground(new Color(22, 99, 54));
        rules.setText(
                "<html>" +
                        "1. Participation in the quiz is free and open to all persons above 18 years old." + "<br><br>" +
                        "2. There are a total of 10 questions." + "<br><br>" +
                        "3. You only have 15 seconds to answer each question." + "<br><br>" +
                        "4. No cell phones or other secondary devices in the room or test area." + "<br><br>" +
                        "5. No talking." + "<br><br>" +
                        "6. No one else can be in the room with you." + "<br><br>" +
                        "</html>"
        );
        gradientPanel.add(rules);

        back = new JButton("Back");
        back.setBounds(300, 420, 100, 30);
        back.setBackground(new Color(22, 99, 54));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        gradientPanel.add(back);

        start = new JButton("Start");
        start.setBounds(450, 420, 100, 30);
        start.setBackground(new Color(22, 99, 54));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        gradientPanel.add(start);

        setSize(800, 500);
        setLocation(350, 100);
        setLayout(null);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            setVisible(false);
            new Quiz(name, quizId).setVisible(true); // Assuming Quiz class accepts quizId
        } else {
            setVisible(false);
            new StudentProfile(name).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Rules("User", 1);
    }
}

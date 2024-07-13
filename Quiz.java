package quiz.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Quiz extends JFrame implements ActionListener {
    List<String[]> questions;
    String useranswers[][] = new String[10][1];
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline;
    public static int timer = 15;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;
    String name;
    int quizId;

    Quiz(String name, int quizId) {
        this.name = name;
        this.quizId = quizId;
        setBounds(50, 50, 1000, 700);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            questions = DatabaseUtil.getQuestionsByQuizId(quizId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        GradientPanel gradientPanel = new GradientPanel(Color.WHITE, new Color(173, 216, 230)); // Light blue gradient
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        qno = new JLabel();
        qno.setBounds(50, 100, 50, 30);
        qno.setFont(new Font("Mongolian Baiti", Font.PLAIN, 24));
        gradientPanel.add(qno);

        question = new JLabel();
        question.setBounds(100, 100, 800, 30);
        question.setFont(new Font("Mongolian Baiti", Font.PLAIN, 24));
        gradientPanel.add(question);

        opt1 = new JRadioButton();
        opt1.setBounds(100, 150, 800, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Mongolian Baiti", Font.PLAIN, 20));
        gradientPanel.add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(100, 190, 800, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Mongolian Baiti", Font.PLAIN, 20));
        gradientPanel.add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(100, 230, 800, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Mongolian Baiti", Font.PLAIN, 20));
        gradientPanel.add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(100, 270, 800, 30);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Mongolian Baiti", Font.PLAIN, 20));
        gradientPanel.add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("Next");
        next.setBounds(600, 350, 100, 30);
        next.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        next.setBackground(new Color(22, 99, 54));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        gradientPanel.add(next);

        lifeline = new JButton("Help");
        lifeline.setBounds(750, 350, 100, 30);
        lifeline.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        lifeline.setBackground(new Color(22, 99, 54));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        gradientPanel.add(lifeline);

        submit = new JButton("Submit");
        submit.setBounds(900, 350, 100, 30);
        submit.setForeground(Color.BLACK);
        submit.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
        submit.setBackground(new Color(255, 215, 0));
        submit.addActionListener(this);
        submit.setEnabled(false);
        gradientPanel.add(submit);

        start(count);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }
            if (count == 9) {
                next.setEnabled(false);
                submit.setEnabled(true);
            } else {
                count++;
                start(count);
            }
        } else if (ae.getSource() == lifeline) {
            if (count % 2 == 0) {
                opt2.setEnabled(false);
                opt3.setEnabled(false);
            } else {
                opt1.setEnabled(false);
                opt4.setEnabled(false);
            }
            lifeline.setEnabled(false);
        } else if (ae.getSource() == submit) {
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }
            for (int i = 0; i < useranswers.length; i++) {
                if (useranswers[i][0].equals(questions.get(i)[5])) {
                    score += 10;
                }
            }
            try {
                DatabaseUtil.saveScore(name, score, quizId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Score(name, score, questions.size()).setVisible(true);
        }
    }

    public void start(int count) {
        qno.setText((count + 1) + ". ");
        question.setText(questions.get(count)[0]);
        opt1.setText(questions.get(count)[1]);
        opt1.setActionCommand(questions.get(count)[1]);
        opt2.setText(questions.get(count)[2]);
        opt2.setActionCommand(questions.get(count)[2]);
        opt3.setText(questions.get(count)[3]);
        opt3.setActionCommand(questions.get(count)[3]);
        opt4.setText(questions.get(count)[4]);
        opt4.setActionCommand(questions.get(count)[4]);
        groupoptions.clearSelection();
        timer = 15;
    }

    public static void main(String[] args) {
        new Quiz("User", 1);
    }
}

package quiz.app;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    JTextField text;
    JRadioButton instructor, student;
    JButton next, exit;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Make the background image cover the whole frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/main.gif"));
        Image i = i1.getImage().getScaledInstance(1000, 500, Image.SCALE_DEFAULT); // Adjust to cover entire frame
        ImageIcon i2 = new ImageIcon(i);
        JLabel image = new JLabel(i2);
        image.setBounds(0, 0, 1000, 500);
        add(image);

        // Center position calculations
        int frameWidth = 1000;
        int frameHeight = 500;

        int componentWidth = 300;
        int componentHeight = 45;
        int centerX = (frameWidth - componentWidth) / 2;
        int centerY = 60;

        JLabel heading = new JLabel("QUIZ TEST");
        heading.setBounds(centerX, centerY, componentWidth, componentHeight);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(148, 199, 245));
        add(heading);

        componentWidth = 300;
        componentHeight = 20;
        centerX = (frameWidth - componentWidth) / 2;
        centerY = 150;

        JLabel nameLabel = new JLabel("Enter Your Name");
        nameLabel.setBounds(centerX, centerY, componentWidth, componentHeight);
        nameLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        nameLabel.setForeground(new Color(22, 99, 54));
        add(nameLabel);

        componentWidth = 300;
        componentHeight = 25;
        centerX = (frameWidth - componentWidth) / 2;
        centerY = 200;

        text = new JTextField();
        text.setBounds(centerX, centerY, componentWidth, componentHeight);
        text.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(text);

        componentWidth = 100;
        componentHeight = 25;
        centerX = (frameWidth - componentWidth) / 2 - 75;
        centerY = 240;

        instructor = new JRadioButton("Instructor");
        instructor.setBounds(centerX, centerY, componentWidth, componentHeight);
        instructor.setBackground(Color.WHITE);
        instructor.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        add(instructor);

        centerX = (frameWidth - componentWidth) / 2 + 75;

        student = new JRadioButton("Student");
        student.setBounds(centerX, centerY, componentWidth, componentHeight);
        student.setBackground(Color.WHITE);
        student.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        add(student);

        ButtonGroup group = new ButtonGroup();
        group.add(instructor);
        group.add(student);

        componentWidth = 120;
        componentHeight = 25;
        centerX = (frameWidth - componentWidth) / 2 - 75;
        centerY = 300;

        next = new JButton("Next");
        next.setBounds(centerX, centerY, componentWidth, componentHeight);
        next.setBackground(new Color(22, 99, 54));
        next.setForeground(Color.WHITE);
        next.addActionListener(e -> {
            String userName = text.getText();
            if (instructor.isSelected()) {
                new InstructorProfile(userName);
            } else if (student.isSelected()) {
                new StudentProfile(userName);
            }
            setVisible(false);
        });
        add(next);

        centerX = (frameWidth - componentWidth) / 2 + 75;

        exit = new JButton("Exit");
        exit.setBounds(centerX, centerY, componentWidth, componentHeight);
        exit.setBackground(new Color(22, 99, 54));
        exit.setForeground(Color.WHITE);
        exit.addActionListener(e -> System.exit(0));
        add(exit);

        // Move the background image to the back
        getContentPane().add(image, new Integer(Integer.MIN_VALUE));

        setSize(1000, 500);
        setLocation(200, 150);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}

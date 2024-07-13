package quiz.app;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AdditionalScores extends JFrame {
    String instructorName;
    JTable table;

    AdditionalScores(String instructorName) {
        this.instructorName = instructorName;
        setBounds(200, 100, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GradientPanel gradientPanel = new GradientPanel(Color.WHITE, new Color(173, 216, 230)); // Light blue gradient
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel heading = new JLabel("Student Quiz Scores");
        heading.setBounds(200, 20, 400, 45);
        heading.setFont(new Font("Mongolian Baiti", Font.BOLD, 40));
        heading.setForeground(new Color(148, 199, 245));
        heading.setBorder(new LineBorder(Color.BLACK, 2));
        gradientPanel.add(heading);

        String[] columnNames = {"Student Name", "Score"};
        String[][] data = fetchScores();

        table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 100, 700, 300);
        scrollPane.setBorder(new LineBorder(Color.BLACK, 2));
        gradientPanel.add(scrollPane);

        JButton back = new JButton("Back");
        back.setBounds(300, 420, 200, 30);
        back.setBackground(new Color(22, 99, 54));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Tahoma", Font.PLAIN, 18));
        back.setBorder(new LineBorder(Color.BLACK, 2));
        back.addActionListener(e -> {
            setVisible(false);
            new InstructorProfile(instructorName).setVisible(true);
        });
        gradientPanel.add(back);

        setVisible(true);
    }

    private String[][] fetchScores() {
        try {
            List<String[]> scores = DatabaseUtil.getScores();
            String[][] data = new String[scores.size()][2];
            for (int i = 0; i < scores.size(); i++) {
                data[i][0] = scores.get(i)[0];
                data[i][1] = scores.get(i)[1];
            }
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0][0];
        }
    }

    public static void main(String[] args) {
        new AdditionalScores("Instructor");
    }
}

package vn.edu.eaut.lab4;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Bai1_TaoDongHo extends JFrame {
    private JTextField txtSeconds;
    private JButton btnStart;
    private JLabel lblTime;

    public Bai1_TaoDongHo() {
        setTitle("Dong ho dem nguoc");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        txtSeconds = new JTextField(10);
        btnStart = new JButton("Bat dau");
        lblTime = new JLabel("Con: 0 giay", SwingConstants.CENTER);
        lblTime.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(txtSeconds);
        panel.add(btnStart);
        panel.add(lblTime);
        add(panel);

        btnStart.addActionListener(e -> startCountdown());
    }

    private void startCountdown() {
        int seconds;
        try {
            seconds = Integer.parseInt(txtSeconds.getText().trim());
            if (seconds <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nhap so nguyen > 0");
            return;
        }

        btnStart.setEnabled(false);
        final int total = seconds;

        new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = total; i >= 0; i--) {
                    publish(i);
                    Thread.sleep(1000);
                }
                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                lblTime.setText("Con: " + chunks.get(chunks.size() - 1) + " giay");
            }

            @Override
            protected void done() {
                btnStart.setEnabled(true);
                JOptionPane.showMessageDialog(Bai1_TaoDongHo.this, "Hoan thanh!");
            }
        }.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai1_TaoDongHo().setVisible(true));
    }
}
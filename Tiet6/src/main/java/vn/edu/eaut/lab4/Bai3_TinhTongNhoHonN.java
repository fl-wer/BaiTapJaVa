package vn.edu.eaut.lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Bai3_TinhTongNhoHonN extends JFrame {
    private JTextField txtN;
    private JButton btnTinh;
    private JButton btnCancel;              // Nút hủy
    private JLabel lblResult;
    private JProgressBar progressBar;
    private SwingWorker<Long, Void> worker; // Lưu worker để hủy

    public Bai3_TinhTongNhoHonN() {
        setTitle("Bai 3 - Tinh tong so nguyen to (Co huy)");
        setSize(550, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        inputPanel.add(new JLabel("N = "));
        
        txtN = new JTextField(10);
        txtN.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(txtN);
        
        btnTinh = new JButton("Tinh");
        btnTinh.setFont(new Font("Arial", Font.BOLD, 14));
        btnTinh.setPreferredSize(new Dimension(100, 35));
        inputPanel.add(btnTinh);
        
        // Nút hủy - ban đầu bị vô hiệu hóa
        btnCancel = new JButton("Huy");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setPreferredSize(new Dimension(100, 35));
        btnCancel.setEnabled(false);
        btnCancel.setForeground(Color.RED);
        inputPanel.add(btnCancel);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.BOLD, 12));

        lblResult = new JLabel("Ket qua: ", SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.BOLD, 16));

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(progressBar, BorderLayout.CENTER);
        mainPanel.add(lblResult, BorderLayout.SOUTH);

        add(mainPanel);

        btnTinh.addActionListener(e -> calculatePrimeSum());
        btnCancel.addActionListener(e -> cancelTask());  // Xử lý hủy
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private void calculatePrimeSum() {
        int n;
        try {
            n = Integer.parseInt(txtN.getText().trim());
            if (n <= 2) {
                JOptionPane.showMessageDialog(this, "N phai lon hon 2");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui long nhap so nguyen hop le");
            return;
        }

        // Vô hiệu hóa nút Tính và TextField, kích hoạt nút Hủy
        btnTinh.setEnabled(false);
        txtN.setEnabled(false);
        btnCancel.setEnabled(true);
        progressBar.setValue(0);
        lblResult.setText("Dang tinh...");

        final int N = n;

        // Tạo SwingWorker
        worker = new SwingWorker<>() {
            @Override
            protected Long doInBackground() {
                long sum = 0;
                for (int i = 2; i < N; i++) {
                    // Kiểm tra hủy mỗi lần lặp
                    if (isCancelled()) {
                        break;  // Thoát vòng lặp nếu đã hủy
                    }
                    
                    if (isPrime(i)) {
                        sum += i;
                    }
                    
                    int progress = (int) ((i * 100.0) / N);
                    setProgress(progress);
                }
                return sum;
            }

            @Override
            protected void done() {
                // Kiểm tra tác vụ có bị hủy không
                if (isCancelled()) {
                    lblResult.setText("Da huy tac vu");
                    progressBar.setValue(0);
                } else {
                    try {
                        long result = get();
                        lblResult.setText("Tong cac so nguyen to nho hon " + N + " = " + result);
                        progressBar.setValue(100);
                    } catch (Exception ex) {
                        lblResult.setText("Co loi khi tinh toan");
                    }
                }
                // Kích hoạt lại các thành phần
                btnTinh.setEnabled(true);
                txtN.setEnabled(true);
                btnCancel.setEnabled(false);
            }
        };

        // Lắng nghe progress
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName()) && !worker.isCancelled()) {
                progressBar.setValue((int) evt.getNewValue());
            }
        });

        worker.execute();
    }


    private void cancelTask() {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);  // Yêu cầu hủy tác vụ
            btnCancel.setEnabled(false);
            lblResult.setText("Dang huy tac vu...");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai3_TinhTongNhoHonN().setVisible(true));
    }
}
package vn.edu.eaut.lab4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

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
import javax.swing.UIManager;

public class Bai4_FibonacciMemoization extends JFrame {
    private JTextField txtN;
    private JButton btnFind;
    private JLabel lblResult;
    private JProgressBar progressBar;

    public Bai4_FibonacciMemoization() {
        setTitle("Bai 4 - Fibonacci Memoization");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        inputPanel.add(new JLabel("N = "));
        
        txtN = new JTextField(10);
        txtN.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(txtN);
        
        btnFind = new JButton("Tim");
        btnFind.setFont(new Font("Arial", Font.BOLD, 14));
        btnFind.setPreferredSize(new Dimension(100, 35));
        inputPanel.add(btnFind);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.BOLD, 12));

        lblResult = new JLabel("Ket qua: ", SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.BOLD, 16));

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(progressBar, BorderLayout.CENTER);
        mainPanel.add(lblResult, BorderLayout.SOUTH);

        add(mainPanel);
        btnFind.addActionListener(e -> findFibonacci());
        
        // Enter key support
        txtN.addActionListener(e -> findFibonacci());
    }

    /**
     * Tinh Fibonacci co memoization
     * @param n so Fibonacci can tinh
     * @param memo Map luu ket qua da tinh
     * @return so Fibonacci thu n
     */
    private BigInteger fibonacci(int n, Map<Integer, BigInteger> memo) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        BigInteger value = fibonacci(n - 1, memo).add(fibonacci(n - 2, memo));
        memo.put(n, value);
        return value;
    }

    /**
     * Xu ly tim Fibonacci bang SwingWorker
     */
    private void findFibonacci() {
        int n;
        try {
            n = Integer.parseInt(txtN.getText().trim());
            if (n < 0) {
                JOptionPane.showMessageDialog(this, "N phai >= 0");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui long nhap so nguyen hop le");
            return;
        }

        btnFind.setEnabled(false);
        progressBar.setIndeterminate(true);
        lblResult.setText("Dang tinh Fibonacci...");

        SwingWorker<BigInteger, Void> worker = new SwingWorker<>() {
            @Override
            protected BigInteger doInBackground() {
                Map<Integer, BigInteger> memo = new HashMap<>();
                return fibonacci(n, memo);
            }

            @Override
            protected void done() {
                try {
                    BigInteger result = get();
                    lblResult.setText("Fibonacci(" + n + ") = " + result);
                } catch (Exception ex) {
                    lblResult.setText("Co loi khi tinh Fibonacci");
                }
                progressBar.setIndeterminate(false);
                progressBar.setValue(100);
                btnFind.setEnabled(true);
            }
        };

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Bai4_FibonacciMemoization().setVisible(true);
        });
    }
}
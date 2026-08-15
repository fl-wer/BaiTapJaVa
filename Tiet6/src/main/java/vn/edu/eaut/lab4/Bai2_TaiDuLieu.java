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
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class Bai2_TaiDuLieu extends JFrame {
    private JButton btnLoad;
    private JButton btnCancel;          // Nút hủy
    private JProgressBar progressBar;
    private JLabel lblStatus;
    private SwingWorker<Void, Integer> worker;  // Lưu worker để có thể hủy

    public Bai2_TaiDuLieu() {
        setTitle("Bai 2 - Mo phong tai du lieu (Co huy)");
        setSize(500, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Panel chứa 2 nút: Tải và Hủy
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnLoad = new JButton("Tai du lieu");
        btnLoad.setFont(new Font("Arial", Font.BOLD, 14));
        btnLoad.setPreferredSize(new Dimension(130, 40));
        
        // Nút hủy - ban đầu bị vô hiệu hóa
        btnCancel = new JButton("Huy");
        btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancel.setPreferredSize(new Dimension(100, 40));
        btnCancel.setEnabled(false);        // Không cho hủy khi chưa có tác vụ
        btnCancel.setForeground(Color.RED);
        
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnCancel);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.BOLD, 12));

        lblStatus = new JLabel("Chua tai du lieu", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 14));

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(progressBar, BorderLayout.CENTER);
        mainPanel.add(lblStatus, BorderLayout.SOUTH);

        add(mainPanel);

        btnLoad.addActionListener(e -> loadData());
        btnCancel.addActionListener(e -> cancelTask());  // Xử lý hủy
    }

    private void loadData() {
        // Vô hiệu hóa nút Tải, kích hoạt nút Hủy
        btnLoad.setEnabled(false);
        btnCancel.setEnabled(true);
        progressBar.setValue(0);
        lblStatus.setText("Dang tai du lieu...");

        // Tạo SwingWorker
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i += 10) {
                    // Kiểm tra xem tác vụ đã bị hủy chưa
                    if (isCancelled()) {
                        break;  // Thoát vòng lặp nếu đã hủy
                    }
                    setProgress(i);
                    Thread.sleep(1000);
                }
                return null;
            }

            @Override
            protected void done() {
                // Kiểm tra xem tác vụ có bị hủy không
                if (isCancelled()) {
                    // Hiển thị thông báo đã hủy
                    lblStatus.setText("Da huy tac vu");
                    progressBar.setValue(0);
                } else {
                    // Hoàn thành bình thường
                    progressBar.setValue(100);
                    lblStatus.setText("Tai du lieu hoan tat");
                }
                // Kích hoạt lại nút Tải, vô hiệu hóa nút Hủy
                btnLoad.setEnabled(true);
                btnCancel.setEnabled(false);
            }
        };

        // Lắng nghe sự thay đổi progress
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName()) && !worker.isCancelled()) {
                progressBar.setValue((int) evt.getNewValue());
            }
        });

        worker.execute();  // Bắt đầu chạy
    }

    
    private void cancelTask() {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);  // Gửi yêu cầu hủy tác vụ
            btnCancel.setEnabled(false);
            lblStatus.setText("Dang huy tac vu...");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai2_TaiDuLieu().setVisible(true));
    }
}
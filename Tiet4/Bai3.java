import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai3 {
    public static void main(String[] args) {
        // Tạo JFrame với kích thước 300x200
        JFrame frame = new JFrame("Exit Application");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Sử dụng BorderLayout để dễ dàng căn giữa
        frame.setLayout(new BorderLayout());
        
        // Tạo nút Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setPreferredSize(new Dimension(100, 40));
        
        // Thêm sự kiện cho nút
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Thoát ứng dụng
                System.exit(0);
            }
        });
        
        // Đặt nút ở giữa cửa sổ
        frame.add(exitButton, BorderLayout.CENTER);
        
        // Hiển thị frame
        frame.setVisible(true);
    }
}
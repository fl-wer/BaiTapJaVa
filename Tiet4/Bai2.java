import javax.swing.*;

public class Bai2 {
    public static void main(String[] args) {
        // Tạo JFrame với tiêu đề
        JFrame frame = new JFrame("Welcome");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Hiển thị JOptionPane
        JOptionPane.showMessageDialog(frame, "Welcome to Java Swing");
        
        // Đóng ứng dụng sau khi nhấn OK
        frame.dispose();
    }
}
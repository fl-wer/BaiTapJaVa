import java.awt.*;
import javax.swing.*;

public class Bai1 {
    public static void main(String[] args) {
        // Tạo JFrame với tiêu đề
        JFrame frame = new JFrame("My First Swing App");
        
        // Đặt kích thước
        frame.setSize(400, 300);
        
        // set chia đều thành phần theo hàng ngang
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 120));
        
        // Tạo JLabel và căn giữa
        JLabel label1 = new JLabel("Black", SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        label1.setForeground(Color.black);

        // Thêm label vào frame
        frame.add(label1);
        JLabel label2 = new JLabel("Pink", SwingConstants.CENTER);
        label2.setFont(new Font("Arial", Font.BOLD, 24));
        label2.setForeground(Color.pink);
        frame.add(label2);
        // Đặt thao tác đóng cửa sổ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Hiển thị frame
        frame.setVisible(true);
    }
}
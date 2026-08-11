import java.awt.*;
import javax.swing.*;

public class Bai6 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Custom Icon Window");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String iconPath = "logo.jpg";
     
        ImageIcon icon = new ImageIcon(iconPath);
        if (icon.getIconWidth() != -1) {
            frame.setIconImage(icon.getImage());
            System.out.println("Đã tải icon thành công!");
        } else {
            System.err.println("Không tìm thấy file tại: " + iconPath);
        }

        JLabel label = new JLabel("Custom Icon Window", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label);
        
        frame.setVisible(true);
    }
}
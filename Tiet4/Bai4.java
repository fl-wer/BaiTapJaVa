import javax.swing.*;

public class Bai4 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Viewer");

        String imagePath = "pic.jpg"; // Hoặc đường dẫn tuyệt đối: "C:/Users/YourName/Pictures/image.jpg"

        ImageIcon imageIcon = new ImageIcon(imagePath);

        if (imageIcon.getIconWidth() == -1) {
            System.out.println("Không tìm thấy hình ảnh tại: " + imagePath);
            System.out.println("Vui lòng kiểm tra đường dẫn file!");
            return;
        }

        JLabel imageLabel = new JLabel(imageIcon);

        frame.add(imageLabel);

        frame.pack(); // Sẽ tự động điều chỉnh kích thước frame theo nội dung

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        
    }
    
}
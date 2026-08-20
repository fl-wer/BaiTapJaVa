import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Class Main - điểm khởi chạy (entry point) của chương trình.
 */
public class main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            QuanLyDiemSinhVien frame = new QuanLyDiemSinhVien();
            frame.setVisible(true);
        });
    }
}
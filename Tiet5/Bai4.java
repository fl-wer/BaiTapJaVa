import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai4 extends JFrame {
    private JTextField txtA, txtB, txtC, txtKetQua;
    private JButton btnKiemTra;

    public Bai4() {
        setTitle("Kiểm tra và phân loại tam giác");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Nhập cạnh a:"));
        txtA = new JTextField();
        add(txtA);

        add(new JLabel("Nhập cạnh b:"));
        txtB = new JTextField();
        add(txtB);

        add(new JLabel("Nhập cạnh c:"));
        txtC = new JTextField();
        add(txtC);

        add(new JLabel("Kết quả:"));
        txtKetQua = new JTextField();
        txtKetQua.setEditable(false);
        add(txtKetQua);

        btnKiemTra = new JButton("Kiểm tra");
        add(new JLabel()); // placeholder
        add(btnKiemTra);

        btnKiemTra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiemTraTamGiac();
            }
        });
    }

    private void kiemTraTamGiac() {
        try {
            double a = Double.parseDouble(txtA.getText());
            double b = Double.parseDouble(txtB.getText());
            double c = Double.parseDouble(txtC.getText());

            if (a + b > c && a + c > b && b + c > a) {
                if (a == b && b == c) {
                    txtKetQua.setText("Tam giác đều");
                } else if (a == b || b == c || a == c) {
                    if (a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a) {
                        txtKetQua.setText("Tam giác vuông cân");
                    } else {
                        txtKetQua.setText("Tam giác cân");
                    }
                } else if (a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a) {
                    txtKetQua.setText("Tam giác vuông");
                } else {
                    txtKetQua.setText("Tam giác thường");
                }
            } else {
                txtKetQua.setText("Không phải là tam giác");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai4().setVisible(true));
    }
}

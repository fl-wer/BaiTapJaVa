import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bai2_Lab3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tính Tổng Hai Số");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel lblNumber1 = new JLabel("Số thứ nhất:");
        JTextField txtNumber1 = new JTextField(15);
        
        JLabel lblNumber2 = new JLabel("Số thứ hai:");
        JTextField txtNumber2 = new JTextField(15);
        
        JButton btnCalculate = new JButton("Tính Tổng");
        JLabel lblResult = new JLabel("Kết quả: ");
        lblResult.setFont(new Font("Arial", Font.BOLD, 16));
        
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                
                    double num1 = Double.parseDouble(txtNumber1.getText());
                    double num2 = Double.parseDouble(txtNumber2.getText());

                    double sum = num1 + num2;
                    
                    lblResult.setText("Kết quả: " + num1 + " + " + num2 + " = " + sum);
                    
                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                        frame,
                        "Vui lòng nhập số hợp lệ!",
                        "Lỗi nhập liệu",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(lblNumber1, gbc);
        gbc.gridx = 1;
        frame.add(txtNumber1, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        frame.add(lblNumber2, gbc);
        gbc.gridx = 1;
        frame.add(txtNumber2, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(btnCalculate, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(lblResult, gbc);
        
        frame.setVisible(true);
    }
}

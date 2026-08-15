import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai6 extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cbRole;
    private JCheckBox chkRemember;
    private JButton btnLogin;

    public Bai6() {
        setTitle("Form đăng nhập cơ bản");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Username:"));
        txtUsername = new JTextField();
        add(txtUsername);

        add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        add(txtPassword);

        add(new JLabel("Role:"));
        String[] roles = {"Admin", "User", "Manager"};
        cbRole = new JComboBox<>(roles);
        add(cbRole);

        add(new JLabel("Options:"));
        chkRemember = new JCheckBox("Remember me");
        add(chkRemember);

        btnLogin = new JButton("Login");
        add(new JLabel()); // placeholder
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String role = (String) cbRole.getSelectedItem();
        boolean remember = chkRemember.isSelected();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            String msg = "Đăng nhập thành công!\nUsername: " + username + "\nRole: " + role + "\nRemember me: " + remember;
            JOptionPane.showMessageDialog(this, msg, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai6().setVisible(true));
    }
}

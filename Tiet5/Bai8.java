import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bai8 extends JFrame {
    private JTextField txtId, txtName, txtAge;
    private JButton btnAdd, btnEdit, btnDelete;
    private JTable table;
    private DefaultTableModel tableModel;

    public Bai8() {
        setTitle("Quản lý sinh viên");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
        
        inputPanel.add(new JLabel("Mã SV:"));
        txtId = new JTextField();
        inputPanel.add(txtId);

        inputPanel.add(new JLabel("Họ tên:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Tuổi:"));
        txtAge = new JTextField();
        inputPanel.add(txtAge);

        // Buttons Panel
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        inputPanel.add(new JLabel()); // placeholder
        inputPanel.add(btnPanel);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Mã SV", "Họ tên", "Tuổi"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Events
        btnAdd.addActionListener(e -> addStudent());
        btnEdit.addActionListener(e -> editStudent());
        btnDelete.addActionListener(e -> deleteStudent());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(tableModel.getValueAt(row, 0).toString());
                    txtName.setText(tableModel.getValueAt(row, 1).toString());
                    txtAge.setText(tableModel.getValueAt(row, 2).toString());
                }
            }
        });
    }

    private void addStudent() {
        if (validateInput()) {
            tableModel.addRow(new Object[]{txtId.getText(), txtName.getText(), txtAge.getText()});
            clearInput();
        }
    }

    private void editStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            if (validateInput()) {
                tableModel.setValueAt(txtId.getText(), row, 0);
                tableModel.setValueAt(txtName.getText(), row, 1);
                tableModel.setValueAt(txtAge.getText(), row, 2);
                clearInput();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            tableModel.removeRow(row);
            clearInput();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean validateInput() {
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtAge.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Integer.parseInt(txtAge.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tuổi phải là một số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearInput() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        table.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Bai8().setVisible(true));
    }
}

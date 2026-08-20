import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Class giao diện: Quản lý điểm sinh viên bằng JTable
 * Sử dụng class model SinhVien để lưu dữ liệu và tính toán.
 * Chức năng: Thêm, Sửa, Xóa, Làm mới.
 */
public class QuanLyDiemSinhVien extends JFrame {

    // ===== Thành phần giao diện =====
    private JTextField txtMaSV, txtHoTen, txtChuyenCan, txtGiuaKy, txtCuoiKy;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;

    private final DecimalFormat df = new DecimalFormat("#0.00");

    public QuanLyDiemSinhVien() {
        setTitle("Quản Lý Điểm Sinh Viên");
        setSize(950, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(taoPanelForm(), BorderLayout.NORTH);
        add(taoPanelBang(), BorderLayout.CENTER);
        add(taoPanelNut(), BorderLayout.SOUTH);
    }

    // ================= FORM NHẬP LIỆU =================
    private JPanel taoPanelForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtMaSV = new JTextField(10);
        txtHoTen = new JTextField(15);
        txtChuyenCan = new JTextField(6);
        txtGiuaKy = new JTextField(6);
        txtCuoiKy = new JTextField(6);

        // Hàng 1
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Mã SV:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(txtMaSV, gbc);

        gbc.gridx = 2; gbc.gridy = 0; panel.add(new JLabel("Họ tên:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; panel.add(txtHoTen, gbc);

        // Hàng 2
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Điểm chuyên cần:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(txtChuyenCan, gbc);

        gbc.gridx = 2; gbc.gridy = 1; panel.add(new JLabel("Điểm giữa kỳ:"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; panel.add(txtGiuaKy, gbc);

        gbc.gridx = 4; gbc.gridy = 1; panel.add(new JLabel("Điểm cuối kỳ:"), gbc);
        gbc.gridx = 5; gbc.gridy = 1; panel.add(txtCuoiKy, gbc);

        return panel;
    }

    // ================= BẢNG DANH SÁCH =================
    private JScrollPane taoPanelBang() {
        String[] cols = {"Mã SV", "Họ tên", "Chuyên cần", "Giữa kỳ", "Cuối kỳ", "Điểm TB", "Xếp loại"};
        model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // không cho sửa trực tiếp trên bảng
            }
        };
        table = new JTable(model);
        table.setRowHeight(24);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) hienThiLenForm();
        });
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder("Danh sách sinh viên"));
        return scroll;
    }

    // ================= NÚT CHỨC NĂNG =================
    private JPanel taoPanelNut() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLamMoi = new JButton("Làm mới");

        // THÊM MÀU CHO CÁC NÚT
        styleButton(btnThem, new Color(46, 204, 113));      // Màu xanh lá
        styleButton(btnSua, new Color(52, 152, 219));       // Màu xanh dương
        styleButton(btnXoa, new Color(231, 76, 60));        // Màu đỏ
        styleButton(btnLamMoi, new Color(149, 165, 166));   // Màu xám

        btnThem.addActionListener(this::themSinhVien);
        btnSua.addActionListener(this::suaSinhVien);
        btnXoa.addActionListener(this::xoaSinhVien);
        btnLamMoi.addActionListener(this::lamMoiForm);

        panel.add(btnThem);
        panel.add(btnSua);
        panel.add(btnXoa);
        panel.add(btnLamMoi);

        return panel;
    }

    // THÊM METHOD STYLE BUTTON
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Thêm hiệu ứng bo góc
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        // Thêm hiệu ứng hover (đổi màu khi di chuột vào)
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
    }

    // ================= XỬ LÝ NGHIỆP VỤ =================

    private void themSinhVien(ActionEvent e) {
        SinhVien sv = layDuLieuTuForm();
        if (sv == null) return;

        if (timDongTheoMa(sv.getMaSV()) != -1) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        themDongVaoBang(sv);
        lamMoiForm(null);
    }

    private void suaSinhVien(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên cần sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SinhVien sv = layDuLieuTuForm();
        if (sv == null) return;

        int dongTrung = timDongTheoMa(sv.getMaSV());
        if (dongTrung != -1 && dongTrung != row) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên đã tồn tại ở dòng khác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        capNhatDongTrongBang(row, sv);
        lamMoiForm(null);
    }

    private void xoaSinhVien(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên cần xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int xacNhan = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sinh viên này?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (xacNhan == JOptionPane.YES_OPTION) {
            model.removeRow(row);
            lamMoiForm(null);
        }
    }

    private void lamMoiForm(ActionEvent e) {
        txtMaSV.setText("");
        txtHoTen.setText("");
        txtChuyenCan.setText("");
        txtGiuaKy.setText("");
        txtCuoiKy.setText("");
        table.clearSelection();
        txtMaSV.requestFocus();
    }

    // ================= HÀM HỖ TRỢ =================

    // Đưa dữ liệu dòng đang chọn trong bảng lên form
    private void hienThiLenForm() {
        int row = table.getSelectedRow();
        if (row == -1) return;
        txtMaSV.setText(model.getValueAt(row, 0).toString());
        txtHoTen.setText(model.getValueAt(row, 1).toString());
        txtChuyenCan.setText(model.getValueAt(row, 2).toString());
        txtGiuaKy.setText(model.getValueAt(row, 3).toString());
        txtCuoiKy.setText(model.getValueAt(row, 4).toString());
    }

    // Tìm dòng theo mã sinh viên, trả về -1 nếu không có
    private int timDongTheoMa(String maSV) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equalsIgnoreCase(maSV)) {
                return i;
            }
        }
        return -1;
    }

    // Thêm dòng mới vào bảng từ đối tượng SinhVien
    private void themDongVaoBang(SinhVien sv) {
        model.addRow(new Object[]{
                sv.getMaSV(),
                sv.getHoTen(),
                df.format(sv.getDiemChuyenCan()),
                df.format(sv.getDiemGiuaKy()),
                df.format(sv.getDiemCuoiKy()),
                df.format(sv.tinhDiemTB()),
                sv.xepLoai()
        });
    }

    // Cập nhật dòng đã chọn trong bảng từ đối tượng SinhVien
    private void capNhatDongTrongBang(int row, SinhVien sv) {
        model.setValueAt(sv.getMaSV(), row, 0);
        model.setValueAt(sv.getHoTen(), row, 1);
        model.setValueAt(df.format(sv.getDiemChuyenCan()), row, 2);
        model.setValueAt(df.format(sv.getDiemGiuaKy()), row, 3);
        model.setValueAt(df.format(sv.getDiemCuoiKy()), row, 4);
        model.setValueAt(df.format(sv.tinhDiemTB()), row, 5);
        model.setValueAt(sv.xepLoai(), row, 6);
    }

    // Đọc và kiểm tra dữ liệu từ form, trả về đối tượng SinhVien hoặc null nếu lỗi
    private SinhVien layDuLieuTuForm() {
        String maSV = txtMaSV.getText().trim();
        String hoTen = txtHoTen.getText().trim();

        if (maSV.isEmpty() || hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ Mã SV và Họ tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        double chuyenCan, giuaKy, cuoiKy;
        try {
            chuyenCan = Double.parseDouble(txtChuyenCan.getText().trim());
            giuaKy = Double.parseDouble(txtGiuaKy.getText().trim());
            cuoiKy = Double.parseDouble(txtCuoiKy.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Điểm phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (chuyenCan < 0 || chuyenCan > 10 || giuaKy < 0 || giuaKy > 10 || cuoiKy < 0 || cuoiKy > 10) {
            JOptionPane.showMessageDialog(this, "Điểm phải nằm trong khoảng từ 0 đến 10!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return new SinhVien(maSV, hoTen, chuyenCan, giuaKy, cuoiKy);
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new QuanLyDiemSinhVien().setVisible(true);
        });
    }
}
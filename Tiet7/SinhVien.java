/**
 * Class model đại diện cho một Sinh viên.
 * Chứa dữ liệu và logic tính điểm trung bình + xếp loại.
 */
public class SinhVien {

    private String maSV;
    private String hoTen;
    private double diemChuyenCan;
    private double diemGiuaKy;
    private double diemCuoiKy;

    public SinhVien(String maSV, String hoTen, double diemChuyenCan, double diemGiuaKy, double diemCuoiKy) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diemChuyenCan = diemChuyenCan;
        this.diemGiuaKy = diemGiuaKy;
        this.diemCuoiKy = diemCuoiKy;
    }

    // ===== Getter / Setter =====
    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getDiemChuyenCan() {
        return diemChuyenCan;
    }

    public void setDiemChuyenCan(double diemChuyenCan) {
        this.diemChuyenCan = diemChuyenCan;
    }

    public double getDiemGiuaKy() {
        return diemGiuaKy;
    }

    public void setDiemGiuaKy(double diemGiuaKy) {
        this.diemGiuaKy = diemGiuaKy;
    }

    public double getDiemCuoiKy() {
        return diemCuoiKy;
    }

    public void setDiemCuoiKy(double diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }

    /**
     * Tính điểm trung bình:
     * DTB = chuyên cần*0.1 + giữa kỳ*0.3 + cuối kỳ*0.6
     */
    public double tinhDiemTB() {
        return diemChuyenCan * 0.1 + diemGiuaKy * 0.3 + diemCuoiKy * 0.6;
    }

    /**
     * Xếp loại dựa trên điểm trung bình:
     * >= 8.5 Giỏi | >= 7 Khá | >= 5 Trung bình | còn lại Yếu
     */
    public String xepLoai() {
        double dtb = tinhDiemTB();
        if (dtb >= 8.5) return "Giỏi";
        if (dtb >= 7) return "Khá";
        if (dtb >= 5) return "Trung bình";
        return "Yếu";
    }
}
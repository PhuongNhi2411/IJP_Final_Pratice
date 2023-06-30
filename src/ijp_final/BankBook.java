package ijp_final;

import java.util.Date;

public class BankBook {
	
    String maSoTK;
    String hoTen;
    String cmnd;
    String diaChi;
    String soDienThoai;
    Date ngayGuiTK;
    float soTien;
    String kyHan;
    int pTTaiTuc;

    public BankBook(String maSoTK, String hoTen, String cmnd, String diaChi, String soDienThoai, Date ngayGuiTK, float soTien, String kyHan, int pTTaiTuc) {
        this.maSoTK = maSoTK;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.ngayGuiTK = ngayGuiTK;
        this.soTien = soTien;
        this.kyHan = kyHan;
        this.pTTaiTuc = pTTaiTuc;
    }
    
    public BankBook() {
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Mã sổ tiết kiệm: " + maSoTK +
                ", Họ tên khách hàng: " + hoTen +
                ", Số CMND/CCCD/Hộ chiếu: " + cmnd +
                ", Địa chỉ: " + diaChi +
                ", Số điện thoại: " + soDienThoai +
                ", Ngày gửi tiết kiệm: " + ngayGuiTK +
                ", Số tiền gửi: " + soTien +
                ", Kỳ hạn gửi tiết kiệm: " + kyHan +
                ", Phương thức tái tục: " + pTTaiTuc;
    }
}


package giaodienchuan.model.BackEnd.QuanLyLoaiTour;
// package giaodienchuan.model.BackEnd.QuanLyLoaiTour;
public class LoaiTour {
    String MaLT, TenLT, MoTa;
    // Phương thức khởi tạo có tham só
    public LoaiTour(String MaLT, String TenLT, String MoTa) {
        this.MaLT = MaLT;
        this.TenLT = TenLT;
        this.MoTa = MoTa;
    }
// Phương thức thiết lập 
    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getMaLT() {
        return MaLT;
    }

    public void setMaLT(String MaLT) {
        this.MaLT = MaLT;
    }

    public String getTenLT() {
        return TenLT;
    }

    public void setTenLT(String TenLT) {
        this.TenLT = TenLT;
    }
}

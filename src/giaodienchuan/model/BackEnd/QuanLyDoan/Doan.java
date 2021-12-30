
package giaodienchuan.model.BackEnd.QuanLyDoan;

/**
 *
 * @author DELL
 */
public class Doan {
    String MaDoan, TenDoan, DiaChi;
    
    public Doan(String MaDoan, String TenDoan, String DiaChi) {
        this.MaDoan = MaDoan;
        this.TenDoan = TenDoan;
        this.DiaChi = DiaChi;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getMaDoan() {
        return MaDoan;
    }

    public void setMaDoan(String MaDoan) {
        this.MaDoan = MaDoan;
    }

    public String getTenDoan() {
        return TenDoan;
    }

    public void setTenDoan(String TenDoan) {
        this.TenDoan = TenDoan;
    }
}

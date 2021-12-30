package giaodienchuan.model.BackEnd.QuanLyTour;

public class Tour {

    String MaT, MaLT, TenT, fileNameHinhAnh,MaTG,MaDoan;
    float DonGia;
    int SoLuong, TrangThai;

    public Tour(String MaT, String MaLT, String TenT, float DonGia, int SoLuong, String url, int TrangThai, String MaDoan) {
        this.MaT = MaT;
        this.MaLT = MaLT;
        this.TenT = TenT;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.fileNameHinhAnh = url;
        this.TrangThai = TrangThai;
        this.MaDoan=MaDoan;
    }

    public String getFileNameHinhAnh() {
        return fileNameHinhAnh;
    }

    public void setFileNameHinhAnh(String fileNameHinhAnh) {
        this.fileNameHinhAnh = fileNameHinhAnh;
    }
    
    public String getMaT() {
        return MaT;
    }

    public void setMaT(String MaT) {
        this.MaT = MaT;
    }

    public String getMaLT() {
        return MaLT;
    }

    public void setMaLT(String MaLT) {
        this.MaLT = MaLT;
    }

    public String getTenT() {
        return TenT;
    }

    public void setTenT(String TenT) {
        this.TenT = TenT;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
    public String getMaDoan(){
        return MaDoan;
    }
    
    public void setMaDoan(String MaDoan){
        this.MaDoan=MaDoan;
    }
}

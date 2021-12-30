package giaodienchuan.model.BackEnd.QuanLyChiTietHoaDon;

import giaodienchuan.model.BackEnd.QuanLyHoaDon.HoaDon;
import giaodienchuan.model.BackEnd.QuanLyHoaDon.QuanLyHoaDonBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.QuanLyTourBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.Tour;
import java.time.LocalDate;
import java.util.ArrayList;

public class QuanLyChiTietHoaDonBUS {

    ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();
    private QuanLyChiTietHoaDonDAO qlcthdDAO = new QuanLyChiTietHoaDonDAO();
    private QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
    private QuanLyTourBUS qltBUS = new QuanLyTourBUS();

    public QuanLyChiTietHoaDonBUS() {
        dscthd = qlcthdDAO.readDB();
    }

    public ArrayList<ChiTietHoaDon> getDscthd() {
        return this.dscthd;
    }

    public void readDB() {
        dscthd = qlcthdDAO.readDB();
    }

    public ChiTietHoaDon getChiTiet(String mahd, String mat) {
        for (ChiTietHoaDon ct : dscthd) {
            if (ct.getMaTour().equals(mat) && ct.getMaHoaDon().equals(mahd)) {
                return ct;
            }
        }
        return null;
    }
    
    public ArrayList<ChiTietHoaDon> getAllChiTiet(String mahd) {
        ArrayList<ChiTietHoaDon> result = new ArrayList<>();
        for (ChiTietHoaDon ct : dscthd) {
            if (ct.getMaHoaDon().equals(mahd)) {
                result.add(ct);
            }
        }
        return result;
    }

    public Boolean add(ChiTietHoaDon ct) {
        int soLuongChiTietMoi = ct.getSoLuong();

        // tìm các chi tiết cùng mã, và tính tổng số lượng
        ArrayList<ChiTietHoaDon> toRemove = new ArrayList<>();
        int tongSoLuong = ct.getSoLuong();

        for (ChiTietHoaDon cthd : dscthd) {
            if (cthd.getMaHoaDon().equals(ct.getMaHoaDon()) && cthd.getMaTour().equals(ct.getMaTour())) {
                tongSoLuong += cthd.getSoLuong();
                toRemove.add(cthd);
            }
        }
        // xóa chi tiết cũ cùng mã
        dscthd.removeAll(toRemove);
        qlcthdDAO.delete(ct.getMaHoaDon(), ct.getMaTour());

        // thêm chi tiết mới có số lượng = tổng số lượng tìm ở trên
        ct.setSoLuong(tongSoLuong);
        Boolean success = qlcthdDAO.add(ct);
        if (success) {
            dscthd.add(ct);
            // update số lượng bên bảng sản phẩm
            updateSoLuongTour(ct.getMaTour(), -soLuongChiTietMoi);
            // update tổng tiền hóa đơn
            updateTongTien(ct.getMaHoaDon());
            return true;
        }
        return false;
    }

    public Boolean add(String maHoaDon, String maTour, int soLuong, float donGia) {
        ChiTietHoaDon hd = new ChiTietHoaDon(maHoaDon, maTour, soLuong, donGia);
        return add(hd);
    }

    public Boolean update(String maHoaDon, String maTour, int soLuong, float donGia) {
        ChiTietHoaDon hd = new ChiTietHoaDon(maHoaDon, maTour, soLuong, donGia);
        return update(hd);
    }

    public Boolean update(ChiTietHoaDon hd) {
        Boolean success = qlcthdDAO.update(hd);
        if (success) {
            for (ChiTietHoaDon cthd : dscthd) {
                if (cthd.getMaHoaDon().equals(hd.getMaHoaDon())) {
                    cthd = hd;
                }
            }
            updateTongTien(hd.getMaHoaDon());
        }

        return success;
    }

    private Boolean updateTongTien(String _mahd) {
        float tong = 0;
        for (ChiTietHoaDon ct : dscthd) {
            if (ct.getMaHoaDon().equals(_mahd)) {
                tong += ct.getSoLuong() * ct.getDonGia();
            }
        }
        Boolean success = qlhdBUS.updateTongTien(_mahd, tong);
        return success;
    }

    private Boolean updateSoLuongTour(String _mat, int _soLuongThayDoi) {
        for (Tour t : qltBUS.getDst()) {
            if (t.getMaT().equals(_mat)) {
                return qltBUS.updateSoLuong(_mat, t.getSoLuong() + _soLuongThayDoi);
            }
        }
        return false;
    }

    public Boolean delete(String _maHoaDon, String _maTour) {
        Boolean success = qlcthdDAO.delete(_maHoaDon, _maTour);
        if (success) {
            for (ChiTietHoaDon cthd : dscthd) {
                if (cthd.getMaHoaDon().equals(_maHoaDon) && cthd.getMaTour().equals(_maTour)) {
                    updateSoLuongTour(_maTour, cthd.getSoLuong());
                    dscthd.remove(cthd);
                    updateTongTien(_maHoaDon);
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean deleteAll(String _maHoaDon) {
        Boolean success = qlcthdDAO.deleteAll(_maHoaDon);
        if (success) {
            for (ChiTietHoaDon cthd : dscthd) {
                if (cthd.getMaHoaDon().equals(_maHoaDon)) {
                    dscthd.remove(cthd);
                }
            }
            updateTongTien(_maHoaDon);
            return true;
        }
        return false;
    }

    public ArrayList<ChiTietHoaDon> search(String type, String keyword, int soLuong1, int soLuong2, float thanhTien1, float thanhTien2) {
        ArrayList<ChiTietHoaDon> result = new ArrayList<>();

        dscthd.forEach((hd) -> {
            switch (type) {
                case "Tất cả":
                    if (hd.getMaHoaDon().toLowerCase().contains(keyword.toLowerCase())
                            || hd.getMaTour().toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(hd.getSoLuong()).toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(hd.getDonGia()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }

                    break;

                case "Mã hóa đơn":
                    if (hd.getMaHoaDon().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;

                case "Mã tour":
                    if (hd.getMaTour().toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;

                case "Số lượng":
                    if (String.valueOf(hd.getSoLuong()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;

                case "Đơn giá":
                    if (String.valueOf(hd.getDonGia()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
                    break;
            }
        });

        for (int i = result.size() - 1; i >= 0; i--) {
            ChiTietHoaDon ct = result.get(i);
            int sl = ct.getSoLuong();
            float tt = ct.getDonGia() * sl;

            Boolean soLuongKhongThoa = (soLuong1 != -1 && sl < soLuong1) || (soLuong2 != -1 && sl > soLuong2);
            Boolean donGiaKhongThoa = (thanhTien1 != -1 && tt < thanhTien1) || (thanhTien2 != -1 && tt > thanhTien2);

            if (soLuongKhongThoa || donGiaKhongThoa) {
                result.remove(ct);
            }
        }
        return result;
    }

}

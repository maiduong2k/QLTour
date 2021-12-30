package giaodienchuan.model.BackEnd.QuanLyTour;

import java.util.ArrayList;

public class QuanLyTourBUS {

    private ArrayList<Tour> dst = new ArrayList<>();
    QuanLyTourDAO qltDAO = new QuanLyTourDAO();

    public QuanLyTourBUS() {
        dst = qltDAO.readDB();
    }

    public void showConsole() {
        dst.forEach((t) -> {
            System.out.print(t.getMaT() + " ");
            System.out.print(t.getMaLT() + " ");
            System.out.println(t.getTenT() + " ");
            System.out.println(t.getDonGia() + " ");
            System.out.println(t.getSoLuong() + " ");
            System.out.println(t.getTrangThai() + " ");
            System.out.println(t.getMaDoan());
        });
    }

    public String[] getHeaders() {
        return new String[]{"Mã tour", "Mã loại", "Tên", "Đơn giá", "Số lượng", "Trạng thái", "Mã đoàn"};
    }

    public void readDB() {
        dst = qltDAO.readDB();
    }

    public String getNextID() {
        return "T" + String.valueOf(this.dst.size() + 1);
    }

    public Tour getTour(String mat) {
        for (Tour t : dst) {
            if (t.getMaT().equals(mat)) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Tour> search(String value, String type, int soluong1, int soluong2, float gia1, float gia2, int trangthai) {
        ArrayList<Tour> result = new ArrayList<>();

        dst.forEach((t) -> {
            if (type.equals("Tất cả")) {
                if (t.getMaT().toLowerCase().contains(value.toLowerCase())
                        || t.getMaLT().toLowerCase().contains(value.toLowerCase())
                        || t.getTenT().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(t.getDonGia()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(t.getSoLuong()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf((t.getTrangThai() == 1 ? "Ẩn" : "Hiện")).toLowerCase().contains(value.toLowerCase())
                        || t.getMaDoan().toLowerCase().contains(value.toLowerCase())) {
                    result.add(t);
                }
            } else {
                switch (type) {
                    case "Mã tour":
                        if (t.getMaT().toLowerCase().contains(value.toLowerCase())) {
                            result.add(t);
                        }
                        break;
                    case "Mã loại":
                        if (t.getMaLT().toLowerCase().contains(value.toLowerCase())) {
                            result.add(t);
                        }
                        break;
                    case "Tên":
                        if (t.getTenT().toLowerCase().contains(value.toLowerCase())) {
                            result.add(t);
                        }
                        break;
                    case "Đơn giá":
                        if (String.valueOf(t.getDonGia()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(t);
                        }
                        break;
                    case "Số lượng":
                        if (String.valueOf(t.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(t);
                        }
                        break;
                    case "Trạng thái":
                        String tt = (t.getTrangThai() == 1 ? "Ẩn" : "Hiện");
                        if (String.valueOf(tt).toLowerCase().contains(value.toLowerCase())) {
                            result.add(t);
                        }
                        break;
                    case "Mã đoàn":
                        if (t.getMaDoan().toLowerCase().contains(value.toLowerCase())) {
                            result.add(t);
                        }
                }
            }
        });

        for (int i = result.size() - 1; i >= 0; i--) {
            Tour t = result.get(i);
            int soluong = t.getSoLuong();
            float gia = t.getDonGia();
            int tt = t.getTrangThai();
            Boolean soLuongKhongThoa = (soluong1 != -1 && soluong < soluong1) || (soluong2 != -1 && soluong > soluong2);
            Boolean giaKhongThoa = (gia1 != -1 && gia < gia1) || (gia2 != -1 && gia > gia2);
            Boolean trangThaiKhongThoa = (trangthai != -1) && tt != trangthai;

            if (soLuongKhongThoa || giaKhongThoa || trangThaiKhongThoa) {
                result.remove(i);
            }
        }

        return result;
    }

    public Boolean add(Tour t) {
        Boolean ok = qltDAO.add(t);

        if (ok) {
            dst.add(t);
        }
        return ok;
    }

    public Boolean add(String mat, String malt, String tent, float dongia, int soluong, String filename, int trangthai, String madoan) {
        Tour t = new Tour(mat, malt, tent, dongia, soluong, filename, trangthai,madoan);
        return add(t);
    }

    public Boolean delete(String mat) {
        Boolean ok = qltDAO.delete(mat);

        if (ok) {
            for (int i = (dst.size() - 1); i >= 0; i--) {
                if (dst.get(i).getMaT().equals(mat)) {
                    dst.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String mat, String malt, String tent, float dongia, int soluong, String filename, int trangthai, String madoan) {
        Boolean ok = qltDAO.update(mat, malt, tent, dongia, soluong, filename, trangthai, madoan);

        if (ok) {
            dst.forEach((t) -> {
                if (t.getMaT().equals(mat)) {
                    t.setMaLT(malt);
                    t.setTenT(tent);
                    t.setDonGia(dongia);
                    t.setSoLuong(soluong);
                    t.setFileNameHinhAnh(filename);
                    t.setTrangThai(trangthai);
                    t.setMaDoan(madoan);
                }
            });
        }

        return ok;
    }

    public Boolean updateSoLuong(String mat, int soluong) {
        Boolean ok = qltDAO.updateSoLuong(mat, soluong);

        if (ok) {
            dst.forEach((t) -> {
                if (t.getMaT().equals(mat)) {
                    t.setSoLuong(soluong);
                }
            });
        }

        return ok;
    }

    public Boolean updateTrangThai(String mat, int trangthai) {
        Boolean ok = qltDAO.updateTrangThai(mat, trangthai);

        if (ok) {
            dst.forEach((t) -> {
                if (t.getMaT().equals(mat)) {
                    t.setTrangThai(trangthai);
                }
            });
        }

        return ok;
    }

    public ArrayList<Tour> getDst() {
        return dst;
    }
}

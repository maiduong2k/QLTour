package giaodienchuan.model.BackEnd.QuanLyKSan;

import java.util.ArrayList;

public class QuanLyKhachSanBUS {

    public ArrayList<KhachSan> dsks = new ArrayList<>();
    QuanLyKhachSanDAO qlksDAO = new QuanLyKhachSanDAO();
    
    public QuanLyKhachSanBUS() {
        dsks = qlksDAO.readDB();
    }

    public void show() {
        dsks.forEach((ks) -> {
            System.out.print(ks.getMaKS() + " ");
            System.out.print(ks.getTenKS() + " ");
            System.out.println(ks.getDiaChi() + " ");
            System.out.println(ks.getSDT() + " ");
            System.out.println(ks.getFax());
        });
    }

    public void readDB() {
        dsks = qlksDAO.readDB();
    }
    
    public String getNextID() {
        return "KS" + String.valueOf(this.dsks.size() + 1);
    }

    public KhachSan getKhachSan(String maks) {
        for (KhachSan ks : dsks) {
            if (ks.getMaKS().equals(maks)) {
                return ks;
            }
        }
        return null;
    }

    public ArrayList<KhachSan> search(String value, String type) {
        ArrayList<KhachSan> result = new ArrayList<>();

        dsks.forEach((ks) -> {
            if (type.equals("Tất cả")) {
                if (ks.getMaKS().toLowerCase().contains(value.toLowerCase())
                        || ks.getTenKS().toLowerCase().contains(value.toLowerCase())
                        || ks.getDiaChi().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(ks.getSDT()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(ks.getFax()).toLowerCase().contains(value.toLowerCase())) {
                    result.add(ks);
                }
            } else {
                switch (type) {
                    case "Mã khách sạn":
                        if (ks.getMaKS().toLowerCase().contains(value.toLowerCase())) {
                            result.add(ks);
                        }
                        break;
                    case "Tên khách sạn":
                        if (ks.getTenKS().toLowerCase().contains(value.toLowerCase())) {
                            result.add(ks);
                        }
                        break;
                    case "Địa chỉ":
                        if (ks.getDiaChi().toLowerCase().contains(value.toLowerCase())) {
                            result.add(ks);
                        }
                        break;
                    case "SĐT":
                        if (String.valueOf(ks.getSDT()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(ks);
                        }
                        break;
                    case "Fax":
                        if (String.valueOf(ks.getFax()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(ks);
                        }
                        break;
                }
            }

        });

        return result;
    }

    public Boolean add(KhachSan ks) {
        qlksDAO = new QuanLyKhachSanDAO();
        Boolean ok = qlksDAO.add(ks);

        if (ok) {
            dsks.add(ks);
        }
        return ok;
    }

    public Boolean add(String ma, String ten, String diachi, String sdt, String fax) {
        KhachSan ks = new KhachSan(ma, ten, diachi, sdt, fax);
        return add(ks);
    }

    public Boolean delete(String maks) {
        qlksDAO = new QuanLyKhachSanDAO();
        Boolean ok = qlksDAO.delete(maks);

        if (ok) {
            for (int i = (dsks.size() - 1); i >= 0; i--) {
                if (dsks.get(i).getMaKS().equals(maks)) {
                    dsks.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String maks, String tenks, String diachi, String sdt, String fax) {
        qlksDAO = new QuanLyKhachSanDAO();
        Boolean ok = qlksDAO.update(maks, tenks, diachi, sdt, fax);

        if (ok) {
            dsks.forEach((ks) -> {
                if (ks.getMaKS().equals(maks)) {
                    ks.setTenKS(tenks);
                    ks.setDiaChi(diachi);
                    ks.setSDT(sdt);
                    ks.setFax(fax);
                }
            });
        }

        return ok;
    }

    public ArrayList<KhachSan> getDsks() {
        return dsks;
    }
}

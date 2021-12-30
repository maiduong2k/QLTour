package giaodienchuan.model.BackEnd.QuanLyLoaiTour;

import java.util.ArrayList;

public class QuanLyLoaiTourBUS {

    private ArrayList<LoaiTour> dslt = new ArrayList<>();
    
    private QuanLyLoaiTourDAO qlltDAO = new QuanLyLoaiTourDAO();

    public QuanLyLoaiTourBUS() {
        dslt = qlltDAO.readDB();
    }

    public void showConsole() {
        dslt.forEach((lt) -> {
            System.out.print(lt.getMaLT() + " ");
            System.out.print(lt.getTenLT());
        });
    }

    public String[] getHeaders() {
        return new String[]{"Mã loại", "Tên loại", "Mô tả"};
    }
    
    public String getNextID() {
        return "LT" + String.valueOf(this.dslt.size() + 1);
    }

    public void readDB() {
        dslt = qlltDAO.readDB();
    }
    
    public LoaiTour getLoaiTour(String maloai) {
        for (LoaiTour lt : dslt) {
            if (lt.getMaLT().equals(maloai)) {
                return lt;
            }
        }
        return null;
    }

    public ArrayList<LoaiTour> search(String value, String type) {
        // Phương pháp tìm từ database
//        QuanLyTourDAO qltDB = new QuanLyTourDAO();
//        dst = qltDB.search(columnName, value);
//        qltDB.close();

        // phương pháp tìm từ arraylist
        ArrayList<LoaiTour> result = new ArrayList<>();

        dslt.forEach((lt) -> {
            if (type.equals("Tất cả")) {
                if (lt.getMaLT().toLowerCase().contains(value.toLowerCase())
                        || lt.getTenLT().toLowerCase().contains(value.toLowerCase())
                        || lt.getMoTa().toLowerCase().contains(value.toLowerCase()))  {
                    result.add(lt);
                }
            } else {
                switch (type) {
                    case "Mã loại":
                        if (lt.getMaLT().toLowerCase().contains(value.toLowerCase())) {
                            result.add(lt);
                        }
                        break;
                    case "Tên loại":
                        if (lt.getTenLT().toLowerCase().contains(value.toLowerCase())) {
                            result.add(lt);
                        }
                        break;
                    case "Mô tả":
                        if (lt.getMoTa().toLowerCase().contains(value.toLowerCase())) {
                            result.add(lt);
                        }
                        break;

                }
            }

        });

        return result;
    }

    public Boolean add(LoaiTour lt) {
        Boolean ok = qlltDAO.add(lt);

        if (ok) {
            dslt.add(lt);
        }
        return ok;
    }

    public Boolean add(String malt, String tenlt, String mota) {
        LoaiTour lt = new LoaiTour(malt, tenlt, mota);
        return add(lt);
    }

    public Boolean delete(String malt) {
        Boolean ok = qlltDAO.delete(malt);

        if (ok) {
            for (int i = (dslt.size() - 1); i >= 0; i--) {
                if (dslt.get(i).getMaLT().equals(malt)) {
                    dslt.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String malt, String tenlt, String mota) {
        Boolean ok = qlltDAO.update(malt, tenlt, mota);

        if (ok) {
            dslt.forEach((lt) -> {
                if (lt.getMaLT().equals(malt)) {
                    lt.setTenLT(tenlt);
                    lt.setMoTa(mota);
                }
            });
        }

        return ok;
    }

    public ArrayList<LoaiTour> getDslt() {
        return dslt;
    }
}

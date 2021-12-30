
package giaodienchuan.model.BackEnd.QuanLyDoan;

import java.util.ArrayList;
/**
 *
 * @author DELL
 */
public class QuanLyDoanBUS {
    
    private ArrayList<Doan> dsdoan = new ArrayList<>();
    private QuanLyDoanDAO qldoanDAO = new QuanLyDoanDAO();

    public QuanLyDoanBUS() {
        dsdoan = qldoanDAO.readDB();
    }

    public void showConsole() {
        dsdoan.forEach((doan) -> {
            System.out.print(doan.getMaDoan() + " ");
            System.out.print(doan.getTenDoan());
        });
    }

    public String[] getHeaders() {
        return new String[]{"Mã đoàn", "Tên đoàn", "Địa chỉ"};
    }
    
    public String getNextID() {
        return "D" + String.valueOf(this.dsdoan.size() + 1);
    }

    public void readDB() {
        dsdoan = qldoanDAO.readDB();
    }
    
    public Doan getDoan(String madoan) {
        for (Doan doan : dsdoan) {
            if (doan.getMaDoan().equals(madoan)) {
                return doan;
            }
        }
        return null;
    }

    public ArrayList<Doan> search(String value, String type) {
        // Phương pháp tìm từ database
//        QuanLySanPhamDAO qlspDB = new QuanLySanPhamDAO();
//        dssp = qlspDB.search(columnName, value);
//        qlspDB.close();

        // phương pháp tìm từ arraylist
        ArrayList<Doan> result = new ArrayList<>();

        dsdoan.forEach((doan) -> {
            if (type.equals("Tất cả")) {
                if (doan.getMaDoan().toLowerCase().contains(value.toLowerCase())
                        || doan.getTenDoan().toLowerCase().contains(value.toLowerCase())
                        || doan.getDiaChi().toLowerCase().contains(value.toLowerCase()))  {
                    result.add(doan);
                }
            } else {
                switch (type) {
                    case "Mã đoàn":
                        if (doan.getMaDoan().toLowerCase().contains(value.toLowerCase())) {
                            result.add(doan);
                        }
                        break;
                    case "Tên đoàn":
                        if (doan.getTenDoan().toLowerCase().contains(value.toLowerCase())) {
                            result.add(doan);
                        }
                        break;
                    case "Địa chỉ":
                        if (doan.getDiaChi().toLowerCase().contains(value.toLowerCase())) {
                            result.add(doan);
                        }
                        break;

                }
            }

        });

        return result;
    }

    public Boolean add(Doan doan) {
        Boolean ok = qldoanDAO.add(doan);

        if (ok) {
            dsdoan.add(doan);
        }
        return ok;
    }

    public Boolean add(String madoan, String tendoan, String diachi) {
        Doan doan = new Doan(madoan, tendoan, diachi);
        return add(doan);
    }

    public Boolean delete(String madoan) {
        Boolean ok = qldoanDAO.delete(madoan);

        if (ok) {
            for (int i = (dsdoan.size() - 1); i >= 0; i--) {
                if (dsdoan.get(i).getMaDoan().equals(madoan)) {
                    dsdoan.remove(i);
                }
            }
        }
        return ok;
    }

    public Boolean update(String madoan, String tendoan, String diachi) {
        Boolean ok = qldoanDAO.update(madoan, tendoan, diachi);

        if (ok) {
            dsdoan.forEach((doan) -> {
                if (doan.getMaDoan().equals(madoan)) {
                    doan.setTenDoan(tendoan);
                    doan.setDiaChi(diachi);
                }
            });
        }

        return ok;
    }

    public ArrayList<Doan> getDsdoan() {
        return dsdoan;
    }
}

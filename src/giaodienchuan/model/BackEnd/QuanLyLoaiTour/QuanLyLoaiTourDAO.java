package giaodienchuan.model.BackEnd.QuanLyLoaiTour;

import giaodienchuan.model.BackEnd.ConnectionDB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuanLyLoaiTourDAO {

    ConnectionDB qlltConnection;

    public QuanLyLoaiTourDAO() {

    }

    public ArrayList<LoaiTour> readDB() {
        qlltConnection = new ConnectionDB();
        ArrayList<LoaiTour> dslt = new ArrayList<>();
        try {
            String qry = "SELECT * FROM loaitour";
            ResultSet r = qlltConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String malt = r.getString(1);
                    String tenlt = r.getString(2);
                    String mota = r.getString(3);

                    dslt.add(new LoaiTour(malt, tenlt, mota));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng loại tour");
        } finally {
            qlltConnection.closeConnect();
        }
        return dslt;
    }

    public ArrayList<LoaiTour> search(String columnName, String value) {
        qlltConnection = new ConnectionDB();
        ArrayList<LoaiTour> dslt = new ArrayList<>();

        try {
            String qry = "SELECT * FROM loaitour WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlltConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String malt = r.getString(1);
                    String tenlt = r.getString(2);
                    String mota = r.getString(3);

                    dslt.add(new LoaiTour(malt, tenlt, mota));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng loại tour");
        } finally {
            qlltConnection.closeConnect();
        }

        return dslt;
    }

    public Boolean add(LoaiTour lt) {
        qlltConnection = new ConnectionDB();
        Boolean ok = qlltConnection.sqlUpdate("INSERT INTO `loaitour` (`MaLT`, `TenLT`, `Mota`) VALUES ('"
                + lt.getMaLT() + "', '" + lt.getTenLT() + "', '" + lt.getMoTa()+ "');");
        qlltConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String malt) {
        qlltConnection = new ConnectionDB();
        Boolean ok = qlltConnection.sqlUpdate("DELETE FROM `loaitour` WHERE `loaitour`.`MaLT` = '" + malt + "'");
        qlltConnection.closeConnect();
        return ok;
    }

    public Boolean update(String MaLT, String TenLT, String Mota) {
        qlltConnection = new ConnectionDB();
        Boolean ok = qlltConnection.sqlUpdate("Update loaitour Set TenLT='" + TenLT + "', Mota='" + Mota + "' where MaLT='" + MaLT + "'");
        qlltConnection.closeConnect();
        return ok;
    }

    public void close() {
        qlltConnection.closeConnect();
    }
}

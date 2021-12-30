
package giaodienchuan.model.BackEnd.QuanLyDoan;

import giaodienchuan.model.BackEnd.ConnectionDB.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author DELL
 */
public class QuanLyDoanDAO {
    
    ConnectionDB qldoanConnection;

    public QuanLyDoanDAO() {

    }

    public ArrayList<Doan> readDB() {
        qldoanConnection = new ConnectionDB();
        ArrayList<Doan> dsdoan = new ArrayList<>();
        try {
            String qry = "SELECT * FROM doan";
            ResultSet r = qldoanConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String madoan = r.getString(1);
                    String tendoan = r.getString(2);
                    String diachi = r.getString(3);

                    dsdoan.add(new Doan(madoan, tendoan, diachi));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng đoàn");
        } finally {
            qldoanConnection.closeConnect();
        }
        return dsdoan;
    }

    public ArrayList<Doan> search(String columnName, String value) {
        qldoanConnection = new ConnectionDB();
        ArrayList<Doan> dsdoan = new ArrayList<>();

        try {
            String qry = "SELECT * FROM doan WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qldoanConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String madoan = r.getString(1);
                    String tendoan = r.getString(2);
                    String diachi = r.getString(3);

                    dsdoan.add(new Doan(madoan, tendoan, diachi));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng đoàn");
        } finally {
            qldoanConnection.closeConnect();
        }

        return dsdoan;
    }

    public Boolean add(Doan doan) {
        qldoanConnection = new ConnectionDB();
        Boolean ok = qldoanConnection.sqlUpdate("INSERT INTO `doan` (`MaDoan`, `TenDoan`, `Diachi`) VALUES ('"
                + doan.getMaDoan() + "', '" + doan.getTenDoan() + "', '" + doan.getDiaChi()+ "');");
        qldoanConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String madoan) {
        qldoanConnection = new ConnectionDB();
        Boolean ok = qldoanConnection.sqlUpdate("DELETE FROM `doan` WHERE `doan`.`MaDoan` = '" + madoan + "'");
        qldoanConnection.closeConnect();
        return ok;
    }

    public Boolean update(String MaDoan, String TenDoan, String Diachi) {
        qldoanConnection = new ConnectionDB();
        Boolean ok = qldoanConnection.sqlUpdate("Update doan Set TenDoan='" + TenDoan + "', Diachi='" + Diachi + "' where MaDoan='" + MaDoan + "'");
        qldoanConnection.closeConnect();
        return ok;
    }

    public void close() {
        qldoanConnection.closeConnect();
    }
}

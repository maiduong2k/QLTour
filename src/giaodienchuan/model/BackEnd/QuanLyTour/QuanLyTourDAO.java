package giaodienchuan.model.BackEnd.QuanLyTour;

import giaodienchuan.model.BackEnd.ConnectionDB.ConnectionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuanLyTourDAO {

    ConnectionDB qltConnection;

    public QuanLyTourDAO() {

    }

    public ArrayList<Tour> readDB() {
        qltConnection = new ConnectionDB();
        ArrayList<Tour> dst = new ArrayList<>();
        try {
            String qry = "SELECT * FROM tour";
            ResultSet r = qltConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String mat = r.getString("MaT");
                    String loait = r.getString("MaLT");
                    String tent = r.getString("TenT");
                    float dongia = r.getFloat("DonGia");
                    int soluong = r.getInt("SoLuong");
                    String url = r.getString("HinhAnh");
                    int trangthai = r.getInt("TrangThai");
                    String madoan = r.getString("MaDoan");
                    dst.add(new Tour(mat, loait, tent, dongia, soluong, url, trangthai,madoan));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng tour");
        } finally {
            qltConnection.closeConnect();
        }
        return dst;
    }

    public ArrayList<Tour> search(String columnName, String value) {
        qltConnection = new ConnectionDB();
        ArrayList<Tour> dst = new ArrayList<>();

        try {
            String qry = "SELECT * FROM tour WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qltConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String mat = r.getString("MaT");
                    String loait = r.getString("MaLT");
                    String tent = r.getString("TenT");
                    float dongia = r.getFloat("DonGia");
                    int soluong = r.getInt("SoLuong");
                    String url = r.getString("HinhAnh");
                    int trangthai = r.getInt("TrangThai");
                    String madoan = r.getString("MaDoan");
                    dst.add(new Tour(mat, loait, tent, dongia, soluong, url, trangthai,madoan));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng tour");
        } finally {
            qltConnection.closeConnect();
        }

        return dst;
    }

    public Boolean add(Tour t) {
        qltConnection = new ConnectionDB();
        Boolean ok = qltConnection.sqlUpdate("INSERT INTO `tour` (`MaT`, `MaLT`, `TenT`, `DonGia`, `SoLuong`, `HinhAnh`, `TrangThai`, `MaDoan`) VALUES ('"
                + t.getMaT() + "', '"
                + t.getMaLT() + "', '"
                + t.getTenT() + "', '"
                + t.getDonGia() + "', '"
                + t.getSoLuong() + "', '"
                + t.getFileNameHinhAnh() + "', '"
                + t.getTrangThai()+ "', '"
                + t.getMaDoan() + "');");
        qltConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String mat) {
        qltConnection = new ConnectionDB();
        Boolean ok = qltConnection.sqlUpdate("DELETE FROM `tour` WHERE `tour`.`MaT` = '" + mat + "'");
        qltConnection.closeConnect();
        return ok;
    }

    public Boolean update(String MaT, String MaLT, String TenT, float DonGia, int SoLuong, String filename, int trangthai, String MaDoan) {
        qltConnection = new ConnectionDB();
        Boolean ok = qltConnection.sqlUpdate("Update Tour Set "
                + "MaLT='" + MaLT
                + "',TenT='" + TenT
                + "',DonGia='" + DonGia
                + "',SoLuong='" + SoLuong
                + "',HinhAnh='" + filename
                + "',TrangThai='" + trangthai
                + "',MaDoan='" + MaDoan
                + "' where MaT='" + MaT + "'");
        qltConnection.closeConnect();
        return ok;
    }

    public Boolean updateSoLuong(String MaT, int SoLuong) {
        qltConnection = new ConnectionDB();
        Boolean ok = qltConnection.sqlUpdate("Update Tour Set "
                + "SoLuong='" + SoLuong
                + "' where MaT='" + MaT + "'");
        qltConnection.closeConnect();
        return ok;
    }
    
    public Boolean updateTrangThai(String MaT, int trangthai) {
        qltConnection = new ConnectionDB();
        Boolean ok = qltConnection.sqlUpdate("Update Tour Set "
                + "TrangThai='" + trangthai
                + "' where MaT='" + MaT + "'");
        qltConnection.closeConnect();
        return ok;
    }

    public void close() {
        qltConnection.closeConnect();
    }
}

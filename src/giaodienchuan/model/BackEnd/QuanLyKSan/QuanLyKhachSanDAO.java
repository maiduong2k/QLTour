/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodienchuan.model.BackEnd.QuanLyKSan;

;

import giaodienchuan.model.BackEnd.ConnectionDB.ConnectionDB;
import giaodienchuan.model.BackEnd.QuanLyTour.Tour;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */


public class QuanLyKhachSanDAO {

    ConnectionDB qlksConnection;

//    public QuanLyKhachSanDAO(){
//        khachsanDB.logIn("root","");
//    }
    public ArrayList<KhachSan> readDB() {
        ArrayList<KhachSan> dsks = new ArrayList<>();
        qlksConnection = new ConnectionDB();
        try {
            String qry = "SELECT * FROM khachsan";
            ResultSet r = qlksConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String ma = r.getString(1);
                    String ten = r.getString(2);
                    String diachi = r.getString(3);
                    String sdt = r.getString(4);
                    String fax = r.getString(5);

                    dsks.add(new KhachSan(ma, ten, diachi, sdt, fax));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Không thấy data cần tìm trong ResultSet");
        } finally {
            qlksConnection.closeConnect();
        }
        return dsks;
    }

    public ArrayList<KhachSan> search(String columnName, String value) {
        qlksConnection = new ConnectionDB();
        ArrayList<KhachSan> dsks = new ArrayList<>();

        try {
            String qry = "SELECT * FROM tour WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = qlksConnection.sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String maks = r.getString(1);
                    String tenks = r.getString(2);
                    String diachi = r.getString(3);
                    String sdt = r.getString(4);
                    String fax = r.getString(5);
                    dsks.add(new KhachSan(maks, tenks, diachi, sdt, fax));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng tour");
        } finally {
            qlksConnection.closeConnect();
        }

        return dsks;
    }

    public Boolean add(KhachSan ks) {
        qlksConnection = new ConnectionDB();
        Boolean ok = qlksConnection.sqlUpdate("INSERT INTO `khachsan` (`MaKS`, `TenKS`, `DiaChi`,`SDT`,`Fax`) VALUES ('"
                + ks.getMaKS() + "', '" 
                + ks.getTenKS() + "', '" 
                + ks.getDiaChi() + "','" 
                + ks.getSDT() + "','" 
                + ks.getFax() + "');");

        qlksConnection.closeConnect();
        return ok;
    }

    public Boolean delete(String maks) {
        qlksConnection = new ConnectionDB();
        Boolean ok = qlksConnection.sqlUpdate("DELETE FROM `khachsan` WHERE `khachsan`.`MaKS` = '" + maks + "'");
        qlksConnection.closeConnect();
        return ok;
    }

    public Boolean update(String ma, String ten, String diachi, String sdt, String fax) {
        qlksConnection = new ConnectionDB();
        Boolean ok = qlksConnection.sqlUpdate("Update Khachan Set MaKS='" + ma + "',TenKS='" + ten + "',DiaChi='" + diachi + "',SDT='" + sdt + "',Fax='" + fax + "' where MaKS='" + ma + "'");
        qlksConnection.closeConnect();
        return ok;
    }

    public void close() {
        qlksConnection.closeConnect();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodienchuan.model.BackEnd.QuanLyChiTietPN;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhap {

    String ma;
    String maT;
    Integer soLuong;
    Float donGia;

    public ChiTietPhieuNhap() {

    }

    public ChiTietPhieuNhap(String ma, String maT, Integer soLuong, Float donGia) {
        this.ma = ma;
        this.maT = maT;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMa() {
        return ma;
    }

    public String getMaT() {
        return maT;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public Float getDonGia() {
        return donGia;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setMaT(String maT) {
        this.maT = maT;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodienchuan.model.BackEnd.QuanLyKSan;

/**
 *
 * @author Admin
 */
public class KhachSan {
    private String maKS;
    private String tenKS;
    private String diaChi;
    private String SDT;
    private String Fax;
    
    public KhachSan(){
        
    }
    public KhachSan(String maKS,String tenKS, String diaChi, String SDT, String Fax)
    {
        this.maKS=maKS;
        this.tenKS=tenKS;
        this.diaChi=diaChi;
        this.SDT=SDT;
        this.Fax=Fax;
    }
    public KhachSan(KhachSan n)
    {
        this.maKS=n.maKS;
        this.tenKS=n.tenKS;
        this.diaChi=n.diaChi;
        this.SDT=n.SDT;
        this.Fax=n.Fax;
    }

    public String getMaKS() {
        return maKS;
    }

    public String getTenKS() {
        return tenKS;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public String getFax() {
        return Fax;
    }

    public void setMaKS(String maKS) {
        this.maKS = maKS;
    }

    public void setTenKS(String tenKS) {
        this.tenKS = tenKS;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }
        
    
}

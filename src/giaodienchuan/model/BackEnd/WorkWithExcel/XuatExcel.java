/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodienchuan.model.BackEnd.WorkWithExcel;

import giaodienchuan.model.BackEnd.QuanLyChiTietHoaDon.ChiTietHoaDon;
import giaodienchuan.model.BackEnd.QuanLyChiTietHoaDon.QuanLyChiTietHoaDonBUS;
import giaodienchuan.model.BackEnd.QuanLyChiTietPN.ChiTietPhieuNhap;
import giaodienchuan.model.BackEnd.QuanLyChiTietPN.QuanLyChiTietPhieuNhapBUS;
import giaodienchuan.model.BackEnd.QuanLyHoaDon.HoaDon;
import giaodienchuan.model.BackEnd.QuanLyKhachHang.KhachHang;
import giaodienchuan.model.BackEnd.QuanLyKhachHang.QuanLyKhachHangBUS;
import giaodienchuan.model.BackEnd.QuanLyKhuyenMai.QuanLyKhuyenMaiBUS;
import giaodienchuan.model.BackEnd.QuanLyLoaiTour.QuanLyLoaiTourBUS;
import giaodienchuan.model.BackEnd.QuanLyKSan.KhachSan;
import giaodienchuan.model.BackEnd.QuanLyKSan.QuanLyKhachSanBUS;
import giaodienchuan.model.BackEnd.QuanLyNhanVien.NhanVien;
import giaodienchuan.model.BackEnd.QuanLyNhanVien.QuanLyNhanVienBUS;
import giaodienchuan.model.BackEnd.QuanLyQuyen.QuanLyQuyenBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.QuanLyTourBUS;
import giaodienchuan.model.BackEnd.QuanLyTaiKhoan.QuanLyTaiKhoanBUS;
import giaodienchuan.model.BackEnd.QuanLyHoaDon.QuanLyHoaDonBUS;
import giaodienchuan.model.BackEnd.QuanLyKhuyenMai.KhuyenMai;
import giaodienchuan.model.BackEnd.QuanLyLoaiTour.LoaiTour;
import giaodienchuan.model.BackEnd.QuanLyPhieuNhap.PhieuNhap;
import giaodienchuan.model.BackEnd.QuanLyPhieuNhap.QuanLyPhieuNhapBUS;
import giaodienchuan.model.BackEnd.QuanLyQuyen.Quyen;
import giaodienchuan.model.BackEnd.QuanLyTour.Tour;
import giaodienchuan.model.BackEnd.QuanLyTaiKhoan.TaiKhoan;

import giaodienchuan.model.BackEnd.QuanLyDoan.Doan;
import giaodienchuan.model.BackEnd.QuanLyDoan.QuanLyDoanBUS;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Admin
 */
public class XuatExcel {

    FileDialog fd = new FileDialog(new JFrame(), "Xu???t excel", FileDialog.SAVE);

    private String getFile() {
        fd.setFile("untitled.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    // Xu???t file Excel Nh?? cung c???p    
    public void xuatFileExcelNhaCungCap() {
        fd.setTitle("Xu???t d??? li???u kh??ch s???n ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nh?? cung c???p");

            QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();
            ArrayList<KhachSan> list = qlksBUS.getDsks();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? kh??ch s???n");
            row.createCell(2, CellType.STRING).setCellValue("T??n kh??ch s???n");
            row.createCell(3, CellType.STRING).setCellValue("?????a ch???");
            row.createCell(4, CellType.STRING).setCellValue("S??? ??i???n tho???i");
            row.createCell(5, CellType.STRING).setCellValue("Fax");

            for (KhachSan ks : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(ks.getMaKS());
                row.createCell(2, CellType.STRING).setCellValue(ks.getTenKS());
                row.createCell(3, CellType.STRING).setCellValue(ks.getDiaChi());
                row.createCell(4, CellType.STRING).setCellValue(ks.getSDT());
                row.createCell(5, CellType.STRING).setCellValue(ks.getFax());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Xu???t file Excel Nh?? xu???t b???n    
    public void xuatFileExcelNhaXuatBan() {
        fd.setTitle("Xu???t d??? li???u nh?? xu???t b???n ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nh?? xu???t b???n");

            QuanLyDoanBUS qldoanBUS = new QuanLyDoanBUS();
            ArrayList<Doan> list = qldoanBUS.getDsdoan();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? nh?? xu???t b???n");
            row.createCell(2, CellType.STRING).setCellValue("T??n nh?? xu???t b???n");
            row.createCell(3, CellType.STRING).setCellValue("?????a ch???");

            for (Doan doan : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(doan.getMaDoan());
                row.createCell(2, CellType.STRING).setCellValue(doan.getTenDoan());
                row.createCell(3, CellType.STRING).setCellValue(doan.getDiaChi());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xu???t file Excel Nh??n vi??n
    public void xuatFileExcelNhanVien() {
        fd.setTitle("Xu???t d??? li???u nh??n vi??n ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nh??n vi??n");

            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            ArrayList<NhanVien> list = qlnvBUS.getDsnv();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? nh??n vi??n");
            row.createCell(2, CellType.STRING).setCellValue("T??n nh??n vi??n");
            row.createCell(3, CellType.STRING).setCellValue("Ng??y sinh");
            row.createCell(4, CellType.STRING).setCellValue("?????a ch???");
            row.createCell(5, CellType.STRING).setCellValue("S??? ??i???n tho???i");
            row.createCell(6, CellType.STRING).setCellValue("Tr???ng th??i");

            for (NhanVien nv : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(nv.getMaNV());
                row.createCell(2, CellType.STRING).setCellValue(nv.getTenNV());
                row.createCell(3, CellType.STRING).setCellValue(String.valueOf(nv.getNgaySinh()));
                row.createCell(4, CellType.STRING).setCellValue(nv.getDiaChi());
                row.createCell(5, CellType.STRING).setCellValue(nv.getSDT());
                row.createCell(6, CellType.STRING).setCellValue((nv.getTrangThai() == 0 ? "Hi???n" : "???n"));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xu???t file Excel Kh??ch h??ng
    public void xuatFileExcelKhachHang() {
        fd.setTitle("Xu???t d??? li???u kh??ch h??ng ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Kh??ch h??ng");

            QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();
            ArrayList<KhachHang> list = qlkhBUS.getDskh();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? kh??ch h??ng");
            row.createCell(2, CellType.STRING).setCellValue("T??n kh??ch h??ng");
            row.createCell(3, CellType.STRING).setCellValue("?????a ch???");
            row.createCell(4, CellType.STRING).setCellValue("S??? ??i???n tho???i");
            row.createCell(5, CellType.STRING).setCellValue("Tr???ng th??i");

            for (KhachHang kh : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(kh.getMaKH());
                row.createCell(2, CellType.STRING).setCellValue(kh.getTenKH());
                row.createCell(3, CellType.STRING).setCellValue(kh.getDiaChi());
                row.createCell(4, CellType.STRING).setCellValue(kh.getSDT());
                row.createCell(5, CellType.STRING).setCellValue((kh.getTrangThai() == 0 ? "Hi???n" : "???n"));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xu???t file Excel T??i kho???n
    public void xuatFileExcelTaiKhoan() {
        fd.setTitle("Xu???t d??? li???u t??i kho???n ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("T??i kho???n");

            QuanLyTaiKhoanBUS qltkBUS = new QuanLyTaiKhoanBUS();
            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            QuanLyQuyenBUS qlqBUS = new QuanLyQuyenBUS();
            ArrayList<TaiKhoan> list = qltkBUS.getDstk();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("T??n t??i kho???n");
            row.createCell(2, CellType.STRING).setCellValue("M???t kh???u");
            row.createCell(3, CellType.STRING).setCellValue("M?? nh??n vi??n");
            row.createCell(4, CellType.STRING).setCellValue("M?? quy???n");

            for (TaiKhoan tk : list) {
                rownum++;
                row = sheet.createRow(rownum);

                String manv = tk.getMaNV();
                String maq = tk.getMaQuyen();

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(tk.getUsername());
                row.createCell(2, CellType.STRING).setCellValue(tk.getPassword());
                row.createCell(3, CellType.STRING).setCellValue(manv + " - " + qlnvBUS.getNhanVien(manv).getTenNV());
                row.createCell(4, CellType.STRING).setCellValue(maq + " - " + qlqBUS.getQuyen(maq).getTenQuyen());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xu???t file Excel Khuy???n m??i
    public void xuatFileExcelKhuyenMai() {
        fd.setTitle("Xu???t d??? li???u khuy???n m??i ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khuy???n m??i");

            QuanLyKhuyenMaiBUS qlkmBUS = new QuanLyKhuyenMaiBUS();
            ArrayList<KhuyenMai> list = qlkmBUS.getDskm();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? khuy???n m??i");
            row.createCell(2, CellType.STRING).setCellValue("T??n khuy???n m??i");
            row.createCell(3, CellType.NUMERIC).setCellValue("??i???u ki???n");
            row.createCell(4, CellType.NUMERIC).setCellValue("Ph???n tr??m");
            row.createCell(5, CellType.STRING).setCellValue("Ng??y b???t ?????u");
            row.createCell(6, CellType.STRING).setCellValue("Ng??y k???t th??c");

            for (KhuyenMai km : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(km.getMaKM());
                row.createCell(2, CellType.STRING).setCellValue(km.getTenKM());
                row.createCell(3, CellType.NUMERIC).setCellValue(km.getDieuKienKM());
                row.createCell(4, CellType.NUMERIC).setCellValue(km.getPhanTramKM());
                row.createCell(5, CellType.STRING).setCellValue(String.valueOf(km.getNgayBD()));
                row.createCell(6, CellType.STRING).setCellValue(String.valueOf(km.getNgayKT()));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xu???t file Excel S???n ph???m
    public void xuatFileExcelTour() {
        fd.setTitle("Xu???t d??? li???u tour ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Tour");

            QuanLyTourBUS qltBUS = new QuanLyTourBUS();
            QuanLyLoaiTourBUS qllt = new QuanLyLoaiTourBUS();
            QuanLyDoanBUS qldoan = new QuanLyDoanBUS();
            ArrayList<Tour> list = qltBUS.getDst();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? tour");
            row.createCell(2, CellType.STRING).setCellValue("Lo???i tour");
            row.createCell(3, CellType.STRING).setCellValue("T??n");
            row.createCell(4, CellType.NUMERIC).setCellValue("????n gi??");
            row.createCell(5, CellType.NUMERIC).setCellValue("S??? l?????ng");
            row.createCell(6, CellType.STRING).setCellValue("H??nh ???nh");
            row.createCell(7, CellType.STRING).setCellValue("Tr???ng th??i");
            row.createCell(9, CellType.STRING).setCellValue("??o??n");

            for (Tour t : list) {
                rownum++;
                row = sheet.createRow(rownum);

                String maloai = t.getMaLT();
                String madoan = t.getMaDoan();

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(t.getMaT());
                row.createCell(2, CellType.STRING).setCellValue(maloai + " - " + qllt.getLoaiTour(maloai).getTenLT());
                row.createCell(3, CellType.STRING).setCellValue(t.getTenT());
                row.createCell(4, CellType.NUMERIC).setCellValue(t.getDonGia());
                row.createCell(5, CellType.NUMERIC).setCellValue(t.getSoLuong());
                row.createCell(6, CellType.STRING).setCellValue(String.valueOf(t.getFileNameHinhAnh()));
                row.createCell(7, CellType.STRING).setCellValue(t.getTrangThai() == 0 ? "Hi???n" : "???n");
                row.createCell(9, CellType.STRING).setCellValue(madoan + " - " + qldoan.getDoan(madoan).getTenDoan());

            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xu???t file Excel Lo???i s???n ph???m
    public void xuatFileExcelLoaiTour() {
        fd.setTitle("Xu???t d??? li???u lo???i tour ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Lo???i Tour");

            QuanLyLoaiTourBUS qlltBUS = new QuanLyLoaiTourBUS();
            ArrayList<LoaiTour> list = qlltBUS.getDslt();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? Lo???i");
            row.createCell(2, CellType.STRING).setCellValue("T??n lo???i");
            row.createCell(3, CellType.STRING).setCellValue("M?? t???");

            for (LoaiTour lt : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(lt.getMaLT());
                row.createCell(2, CellType.STRING).setCellValue(lt.getTenLT());
                row.createCell(3, CellType.STRING).setCellValue(lt.getMoTa());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    

    // Xu???t file Excel Quy???n
    public void xuatFileExcelQuyen() {
        fd.setTitle("Xu???t d??? li???u quy???n ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Quy???n");

            QuanLyQuyenBUS qlqBUS = new QuanLyQuyenBUS();
            ArrayList<Quyen> list = qlqBUS.getDsq();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? quy???n");
            row.createCell(2, CellType.STRING).setCellValue("T??n quy???n");
            row.createCell(3, CellType.STRING).setCellValue("Chi ti???t quy???n");

            for (Quyen q : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(q.getMaQuyen());
                row.createCell(2, CellType.STRING).setCellValue(q.getTenQuyen());
                row.createCell(3, CellType.STRING).setCellValue(q.getChiTietQuyen());
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xu???t file Excel H??a ????n
    public void xuatFileExcelHoaDon() {
        fd.setTitle("Xu???t d??? li???u h??a ????n ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("H??a ????n");

            QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
            QuanLyChiTietHoaDonBUS qlcthdBUS = new QuanLyChiTietHoaDonBUS();
            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();
            QuanLyKhuyenMaiBUS qlkmBUS = new QuanLyKhuyenMaiBUS();
            QuanLyTourBUS qltBUS = new QuanLyTourBUS();
            ArrayList<HoaDon> list = qlhdBUS.getDshd();

            int rownum = 0;
            int sttHoaDon = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? h??a ????n");
            row.createCell(2, CellType.STRING).setCellValue("M?? nh??n vi??n");
            row.createCell(3, CellType.STRING).setCellValue("M?? kh??ch h??ng");
            row.createCell(4, CellType.STRING).setCellValue("M?? khuy???n m??i");
            row.createCell(5, CellType.STRING).setCellValue("Ng??y l???p");
            row.createCell(6, CellType.STRING).setCellValue("Gi??? l???p");
            row.createCell(7, CellType.STRING).setCellValue("T???ng ti???n");

            row.createCell(8, CellType.STRING).setCellValue("Tour");
            row.createCell(9, CellType.STRING).setCellValue("S??? l?????ng");
            row.createCell(10, CellType.STRING).setCellValue("????n gi??");
            row.createCell(11, CellType.STRING).setCellValue("Th??nh ti???n");

            for (HoaDon hd : list) {
                rownum++;
                sttHoaDon++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(sttHoaDon);
                row.createCell(1, CellType.STRING).setCellValue(hd.getMaHoaDon());
                row.createCell(2, CellType.STRING).setCellValue(hd.getMaNhanVien() + " - " + qlnvBUS.getNhanVien(hd.getMaNhanVien()).getTenNV());
                row.createCell(3, CellType.STRING).setCellValue(hd.getMaKhachHang() + " - " + qlkhBUS.getKhachHang(hd.getMaKhachHang()).getTenKH());
                row.createCell(4, CellType.STRING).setCellValue(hd.getMaKhuyenMai() + " - " + qlkmBUS.getKhuyenMai(hd.getMaKhuyenMai()).getTenKM());
                row.createCell(5, CellType.STRING).setCellValue(String.valueOf(hd.getNgayLap()));
                row.createCell(6, CellType.STRING).setCellValue(String.valueOf(hd.getGioLap()));
                row.createCell(7, CellType.NUMERIC).setCellValue(hd.getTongTien());

                for (ChiTietHoaDon cthd : qlcthdBUS.getAllChiTiet(hd.getMaHoaDon())) {
                    rownum++;
                    row = sheet.createRow(rownum);

                    String mat = cthd.getMaTour();

                    row.createCell(8, CellType.STRING).setCellValue(mat + " - " + qltBUS.getTour(mat).getTenT());
                    row.createCell(9, CellType.NUMERIC).setCellValue(cthd.getSoLuong());
                    row.createCell(10, CellType.NUMERIC).setCellValue(cthd.getDonGia());
                    row.createCell(11, CellType.NUMERIC).setCellValue(cthd.getDonGia() * cthd.getSoLuong());
                }
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Xu???t file Excel Phi???u nh???p
    public void xuatFileExcelPhieuNhap() {
        fd.setTitle("Xu???t d??? li???u phi???u nh???p ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("H??a ????n");

            QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
            QuanLyChiTietPhieuNhapBUS qlctpnBUS = new QuanLyChiTietPhieuNhapBUS();
            QuanLyTourBUS qltBUS = new QuanLyTourBUS();
            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();
            ArrayList<PhieuNhap> list = qlpnBUS.getDspn();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("M?? h??a ????n");
            row.createCell(2, CellType.STRING).setCellValue("M?? kh??ch s???n");
            row.createCell(3, CellType.STRING).setCellValue("M?? nh??n vi??n");
            row.createCell(4, CellType.STRING).setCellValue("Ng??y l???p");
            row.createCell(5, CellType.STRING).setCellValue("Gi??? l???p");
            row.createCell(6, CellType.STRING).setCellValue("T???ng ti???n");
            row.createCell(7, CellType.STRING).setCellValue("Tour");
            row.createCell(8, CellType.STRING).setCellValue("S??? l?????ng");
            row.createCell(9, CellType.STRING).setCellValue("????n gi??");
            row.createCell(10, CellType.STRING).setCellValue("Th??nh ti???n");

            for (PhieuNhap pn : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(pn.getMaPN());
                row.createCell(2, CellType.STRING).setCellValue(pn.getMaKS() + " - " + qlksBUS.getKhachSan(pn.getMaKS()).getTenKS());
                row.createCell(3, CellType.STRING).setCellValue(pn.getMaNV() + " - " + qlnvBUS.getNhanVien(pn.getMaNV()).getTenNV());
                row.createCell(4, CellType.STRING).setCellValue(String.valueOf(pn.getNgayNhap()));
                row.createCell(5, CellType.STRING).setCellValue(String.valueOf(pn.getGioNhap()));
                row.createCell(6, CellType.NUMERIC).setCellValue(pn.getTongTien());

                for (ChiTietPhieuNhap ctpn : qlctpnBUS.getAllChiTiet(pn.getMaPN())) {
                    rownum++;
                    row = sheet.createRow(rownum);

                    String mat = ctpn.getMaT();

                    row.createCell(7, CellType.STRING).setCellValue(mat + " - " + qltBUS.getTour(mat).getTenT());
                    row.createCell(8, CellType.NUMERIC).setCellValue(ctpn.getSoLuong());
                    row.createCell(9, CellType.NUMERIC).setCellValue(ctpn.getDonGia());
                    row.createCell(10, CellType.NUMERIC).setCellValue(ctpn.getDonGia() * ctpn.getSoLuong());
                }
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file th??nh c??ng: " + file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (outFile != null) {
                    outFile.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(XuatExcel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private String getTime() {
        return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
}

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

    FileDialog fd = new FileDialog(new JFrame(), "Xuất excel", FileDialog.SAVE);

    private String getFile() {
        fd.setFile("untitled.xls");
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("nullnull")) {
            return null;
        }
        return url;
    }

    // Xuất file Excel Nhà cung cấp    
    public void xuatFileExcelNhaCungCap() {
        fd.setTitle("Xuất dữ liệu khách sạn ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhà cung cấp");

            QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();
            ArrayList<KhachSan> list = qlksBUS.getDsks();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã khách sạn");
            row.createCell(2, CellType.STRING).setCellValue("Tên khách sạn");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(4, CellType.STRING).setCellValue("Số điện thoại");
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

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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
    
    // Xuất file Excel Nhà xuất bản    
    public void xuatFileExcelNhaXuatBan() {
        fd.setTitle("Xuất dữ liệu nhà xuất bản ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhà xuất bản");

            QuanLyDoanBUS qldoanBUS = new QuanLyDoanBUS();
            ArrayList<Doan> list = qldoanBUS.getDsdoan();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã nhà xuất bản");
            row.createCell(2, CellType.STRING).setCellValue("Tên nhà xuất bản");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");

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

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

    // Xuất file Excel Nhân viên
    public void xuatFileExcelNhanVien() {
        fd.setTitle("Xuất dữ liệu nhân viên ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Nhân viên");

            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            ArrayList<NhanVien> list = qlnvBUS.getDsnv();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(2, CellType.STRING).setCellValue("Tên nhân viên");
            row.createCell(3, CellType.STRING).setCellValue("Ngày sinh");
            row.createCell(4, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(5, CellType.STRING).setCellValue("Số điện thoại");
            row.createCell(6, CellType.STRING).setCellValue("Trạng thái");

            for (NhanVien nv : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(nv.getMaNV());
                row.createCell(2, CellType.STRING).setCellValue(nv.getTenNV());
                row.createCell(3, CellType.STRING).setCellValue(String.valueOf(nv.getNgaySinh()));
                row.createCell(4, CellType.STRING).setCellValue(nv.getDiaChi());
                row.createCell(5, CellType.STRING).setCellValue(nv.getSDT());
                row.createCell(6, CellType.STRING).setCellValue((nv.getTrangThai() == 0 ? "Hiện" : "Ẩn"));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

    // Xuất file Excel Khách hàng
    public void xuatFileExcelKhachHang() {
        fd.setTitle("Xuất dữ liệu khách hàng ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khách hàng");

            QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();
            ArrayList<KhachHang> list = qlkhBUS.getDskh();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã khách hàng");
            row.createCell(2, CellType.STRING).setCellValue("Tên khách hàng");
            row.createCell(3, CellType.STRING).setCellValue("Địa chỉ");
            row.createCell(4, CellType.STRING).setCellValue("Số điện thoại");
            row.createCell(5, CellType.STRING).setCellValue("Trạng thái");

            for (KhachHang kh : list) {
                rownum++;
                row = sheet.createRow(rownum);

                row.createCell(0, CellType.NUMERIC).setCellValue(rownum);
                row.createCell(1, CellType.STRING).setCellValue(kh.getMaKH());
                row.createCell(2, CellType.STRING).setCellValue(kh.getTenKH());
                row.createCell(3, CellType.STRING).setCellValue(kh.getDiaChi());
                row.createCell(4, CellType.STRING).setCellValue(kh.getSDT());
                row.createCell(5, CellType.STRING).setCellValue((kh.getTrangThai() == 0 ? "Hiện" : "Ẩn"));
            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

    // Xuất file Excel Tài khoản
    public void xuatFileExcelTaiKhoan() {
        fd.setTitle("Xuất dữ liệu tài khoản ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Tài khoản");

            QuanLyTaiKhoanBUS qltkBUS = new QuanLyTaiKhoanBUS();
            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            QuanLyQuyenBUS qlqBUS = new QuanLyQuyenBUS();
            ArrayList<TaiKhoan> list = qltkBUS.getDstk();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Tên tài khoản");
            row.createCell(2, CellType.STRING).setCellValue("Mật khẩu");
            row.createCell(3, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(4, CellType.STRING).setCellValue("Mã quyền");

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

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

    // Xuất file Excel Khuyến mãi
    public void xuatFileExcelKhuyenMai() {
        fd.setTitle("Xuất dữ liệu khuyến mãi ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Khuyến mãi");

            QuanLyKhuyenMaiBUS qlkmBUS = new QuanLyKhuyenMaiBUS();
            ArrayList<KhuyenMai> list = qlkmBUS.getDskm();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã khuyến mãi");
            row.createCell(2, CellType.STRING).setCellValue("Tên khuyến mãi");
            row.createCell(3, CellType.NUMERIC).setCellValue("Điều kiện");
            row.createCell(4, CellType.NUMERIC).setCellValue("Phần trăm");
            row.createCell(5, CellType.STRING).setCellValue("Ngày bắt đầu");
            row.createCell(6, CellType.STRING).setCellValue("Ngày kết thúc");

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

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

    // Xuất file Excel Sản phẩm
    public void xuatFileExcelTour() {
        fd.setTitle("Xuất dữ liệu tour ra excel");
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
            row.createCell(1, CellType.STRING).setCellValue("Mã tour");
            row.createCell(2, CellType.STRING).setCellValue("Loại tour");
            row.createCell(3, CellType.STRING).setCellValue("Tên");
            row.createCell(4, CellType.NUMERIC).setCellValue("Đơn giá");
            row.createCell(5, CellType.NUMERIC).setCellValue("Số lượng");
            row.createCell(6, CellType.STRING).setCellValue("Hình ảnh");
            row.createCell(7, CellType.STRING).setCellValue("Trạng thái");
            row.createCell(9, CellType.STRING).setCellValue("Đoàn");

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
                row.createCell(7, CellType.STRING).setCellValue(t.getTrangThai() == 0 ? "Hiện" : "Ẩn");
                row.createCell(9, CellType.STRING).setCellValue(madoan + " - " + qldoan.getDoan(madoan).getTenDoan());

            }
            for (int i = 0; i < rownum; i++) {
                sheet.autoSizeColumn(i);
            }

            File file = new File(url);
            file.getParentFile().mkdirs();
            outFile = new FileOutputStream(file);
            workbook.write(outFile);

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

    // Xuất file Excel Loại sản phẩm
    public void xuatFileExcelLoaiTour() {
        fd.setTitle("Xuất dữ liệu loại tour ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Loại Tour");

            QuanLyLoaiTourBUS qlltBUS = new QuanLyLoaiTourBUS();
            ArrayList<LoaiTour> list = qlltBUS.getDslt();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã Loại");
            row.createCell(2, CellType.STRING).setCellValue("Tên loại");
            row.createCell(3, CellType.STRING).setCellValue("Mô tả");

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

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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
    

    // Xuất file Excel Quyền
    public void xuatFileExcelQuyen() {
        fd.setTitle("Xuất dữ liệu quyền ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Quyền");

            QuanLyQuyenBUS qlqBUS = new QuanLyQuyenBUS();
            ArrayList<Quyen> list = qlqBUS.getDsq();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã quyền");
            row.createCell(2, CellType.STRING).setCellValue("Tên quyền");
            row.createCell(3, CellType.STRING).setCellValue("Chi tiết quyền");

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

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

    // Xuất file Excel Hóa đơn
    public void xuatFileExcelHoaDon() {
        fd.setTitle("Xuất dữ liệu hóa đơn ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hóa đơn");

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
            row.createCell(1, CellType.STRING).setCellValue("Mã hóa đơn");
            row.createCell(2, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(3, CellType.STRING).setCellValue("Mã khách hàng");
            row.createCell(4, CellType.STRING).setCellValue("Mã khuyến mãi");
            row.createCell(5, CellType.STRING).setCellValue("Ngày lập");
            row.createCell(6, CellType.STRING).setCellValue("Giờ lập");
            row.createCell(7, CellType.STRING).setCellValue("Tổng tiền");

            row.createCell(8, CellType.STRING).setCellValue("Tour");
            row.createCell(9, CellType.STRING).setCellValue("Số lượng");
            row.createCell(10, CellType.STRING).setCellValue("Đơn giá");
            row.createCell(11, CellType.STRING).setCellValue("Thành tiền");

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

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

    // Xuất file Excel Phiếu nhập
    public void xuatFileExcelPhieuNhap() {
        fd.setTitle("Xuất dữ liệu phiếu nhập ra excel");
        String url = getFile();
        if (url == null) {
            return;
        }

        FileOutputStream outFile = null;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Hóa đơn");

            QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
            QuanLyChiTietPhieuNhapBUS qlctpnBUS = new QuanLyChiTietPhieuNhapBUS();
            QuanLyTourBUS qltBUS = new QuanLyTourBUS();
            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();
            ArrayList<PhieuNhap> list = qlpnBUS.getDspn();

            int rownum = 0;
            Row row = sheet.createRow(rownum);

            row.createCell(0, CellType.NUMERIC).setCellValue("STT");
            row.createCell(1, CellType.STRING).setCellValue("Mã hóa đơn");
            row.createCell(2, CellType.STRING).setCellValue("Mã khách sạn");
            row.createCell(3, CellType.STRING).setCellValue("Mã nhân viên");
            row.createCell(4, CellType.STRING).setCellValue("Ngày lập");
            row.createCell(5, CellType.STRING).setCellValue("Giờ lập");
            row.createCell(6, CellType.STRING).setCellValue("Tổng tiền");
            row.createCell(7, CellType.STRING).setCellValue("Tour");
            row.createCell(8, CellType.STRING).setCellValue("Số lượng");
            row.createCell(9, CellType.STRING).setCellValue("Đơn giá");
            row.createCell(10, CellType.STRING).setCellValue("Thành tiền");

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

            JOptionPane.showMessageDialog(null, "Ghi file thành công: " + file.getAbsolutePath());

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

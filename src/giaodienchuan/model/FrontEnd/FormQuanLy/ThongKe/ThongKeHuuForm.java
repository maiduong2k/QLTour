/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodienchuan.model.FrontEnd.FormQuanLy.ThongKe;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import giaodienchuan.model.BackEnd.PriceFormatter;
import giaodienchuan.model.BackEnd.QuanLyChiTietHoaDon.ChiTietHoaDon;
import giaodienchuan.model.BackEnd.QuanLyChiTietHoaDon.QuanLyChiTietHoaDonBUS;
import giaodienchuan.model.BackEnd.QuanLyChiTietPN.ChiTietPhieuNhap;
import giaodienchuan.model.BackEnd.QuanLyChiTietPN.QuanLyChiTietPhieuNhapBUS;
import giaodienchuan.model.BackEnd.QuanLyHoaDon.HoaDon;
import giaodienchuan.model.BackEnd.QuanLyHoaDon.QuanLyHoaDonBUS;
import giaodienchuan.model.BackEnd.QuanLyKhachHang.KhachHang;
import giaodienchuan.model.BackEnd.QuanLyKhachHang.QuanLyKhachHangBUS;
import giaodienchuan.model.BackEnd.QuanLyKSan.KhachSan;
import giaodienchuan.model.BackEnd.QuanLyKSan.QuanLyKhachSanBUS;
import giaodienchuan.model.BackEnd.QuanLyNhanVien.NhanVien;
import giaodienchuan.model.BackEnd.QuanLyNhanVien.QuanLyNhanVienBUS;
import giaodienchuan.model.BackEnd.QuanLyPhieuNhap.PhieuNhap;
import giaodienchuan.model.BackEnd.QuanLyPhieuNhap.QuanLyPhieuNhapBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.QuanLyTourBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.Tour;
import giaodienchuan.model.FrontEnd.GiaoDienChuan.MyTable;
import giaodienchuan.model.FrontEnd.MyButton.DateButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author DELL
 */
public class ThongKeHuuForm {

}

class ThongKeTour extends JPanel {

    QuanLyChiTietHoaDonBUS qlcthdBUS = new QuanLyChiTietHoaDonBUS();
    QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
    QuanLyChiTietPhieuNhapBUS qlctpnBUS = new QuanLyChiTietPhieuNhapBUS();
    QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();
    QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    MyTable tb;

    public ThongKeTour() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Số lượng nhập", "Số lượng bán"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(getClass().getResource("/giaodienchuan/images/icons8_data_backup_30px.png")));
        btnRefresh.addActionListener((ae) -> {
            qltBUS.readDB();
            qlpnBUS.readDB();
            qlctpnBUS.readDB();
            qlksBUS.readDB();
            qlnvBUS.readDB();
            qlhdBUS.readDB();
            qlcthdBUS.readDB();
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new MyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

   
    private void soLuongTourNhap() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã tour", "Tên tour", "Mã phiếu nhập", "Tên khách sạn", "Ngày nhập", "Số lượng", "Ðơn giá", "Tổng tiền"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        int tongTatCa = 0;
        float tongTien = 0;
        for (Tour t : qltBUS.getDst()) {
            int tongSoLuong = 0;
            float tongTienPhieuNhapCuaMoiTour = 0;
            tb.addRow(new String[]{t.getMaT(), t.getTenT(), "", "", "", "", String.valueOf(t.getDonGia()), ""});

            for (PhieuNhap pn : qlpnBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                ChiTietPhieuNhap ctpn = qlctpnBUS.getChiTiet(pn.getMaPN(), t.getMaT());

                if (ctpn != null) {
                    tb.addRow(new String[]{"", "",
                        pn.getMaPN(),
                        qlksBUS.getKhachSan(pn.getMaKS()).getTenKS(),
                        String.valueOf(pn.getNgayNhap()),
                        String.valueOf(ctpn.getSoLuong()),
                        "",
                        String.valueOf(ctpn.getSoLuong() * ctpn.getDonGia())

                    });
                    tongSoLuong += ctpn.getSoLuong();
                    tongTienPhieuNhapCuaMoiTour += ctpn.getSoLuong() * ctpn.getDonGia();
                }
            }

            tb.addRow(new String[]{"", "", "", "", mcd.getKhoangTG(), String.valueOf(tongSoLuong), "", String.valueOf(tongTienPhieuNhapCuaMoiTour)});
            tb.addRow(new String[]{"", "", "", "", "", "", "", ""});

            tongTatCa += tongSoLuong;
            tongTien += tongTienPhieuNhapCuaMoiTour;
        }
        tb.addRow(new String[]{"", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa), "", String.valueOf(tongTien)});
    }

    private void soLuongTourBan() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã tour", "Tên tour", "Mã hóa đơn", "Tên nhân viên", "Ngày lập", "Số lượng", "Ðơn giá", "Tổng tiền"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        int tongTatCa = 0;
        float tongTien = 0;
        for (Tour t : qltBUS.getDst()) {
            int tongSoLuong = 0;
            float tongTienHoaDonTungTour = 0;
            tb.addRow(new String[]{t.getMaT(), t.getTenT(), "", "", "", "", String.valueOf(t.getDonGia()), ""});

            for (HoaDon hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayTu(), -1, -1)) {
                ChiTietHoaDon cthd = qlcthdBUS.getChiTiet(hd.getMaHoaDon(), t.getMaT());
                if (cthd != null) {
                    tb.addRow(new String[]{"", "",
                        hd.getMaHoaDon(),
                        qlnvBUS.getNhanVien(hd.getMaNhanVien()).getTenNV(),
                        String.valueOf(hd.getNgayLap()),
                        String.valueOf(cthd.getSoLuong()), "", String.valueOf(cthd.getSoLuong() * cthd.getDonGia())
                    });
                    tongSoLuong += cthd.getSoLuong();
                    tongTienHoaDonTungTour += cthd.getSoLuong() * cthd.getDonGia();
                }
            }

            tb.addRow(new String[]{"", "", "", "", mcd.getKhoangTG(), String.valueOf(tongSoLuong), "", String.valueOf(tongTienHoaDonTungTour)});
            tb.addRow(new String[]{"", "", "", "", "", ""});
            tongTatCa += tongSoLuong;
            tongTien += tongTienHoaDonTungTour;
        }

        tb.addRow(new String[]{"", "", "", "", "T?ng t?t c?", String.valueOf(tongTatCa), "", String.valueOf(tongTien)});
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Số lượng nhập")) {
            soLuongTourNhap();
        }
        if (cbTieuChi.getSelectedItem().equals("Số lượng bán")) {
            soLuongTourBan();
        }
    }
}

class ThongKeNhanVien extends JPanel {

    QuanLyChiTietHoaDonBUS qlcthdBUS = new QuanLyChiTietHoaDonBUS();
    QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    MyTable tb;

    public ThongKeNhanVien() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Tổng tiền", "Số lượng tour"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(getClass().getResource("/giaodienchuan/images/icons8_data_backup_30px.png")));
        btnRefresh.addActionListener((ae) -> {
            qltBUS.readDB();
            qlnvBUS.readDB();
            qlhdBUS.readDB();
            qlcthdBUS.readDB();
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new MyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    public void tongTienTungNhanVien_searchOnChange() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã nhân viên", "Tên nhân viên", "Mã hóa đơn", "Ngày lập", "Tổng tiền hóa đơn"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        float tongTatCa = 0;
        for (NhanVien nv : qlnvBUS.getDsnv()) {
            float tongTien = 0;
            tb.addRow(new String[]{nv.getMaNV(), nv.getTenNV(), "", ""});

            for (HoaDon hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                if (nv.getMaNV().equals(hd.getMaNhanVien())) {
                    tb.addRow(new String[]{"", "",
                        hd.getMaHoaDon(),
                        String.valueOf(hd.getNgayLap()),
                        PriceFormatter.format(hd.getTongTien())
                    });
                    tongTien += hd.getTongTien();
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), PriceFormatter.format(tongTien)});
            tb.addRow(new String[]{"", "", "", "", "", ""});

            tongTatCa += tongTien;
        }
        tb.addRow(new String[]{"", "", "", "Tổng tất cả", PriceFormatter.format(tongTatCa)});
    }

    public void sanPhamCuaTungNhanVien_searchOnChange() {
        tb.setHeaders(new String[]{"Mã nhân viên", "Tên nhân viên", "Mã hóa đơn", "Ngày lập", "Mã tour", "Tên tour", "Số lượng "});
        tb.clear();

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        int tongTatCa = 0;

        for (NhanVien nv : qlnvBUS.getDsnv()) {
            float tongSoLuong = 0;
            tb.addRow(new String[]{nv.getMaNV(), nv.getTenNV(), "", "", "", "", ""});

            for (HoaDon hd : qlhdBUS.search("Mã nhân viên", nv.getMaNV(), mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) { // tương đối -> sai
                tb.addRow(new String[]{"", "", hd.getMaHoaDon(), String.valueOf(hd.getNgayLap()), "", "", ""});

                for (ChiTietHoaDon cthd : qlcthdBUS.search("Mã hóa đơn", hd.getMaHoaDon(), -1, -1, -1, -1)) { // tương đối -> sai
                    tongSoLuong += cthd.getSoLuong();
                    tb.addRow(new String[]{"", "", "", "",
                        cthd.getMaTour(),
                        qltBUS.getTour(cthd.getMaTour()).getTenT(),
                        String.valueOf(cthd.getSoLuong())
                    });
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "Tổng số tour", String.valueOf(tongSoLuong)});
            tb.addRow(new String[]{"", "", "", "", "", ""});

            tongTatCa += tongSoLuong;
        }
        tb.addRow(new String[]{"", "", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)});
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Tổng tiền")) {
            tongTienTungNhanVien_searchOnChange();
        }
        if (cbTieuChi.getSelectedItem().equals("Số lượng ")) {
            sanPhamCuaTungNhanVien_searchOnChange();
        }
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }
}

class ThongKeKhachHang extends JPanel {

    QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
    QuanLyChiTietHoaDonBUS qlcthdBUS = new QuanLyChiTietHoaDonBUS();
    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    JButton btnRefresh = new JButton("Làm mới");
    MyTable tb;

    public ThongKeKhachHang() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Tổng tiền", "Số lượng tour"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(getClass().getResource("/giaodienchuan/images/icons8_data_backup_30px.png")));
        btnRefresh.addActionListener((ae) -> {
            qltBUS.readDB();
            qlcthdBUS.readDB();
            qlhdBUS.readDB();
            qlkhBUS.readDB();
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);

        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new MyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    //Thong ke tong tien hoa don cua tung khach hang
    public void tongTienTungKhachHang_searchOnChange() {
        tb.setHeaders(new String[]{"Mã khách hàng", "Tên khách hàng", "Mã hóa đơn", "Ngày lập", "Tổng tiền hóa đơn"});
        tb.clear();

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        float tongTatCa = 0;
        for (KhachHang kh : qlkhBUS.getDskh()) {
            float tongTien = 0;
            tb.addRow(new String[]{kh.getMaKH(), kh.getTenKH(), "", "", ""});

            for (HoaDon hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                if (kh.getMaKH().equals(hd.getMaKhachHang())) {
                    tb.addRow(new String[]{"", "",
                        hd.getMaHoaDon(),
                        String.valueOf(hd.getNgayLap()),
                        PriceFormatter.format(hd.getTongTien())
                    });
                    tongTien += hd.getTongTien();
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), PriceFormatter.format(tongTien)});
            tb.addRow(new String[]{"", "", "", "", "", ""});

            tongTatCa += tongTien;
        }

        tb.addRow(new String[]{"", "", "", "Tổng tất cả", PriceFormatter.format(tongTatCa)});
    }

    //Thong ke san pham va so luong mua cua tung khach hang
    public void sanPhamCuaTungKhachHang_searchOnChange() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã khách hàng", "Tên khách hàng", "Mã hóa đơn", "Ngày lập", "Mã tour", "Tên tour", "Số lượng tour"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
        int tongTatCa = 0;
        for (KhachHang kh : qlkhBUS.getDskh()) {
            int tongSoLuong = 0;
            tb.addRow(new String[]{kh.getMaKH(), kh.getTenKH(), "", "", "", "", ""});

            for (HoaDon hd : qlhdBUS.search("Mã khách hàng", kh.getMaKH(), mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) { // tương đối -> sai 
                tb.addRow(new String[]{"", "", hd.getMaHoaDon(), String.valueOf(hd.getNgayLap()), "", "", ""});

                for (ChiTietHoaDon cthd : qlcthdBUS.search("Mã hóa đơn", hd.getMaHoaDon(), -1, -1, -1, -1)) { // tương đối -> sai
                    tongSoLuong += cthd.getSoLuong();
                    tb.addRow(new String[]{"", "", "", "",
                        cthd.getMaTour(),
                        qltBUS.getTour(cthd.getMaTour()).getTenT(),
                        String.valueOf(cthd.getSoLuong())
                    });
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "Tổng số tour", String.valueOf(tongSoLuong)});
            tb.addRow(new String[]{"", "", "", "", "", ""});

            tongTatCa += tongSoLuong;
        }
        tb.addRow(new String[]{"", "", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa)});
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Tổng tiền")) {
            tongTienTungKhachHang_searchOnChange();
        }
        if (cbTieuChi.getSelectedItem().equals("Số lượng tour")) {
            sanPhamCuaTungKhachHang_searchOnChange();
        }
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }
}

class ThongKeNhaCungCap extends JPanel {

    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
    QuanLyChiTietPhieuNhapBUS qlctpnBUS = new QuanLyChiTietPhieuNhapBUS();
    QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();

    JTextField txKhoangNgayTu = new JTextField(15);
    JTextField txKhoangNgayDen = new JTextField(15);
    DatePicker dPicker1;
    DatePicker dPicker2;

    JComboBox cbTieuChi;
    MyTable tb;
    JButton btnRefresh = new JButton("Làm mới");

    public ThongKeNhaCungCap() {
        this.setLayout(new BorderLayout());

        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgayTu.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgayDen.setText(dPicker2.getDateStringOrEmptyString());
        });

        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);

        //Panel tieu chi
        JPanel plTieuchi = new JPanel();
        plTieuchi.setLayout(new FlowLayout());

        cbTieuChi = new JComboBox(new String[]{"Số lượng tour", "Tổng thành tiền"});
        cbTieuChi.addActionListener((ae) -> {
            cbSearchOnChange();
        });
        plTieuchi.add(cbTieuChi);

        JPanel plKhoangNgay1 = new JPanel();
        txKhoangNgayTu.setBorder(BorderFactory.createTitledBorder("Từ:"));
        addDocumentListener(txKhoangNgayTu);
        plKhoangNgay1.add(txKhoangNgayTu);
        plKhoangNgay1.add(dPicker1);
        JPanel plKhoangNgay2 = new JPanel();
        txKhoangNgayDen.setBorder(BorderFactory.createTitledBorder("Đến"));
        addDocumentListener(txKhoangNgayDen);
        plKhoangNgay2.add(txKhoangNgayDen);
        plKhoangNgay2.add(dPicker2);

        btnRefresh.setIcon(new ImageIcon(getClass().getResource("/giaodienchuan/images/icons8_data_backup_30px.png")));
        btnRefresh.addActionListener((ae) -> {
            qltBUS.readDB();
            qlpnBUS.readDB();
            qlctpnBUS.readDB();
            qlksBUS.readDB();
            txKhoangNgayTu.setText("");
            txKhoangNgayDen.setText("");
            dPicker1.setDate(null);
            dPicker2.setDate(null);
            cbSearchOnChange();
        });

        plTieuchi.add(plKhoangNgay1);
        plTieuchi.add(plKhoangNgay2);
        plTieuchi.add(btnRefresh);
        this.add(plTieuchi, BorderLayout.NORTH);

        //Table thong ke
        tb = new MyTable();
        cbSearchOnChange();
        this.add(tb, BorderLayout.CENTER);
    }

    private void soLuongTourCungCap() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã khách sạn", "Tên khách sạn", "Mã phiếu nhập", "Ngày lập", "Mã tour", "Tên tour", "Số lượng"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        int tongTatCa = 0;

        for (KhachSan ks : qlksBUS.getDsks()) {
            int tongSoLuong = 0;
            tb.addRow(new String[]{ks.getMaKS(), ks.getTenKS(), "", "", "", "", ""});

            for (PhieuNhap pn : qlpnBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                if (pn.getMaKS().equals(ks.getMaKS())) {
                    tb.addRow(new String[]{"", "", pn.getMaPN(), String.valueOf(pn.getNgayNhap()), "", "", ""});

                    for (ChiTietPhieuNhap ctpn : qlctpnBUS.search("Mã phiếu nhập", pn.getMaPN())) {
                        tongSoLuong += ctpn.getSoLuong();
                        tb.addRow(new String[]{"", "", "", "",
                            ctpn.getMaT(),
                            qltBUS.getTour(ctpn.getMaT()).getTenT(),
                            String.valueOf(ctpn.getSoLuong())
                        });
                    }
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "Tổng số lượng:", String.valueOf(tongSoLuong)});
            tb.addRow(new String[]{"", "", "", "", "", "", ""});

            tongTatCa += tongSoLuong;
        }
        tb.addRow(new String[]{"", "", "", "", "", "Tổng tất cả:", String.valueOf(tongTatCa)});
    }

    private void tongTienThanhToan() {
        tb.clear();
        tb.setHeaders(new String[]{"Mã khách sạn", "Tên khách sạn", "Mã phiếu nhập", "Ngày lập", "Mã tour", "Đơn giá", "Số lượng", "Thành tiền"});

        MyCheckDate mcd = new MyCheckDate(txKhoangNgayTu, txKhoangNgayDen);

        float tongTatCa = 0;
        for (KhachSan ks : qlksBUS.getDsks()) {
            float tongTien = 0;
            tb.addRow(new String[]{ks.getMaKS(), ks.getTenKS(), "", "", "", "", "", ""});

            for (PhieuNhap pn : qlpnBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                if (pn.getMaKS().equals(ks.getMaKS())) {
                    tb.addRow(new String[]{"", "", pn.getMaPN(), String.valueOf(pn.getNgayNhap()), "", "", "", ""});

                    for (ChiTietPhieuNhap ctpn : qlctpnBUS.search("Mã phiếu nhập", pn.getMaPN())) {
                        tongTien += ctpn.getSoLuong() * ctpn.getDonGia();
                        tb.addRow(new String[]{"", "", "", "",
                            ctpn.getMaT(),
                            String.valueOf(ctpn.getDonGia()),
                            String.valueOf(ctpn.getSoLuong()),
                            PriceFormatter.format(ctpn.getSoLuong() * ctpn.getDonGia())});
                    }
                }
            }
            tb.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "", "Tổng thành tiền:", PriceFormatter.format(tongTien)});
            tb.addRow(new String[]{"", "", "", "", "", "", "", ""});

            tongTatCa += tongTien;
        }
        tb.addRow(new String[]{"", "", "", "", "", "", "Tổng tất cả:", PriceFormatter.format(tongTatCa)});
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                cbSearchOnChange();
            }
        });
    }

    public void cbSearchOnChange() {
        if (cbTieuChi.getSelectedItem().equals("Số lượng tour")) {
            soLuongTourCungCap();
        }
        if (cbTieuChi.getSelectedItem().equals("Tổng thành tiền")) {
            tongTienThanhToan();
        }
    }
}

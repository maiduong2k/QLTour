/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodienchuan.model.FrontEnd.FormQuanLy;

import giaodienchuan.model.BackEnd.PriceFormatter;
import giaodienchuan.model.BackEnd.QuanLyChiTietHoaDon.ChiTietHoaDon;
import giaodienchuan.model.BackEnd.QuanLyChiTietHoaDon.QuanLyChiTietHoaDonBUS;
import giaodienchuan.model.BackEnd.QuanLyChiTietPN.ChiTietPhieuNhap;
import giaodienchuan.model.BackEnd.QuanLyChiTietPN.QuanLyChiTietPhieuNhapBUS;
import giaodienchuan.model.BackEnd.QuanLyHoaDon.HoaDon;
import giaodienchuan.model.BackEnd.QuanLyHoaDon.QuanLyHoaDonBUS;
import giaodienchuan.model.BackEnd.QuanLyKhachHang.KhachHang;
import giaodienchuan.model.BackEnd.QuanLyKhachHang.QuanLyKhachHangBUS;
import giaodienchuan.model.BackEnd.QuanLyKhuyenMai.KhuyenMai;
import giaodienchuan.model.BackEnd.QuanLyKhuyenMai.QuanLyKhuyenMaiBUS;
import giaodienchuan.model.BackEnd.QuanLyLoaiTour.QuanLyLoaiTourBUS;
import giaodienchuan.model.BackEnd.QuanLyKSan.KhachSan;
import giaodienchuan.model.BackEnd.QuanLyKSan.QuanLyKhachSanBUS;
import giaodienchuan.model.BackEnd.QuanLyNhanVien.NhanVien;
import giaodienchuan.model.BackEnd.QuanLyNhanVien.QuanLyNhanVienBUS;
import giaodienchuan.model.BackEnd.QuanLyPhieuNhap.PhieuNhap;
import giaodienchuan.model.BackEnd.QuanLyPhieuNhap.QuanLyPhieuNhapBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.QuanLyTourBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.Tour;
import giaodienchuan.model.BackEnd.WritePDF.WritePDF;
import giaodienchuan.model.FrontEnd.FormChon.ChonKhachHangForm;
import giaodienchuan.model.FrontEnd.FormChon.ChonKhuyenMaiForm;
import giaodienchuan.model.FrontEnd.FormChon.ChonKhachSanForm;
import giaodienchuan.model.FrontEnd.FormChon.ChonNhanVienForm;
import giaodienchuan.model.FrontEnd.GiaoDienChuan.LoginForm;
import giaodienchuan.model.FrontEnd.GiaoDienChuan.MyTable;
import giaodienchuan.model.FrontEnd.MyButton.MoreButton;
import giaodienchuan.model.FrontEnd.MyButton.RefreshButton;
import giaodienchuan.model.FrontEnd.MyButton.SuaButton;
import giaodienchuan.model.FrontEnd.MyButton.ThemButton;
import giaodienchuan.model.FrontEnd.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author DELL
 */
public class FormHang extends JPanel {
    
    ChonTourPanel target;

    public FormHang() {

    }

    public void addChiTiet(String mat, int soluong) {

    }
    
    public void setTarget(ChonTourPanel target) {
        this.target = target;
    }
}

class PhieuNhapHang extends FormHang {

    QuanLyChiTietPhieuNhapBUS qlctpn = new QuanLyChiTietPhieuNhapBUS();
    QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();
    QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();

    NhanVien nhanVien;
    KhachSan nhacungcap;

    JTextField txMaPhieuNhap = new JTextField(20);
    JTextField txNhanVien = new JTextField(17);
    JTextField txKSan = new JTextField(17);
    JTextField txNgayLap = new JTextField(20);
    JTextField txGioLap = new JTextField(20);
    JTextField txTongTien = new JTextField(20);

    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonKSan = new MoreButton();

    MyTable tbChiTietPhieuNhap = new MyTable();
    XoaButton btnXoa = new XoaButton();
    SuaButton btnSua = new SuaButton();
    RefreshButton btnRefresh = new RefreshButton();

    JButton btnNhapHang = new JButton("Nhập hàng");
    JButton btnHuy = new JButton("Hủy");

    ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();

    public PhieuNhapHang(int _x, int _y, int _width, int _height) {
        this.setBounds(_x, _y, _width, _height);
        this.setBackground(new Color(0, 0, 0));
        this.setLayout(new FlowLayout());

        // =============== panel input =================
        int plIP_height = 180;
        JPanel plInput = new JPanel();
        plInput.setPreferredSize(new Dimension(_width - 10, plIP_height));
        plInput.setBackground(new Color(240, 240, 240));
        plInput.setLayout(new FlowLayout());

        // btn
        btnChonKSan.setPreferredSize(new Dimension(30, 30));
        btnChonKSan.addActionListener((ae) -> {
            ChonKhachSanForm cks = new ChonKhachSanForm(txKSan);
            cks.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String maks = txKSan.getText();
                    nhacungcap = qlksBUS.getKhachSan(maks);
                    if (nhacungcap != null) {
                        txKSan.setText(nhacungcap.getTenKS() + " (" + nhacungcap.getMaKS() + ")");
                    }
                }
            });
        });

        btnChonNhanVien.setPreferredSize(new Dimension(30, 30));
        btnChonNhanVien.addActionListener((ae) -> {
            ChonNhanVienForm cnv = new ChonNhanVienForm(txNhanVien);
            cnv.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String mavn = txNhanVien.getText();
                    nhanVien = qlnvBUS.getNhanVien(mavn);
                    if (nhanVien != null) {
                        txNhanVien.setText(nhanVien.getTenNV() + " (" + nhanVien.getMaNV() + ")");
                    }
                }
            });
        });
        btnChonNhanVien.setEnabled(false);

        // set border
        txMaPhieuNhap.setBorder(BorderFactory.createTitledBorder("Mã phiếu nhập:"));
        txNhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên:"));
        txNgayLap.setBorder(BorderFactory.createTitledBorder("Ngày lập:"));
        txGioLap.setBorder(BorderFactory.createTitledBorder("Giờ lập:"));
        txKSan.setBorder(BorderFactory.createTitledBorder("Khách sạn:"));
        txTongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền (triệu vnd):"));

        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaPhieuNhap.setFont(f);
        txNhanVien.setFont(f);
        txNgayLap.setFont(f);
        txGioLap.setFont(f);
        txKSan.setFont(f);
        txMaPhieuNhap.setFont(f);
        txTongTien.setFont(f);

        // set Text
        if (LoginForm.nhanVienLogin != null) {
            nhanVien = LoginForm.nhanVienLogin;
            txNhanVien.setText(nhanVien.getTenNV() + " (" + nhanVien.getMaNV() + ")");
        }

        txMaPhieuNhap.setText(qlpnBUS.getNextID());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txNgayLap.setText(LocalDate.now().toString());
                txGioLap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                if (txNhanVien.getText().equals("")
                        || txKSan.getText().equals("")
                        || txTongTien.getText().equals("")
                        || txTongTien.getText().equals("0")) {
                    btnNhapHang.setEnabled(false);
                } else {
                    btnNhapHang.setEnabled(true);
                }
            }
        }, 0, 1000);

        // set editable
        txMaPhieuNhap.setEditable(false);
        txNhanVien.setEditable(false);
        txKSan.setEditable(false);
        txNgayLap.setEditable(false);
        txGioLap.setEditable(false);
        txTongTien.setEditable(false);

        // add to panel
        plInput.add(txMaPhieuNhap);
        plInput.add(txTongTien);
        plInput.add(txKSan);
        plInput.add(btnChonKSan);
        plInput.add(txNhanVien);
        plInput.add(btnChonNhanVien);
        plInput.add(txNgayLap);
        plInput.add(txGioLap);

        this.add(plInput);

        // =============== panel các sản phẩm đã chọn ==================
        int plT_height = 495;
        JPanel plTour = new JPanel();
        plTour.setPreferredSize(new Dimension(_width - 10, plT_height));
        plTour.setBackground(new Color(250, 250, 29));
        plTour.setLayout(new BorderLayout());

        int plBtn_height = 50;
        JPanel plButtonChiTiet = new JPanel();
        plButtonChiTiet.setLayout(new FlowLayout(FlowLayout.CENTER));
        plButtonChiTiet.setBackground(new Color(220, 220, 220));
        plButtonChiTiet.setPreferredSize(new Dimension(_width - 10, plBtn_height));

        btnXoa.addActionListener((ae) -> {
            btnXoaOnClick();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaOnClick();
        });
        btnRefresh.addActionListener((ae) -> {
            setDataToTable(dsctpn, tbChiTietPhieuNhap);
        });

        plButtonChiTiet.add(btnXoa);
        plButtonChiTiet.add(btnSua);
        plButtonChiTiet.add(btnRefresh);

        tbChiTietPhieuNhap.setPreferredSize(new Dimension(_width - 10, plT_height - plBtn_height));
        tbChiTietPhieuNhap.setHeaders(new String[]{"STT", "Mã", "Tên", "Số lượng", "Đơn giá", "Thành tiền"});
        tbChiTietPhieuNhap.setColumnsWidth(new double[]{1, 2, 3, 2.2, 2.5, 3});
        tbChiTietPhieuNhap.setAlignment(0, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(1, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(3, JLabel.CENTER);
        tbChiTietPhieuNhap.setAlignment(4, JLabel.RIGHT);
        tbChiTietPhieuNhap.setAlignment(5, JLabel.RIGHT);

        plTour.add(tbChiTietPhieuNhap, BorderLayout.CENTER);
        plTour.add(plButtonChiTiet, BorderLayout.SOUTH);

        this.add(plTour);

        // ============= panel Thanh toán ==============
        int plTT_height = _height - plIP_height - plT_height - 20;
        JPanel plThanhToan = new JPanel();
        plThanhToan.setLayout(new FlowLayout(FlowLayout.RIGHT));
        plThanhToan.setPreferredSize(new Dimension(_width - 10, plTT_height));
        plThanhToan.setBackground(new Color(0, 0, 0));

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_cancel_30px_1.png")));
        btnNhapHang.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_us_dollar_30px.png")));

        btnHuy.addActionListener((ae) -> {
            btnHuyOnClick();
        });
        btnNhapHang.addActionListener((ae) -> {
            btnNhapHangOnClick();
        });

        plThanhToan.add(btnHuy);
        plThanhToan.add(btnNhapHang);

        this.add(plThanhToan);
    }

    private void btnHuyOnClick() {
        clear();
    }

    private void btnNhapHangOnClick() {
        PhieuNhap pn = new PhieuNhap(
                txMaPhieuNhap.getText(),
                nhacungcap.getMaKS(),
                nhanVien.getMaNV(),
                LocalDate.parse(txNgayLap.getText()),
                LocalTime.parse(txGioLap.getText()),
                Float.parseFloat(txTongTien.getText()));
        qlpnBUS.add(pn);

        for (ChiTietPhieuNhap ct : dsctpn) {
            qlctpn.add(ct);
        }
        
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                        "Nhập hàng thành công, bạn có muốn IN PHIẾU NHẬP?", "Thành công",
                        JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.OK_OPTION) {
            new WritePDF().writePhieuNhap(txMaPhieuNhap.getText());
        }
        txMaPhieuNhap.setText(qlpnBUS.getNextID()); // lấy mã cho phiếu nhập mới
        clear();
        this.target.refreshAll();
    }

    private void btnXoaOnClick() {
        int i = tbChiTietPhieuNhap.getTable().getSelectedRow();
        if (i >= 0 && i < dsctpn.size()) {
            dsctpn.remove(i);
            setDataToTable(dsctpn, tbChiTietPhieuNhap);
        }
    }

    private void btnSuaOnClick() {
        int i = tbChiTietPhieuNhap.getTable().getSelectedRow();
        if (i >= 0 && i < dsctpn.size()) {
            ChiTietPhieuNhap ct = dsctpn.get(i);
            this.target.showInfo(ct.getMaT(), ct.getSoLuong());

            dsctpn.remove(i);
            setDataToTable(dsctpn, tbChiTietPhieuNhap);
        }
    }

    public void clear() {
        txKSan.setText("");
        txTongTien.setText("");
        dsctpn.clear();
        setDataToTable(dsctpn, tbChiTietPhieuNhap);
    }

    @Override
    public void addChiTiet(String mat, int soluong) {
        Tour t = qltBUS.getTour(mat);

        Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa
        for (ChiTietPhieuNhap ctpn : dsctpn) {
            if (ctpn.getMaT().equals(t.getMaT())) {
                int tongSoLuong = soluong + ctpn.getSoLuong();
                ctpn.setSoLuong(tongSoLuong); // có rồi thì thay đổi số lượng
                daCo = true;
            }
        }

        if (!daCo) { // nếu chưa có thì thêm mới
            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(qlpnBUS.getNextID(), mat, soluong, t.getDonGia());
            dsctpn.add(ctpn);
        }

        // cập nhật lại table
        setDataToTable(dsctpn, tbChiTietPhieuNhap);
    }

    public void setDataToTable(ArrayList<ChiTietPhieuNhap> arr, MyTable tb) {
        tb.clear();
        float tongtien = 0;
        int stt = 1;
        for (ChiTietPhieuNhap ctpn : arr) {
            String mat = ctpn.getMaT();
            Tour t = qltBUS.getTour(mat);
            String tent = t.getTenT();
            int soluong = ctpn.getSoLuong();
            float dongia = ctpn.getDonGia();
            float thanhtien = soluong * dongia;

            tb.addRow(new String[]{
                String.valueOf(stt),
                mat,
                tent,
                String.valueOf(soluong),
                PriceFormatter.format(dongia),
                PriceFormatter.format(thanhtien)
            });
            stt++;
            tongtien += thanhtien;
        }

        // check khuyến mãi
        tb.addRow(new String[]{"", "", "", "", "", ""});
        tb.addRow(new String[]{"", "", "", "", "Tổng tiền", PriceFormatter.format(tongtien)});
        txTongTien.setText(String.valueOf(tongtien));
    }
}

class HoaDonBanHang extends FormHang {

    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();
    QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
    QuanLyKhuyenMaiBUS qlkmBUS = new QuanLyKhuyenMaiBUS();
    QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
    QuanLyChiTietHoaDonBUS qlcthd = new QuanLyChiTietHoaDonBUS();

    NhanVien nhanVien;
    KhachHang khachHang;
    KhuyenMai khuyenMai;

    JTextField txMaHoaDon = new JTextField(20);
    JTextField txNhanVien = new JTextField(17);
    JTextField txNgayLap = new JTextField(9);
    JTextField txGioLap = new JTextField(9);
    JTextField txKhachHang = new JTextField(17);
    JTextField txTongTien = new JTextField(20);
    JTextField txKhuyenMai = new JTextField(17);

    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonKhachHang = new MoreButton();
    MoreButton btnChonKhuyenMai = new MoreButton();

    MyTable tbChiTietHoaDon = new MyTable();
    XoaButton btnXoa = new XoaButton();
    SuaButton btnSua = new SuaButton();
    RefreshButton btnRefresh = new RefreshButton();

    JButton btnThanhToan = new JButton("Thanh toán");
    JButton btnHuy = new JButton("Hủy");

    ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();

    public HoaDonBanHang(int _x, int _y, int _width, int _height) {
        this.setBounds(_x, _y, _width, _height);
        this.setBackground(new Color(0, 0, 0));
        this.setLayout(new FlowLayout());

        // =============== panel input =================
        int plIP_height = 180;
        JPanel plInput = new JPanel();
        plInput.setPreferredSize(new Dimension(_width - 10, plIP_height));
        plInput.setBackground(new Color(240, 240, 240));
        plInput.setLayout(new FlowLayout());

        // btn
        btnChonKhachHang.setPreferredSize(new Dimension(30, 30));
        btnChonKhachHang.addActionListener((ae) -> {
            ChonKhachHangForm ckh = new ChonKhachHangForm(txKhachHang);
            ckh.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String makh = txKhachHang.getText();
                    khachHang = qlkhBUS.getKhachHang(makh);
                    if (khachHang != null) {
                        txKhachHang.setText(khachHang.getTenKH() + " (" + khachHang.getMaKH() + ")");
                    }
                }
            });
        });

        btnChonNhanVien.setPreferredSize(new Dimension(30, 30));
        btnChonNhanVien.addActionListener((ae) -> {
            ChonNhanVienForm cnv = new ChonNhanVienForm(txNhanVien);
            cnv.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String mavn = txNhanVien.getText();
                    nhanVien = qlnvBUS.getNhanVien(mavn);
                    if (nhanVien != null) {
                        txNhanVien.setText(nhanVien.getTenNV() + " (" + nhanVien.getMaNV() + ")");
                    }
                }
            });
        });
        btnChonNhanVien.setEnabled(false);

        btnChonKhuyenMai.setPreferredSize(new Dimension(30, 30));
        btnChonKhuyenMai.addActionListener((ae) -> {
            ChonKhuyenMaiForm ckm = new ChonKhuyenMaiForm(txKhuyenMai);
            ckm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    String makm = txKhuyenMai.getText();
                    khuyenMai = qlkmBUS.getKhuyenMai(makm);
                    if (khuyenMai != null) {
                        if (!khuyenMai.getTrangThai().equals("Đang diễn ra")) {
                            JOptionPane.showMessageDialog(txKhuyenMai, "Khuyến mãi hiện " + khuyenMai.getTrangThai());
                            txKhuyenMai.setText(""); // xóa mã trong textfield
                            return;
                        }
                        txKhuyenMai.setText(khuyenMai.getTenKM() + " (" + khuyenMai.getMaKM() + ")");

                        // cập nhật lại table
                        setDataToTable(dscthd, tbChiTietHoaDon);
                    }
                }
            });
        });

        // set border
        txMaHoaDon.setBorder(BorderFactory.createTitledBorder("Mã hóa đơn:"));
        txNhanVien.setBorder(BorderFactory.createTitledBorder("Nhân viên:"));
        txNgayLap.setBorder(BorderFactory.createTitledBorder("Ngày lập:"));
        txGioLap.setBorder(BorderFactory.createTitledBorder("Giờ lập:"));
        txKhachHang.setBorder(BorderFactory.createTitledBorder("Khách hàng:"));
        txTongTien.setBorder(BorderFactory.createTitledBorder("Tổng tiền (triệu vnd):"));
        txKhuyenMai.setBorder(BorderFactory.createTitledBorder("Khuyến mãi:"));

        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        txMaHoaDon.setFont(f);
        txNhanVien.setFont(f);
        txNgayLap.setFont(f);
        txGioLap.setFont(f);
        txKhachHang.setFont(f);
        txMaHoaDon.setFont(f);
        txTongTien.setFont(f);
        txKhuyenMai.setFont(f);

        // set Text
        if (LoginForm.nhanVienLogin != null) {
            nhanVien = LoginForm.nhanVienLogin;
            txNhanVien.setText(nhanVien.getTenNV() + " (" + nhanVien.getMaNV() + ")");
        }

        txMaHoaDon.setText(qlhdBUS.getNextID());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txNgayLap.setText(LocalDate.now().toString());
                txGioLap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                if (txNhanVien.getText().equals("")
                        || txKhachHang.getText().equals("")
                        || txKhuyenMai.getText().equals("")
                        || dscthd.isEmpty()) {
                    btnThanhToan.setEnabled(false);
                } else {
                    btnThanhToan.setEnabled(true);
                }
            }
        }, 0, 1000);

        // set editable
        txMaHoaDon.setEditable(false);
        txNhanVien.setEditable(false);
        txKhachHang.setEditable(false);
        txNgayLap.setEditable(false);
        txGioLap.setEditable(false);
        txTongTien.setEditable(false);
        txKhuyenMai.setEditable(false);

        // add to panel
        plInput.add(txMaHoaDon);
        plInput.add(txTongTien);
        plInput.add(txKhachHang);
        plInput.add(btnChonKhachHang);
        plInput.add(txNhanVien);
        plInput.add(btnChonNhanVien);
        plInput.add(txNgayLap);
        plInput.add(txGioLap);
        plInput.add(txKhuyenMai);
        plInput.add(btnChonKhuyenMai);

        this.add(plInput);

        // =============== panel các sản phẩm đã chọn ==================
        int plT_height = 495;
        JPanel plTour = new JPanel();
        plTour.setPreferredSize(new Dimension(_width - 10, plT_height));
        plTour.setBackground(new Color(250, 250, 29));
        plTour.setLayout(new BorderLayout());

        int plBtn_height = 50;
        JPanel plButtonChiTiet = new JPanel();
        plButtonChiTiet.setLayout(new FlowLayout(FlowLayout.CENTER));
        plButtonChiTiet.setBackground(new Color(220, 220, 220));
        plButtonChiTiet.setPreferredSize(new Dimension(_width - 10, plBtn_height));

        btnXoa.addActionListener((ae) -> {
            btnXoaOnClick();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaOnClick();
        });
        btnRefresh.addActionListener((ae) -> {
            setDataToTable(dscthd, tbChiTietHoaDon);
        });

        plButtonChiTiet.add(btnXoa);
        plButtonChiTiet.add(btnSua);
        plButtonChiTiet.add(btnRefresh);

        tbChiTietHoaDon.setPreferredSize(new Dimension(_width - 10, plT_height - plBtn_height));
        tbChiTietHoaDon.setHeaders(new String[]{"STT", "Mã", "Tên", "Số lượng", "Đơn giá", "Thành tiền"});
        tbChiTietHoaDon.setColumnsWidth(new double[]{1, 2, 3, 2.2, 2.5, 3});
        tbChiTietHoaDon.setAlignment(0, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(1, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(3, JLabel.CENTER);
        tbChiTietHoaDon.setAlignment(4, JLabel.RIGHT);
        tbChiTietHoaDon.setAlignment(5, JLabel.RIGHT);

        plTour.add(tbChiTietHoaDon, BorderLayout.CENTER);
        plTour.add(plButtonChiTiet, BorderLayout.SOUTH);

        this.add(plTour);

        // ============= panel Thanh toán ==============
        int plTT_height = _height - plIP_height - plT_height - 20;
        JPanel plThanhToan = new JPanel();
        plThanhToan.setLayout(new FlowLayout(FlowLayout.RIGHT));
        plThanhToan.setPreferredSize(new Dimension(_width - 10, plTT_height));
        plThanhToan.setBackground(new Color(0, 0, 0));

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_cancel_30px_1.png")));
        btnThanhToan.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_us_dollar_30px.png")));

        btnHuy.addActionListener((ae) -> {
            btnHuyOnClick();
        });
        btnThanhToan.addActionListener((ae) -> {
            btnThanhToanOnClick();
        });

        plThanhToan.add(btnHuy);
        plThanhToan.add(btnThanhToan);

        this.add(plThanhToan);
    }

    private void btnHuyOnClick() {
        clear();
    }

    private void btnThanhToanOnClick() {
        HoaDon hd = new HoaDon(
                txMaHoaDon.getText(),
                nhanVien.getMaNV(),
                khachHang.getMaKH(),
                khuyenMai.getMaKM(),
                LocalDate.parse(txNgayLap.getText()),
                LocalTime.parse(txGioLap.getText()),
                Float.parseFloat(txTongTien.getText()));
        qlhdBUS.add(hd);

        for (ChiTietHoaDon ct : dscthd) {
            qlcthd.add(ct);
        }
        
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                        "Thanh toán thành công, bạn có muốn IN HÓA ĐƠN?", "Thành công",
                        JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.OK_OPTION) {
            new WritePDF().writeHoaDon(txMaHoaDon.getText());
        }
        txMaHoaDon.setText(qlhdBUS.getNextID());
        clear();
        this.target.refreshAll();
    }

    private void btnXoaOnClick() {
        int i = tbChiTietHoaDon.getTable().getSelectedRow();
        if (i >= 0 && i < dscthd.size()) {
            dscthd.remove(i);
            setDataToTable(dscthd, tbChiTietHoaDon);
        }
    }

    private void btnSuaOnClick() {
        int i = tbChiTietHoaDon.getTable().getSelectedRow();
        if (i >= 0 && i < dscthd.size()) {
            ChiTietHoaDon ct = dscthd.get(i);
            this.target.showInfo(ct.getMaTour(), ct.getSoLuong());

            dscthd.remove(i);
            setDataToTable(dscthd, tbChiTietHoaDon);
        }
    }

    public void clear() {
        txKhachHang.setText("");
        txTongTien.setText("");
        txKhuyenMai.setText("");
        dscthd.clear();
        setDataToTable(dscthd, tbChiTietHoaDon);
    }

    @Override
    public void addChiTiet(String mat, int soluong) {
        Tour t = qltBUS.getTour(mat);

        Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa
        for (ChiTietHoaDon cthd : dscthd) {
            if (cthd.getMaTour().equals(t.getMaT())) {
                int tongSoLuong = soluong + cthd.getSoLuong();
                if (tongSoLuong > t.getSoLuong()) {
                    JOptionPane.showMessageDialog(this, "Số lượng tour trong csdl không đủ (" + t.getSoLuong() + ")");
                    return;
                }
                cthd.setSoLuong(tongSoLuong); // có rồi thì thay đổi số lượng
                daCo = true;
            }
        }

        if (!daCo) { // nếu chưa có thì thêm mới
            if (soluong > t.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Số lượng tour trong csdl không đủ (" + t.getSoLuong() + ")");
                return;
            }
            ChiTietHoaDon cthd = new ChiTietHoaDon(qlhdBUS.getNextID(), mat, soluong, t.getDonGia());
            dscthd.add(cthd);
        }

        // cập nhật lại table
        setDataToTable(dscthd, tbChiTietHoaDon);
    }

    public void setDataToTable(ArrayList<ChiTietHoaDon> arr, MyTable tb) {
        tb.clear();
        float tongtien = 0;
        int stt = 1;
        for (ChiTietHoaDon cthd : arr) {
            String mat = cthd.getMaTour();
            Tour t = qltBUS.getTour(mat);
            String tent = t.getTenT();
            int soluong = cthd.getSoLuong();
            float dongia = cthd.getDonGia();
            float thanhtien = soluong * dongia;

            tb.addRow(new String[]{
                String.valueOf(stt),
                mat,
                tent,
                String.valueOf(soluong),
                PriceFormatter.format(dongia),
                PriceFormatter.format(thanhtien)
            });
            stt++;
            tongtien += thanhtien;
        }

        // check khuyến mãi
        tb.addRow(new String[]{"", "", "", "", "", ""});
        tb.addRow(new String[]{"", "", "", "", "Tổng tiền", PriceFormatter.format(tongtien)});
        if (khuyenMai != null && khuyenMai.getPhanTramKM() > 0 && khuyenMai.getDieuKienKM() <= tongtien) {
            float giaTriKhuyenMai = tongtien * khuyenMai.getPhanTramKM() / 100;
            float tongTienSauKhuyenMai = tongtien - giaTriKhuyenMai;
            tb.addRow(new String[]{"", "", "", "", "Khuyến mãi", PriceFormatter.format(-giaTriKhuyenMai)});
            tb.addRow(new String[]{"", "", "", "", "Còn lại", PriceFormatter.format(tongTienSauKhuyenMai)});
            txTongTien.setText(String.valueOf(tongTienSauKhuyenMai));
        } else {
            txTongTien.setText(String.valueOf(tongtien));
        }
    }
}

class ChonTourPanel extends JPanel {

    QuanLyLoaiTourBUS qlltBUS = new QuanLyLoaiTourBUS();
    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    MyTable tbTour = new MyTable();
    JTextField txTimKiem = new JTextField(30);

    JLabel lbImage = new JLabel();
    JTextField txMaT = new JTextField(12);
    JTextField txLoaiT = new JTextField(12);
    JTextField txTenT = new JTextField(12);
    JTextField txDonGia = new JTextField(12);
    JTextField txSoLuong = new JTextField(7);

    RefreshButton btnRefresh = new RefreshButton();
    ThemButton btnThem = new ThemButton();

    FormHang target = new FormHang();

    public ChonTourPanel(int _x, int _y, int _width, int _height) {
        this.setBounds(_x, _y, _width, _height);
        this.setBackground(new Color(59, 68, 75));
        this.setLayout(new BorderLayout());

        // panel hiển thị sản phẩm
        int plT_height = _height - 300;
        JPanel plTour = new JPanel();
        plTour.setPreferredSize(new Dimension(_width - 10, plT_height));
        plTour.setBackground(new Color(49, 49, 49));
        plTour.setLayout(new BorderLayout());

        JPanel plTimKiem = new JPanel();
        txTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTimKiem.setHorizontalAlignment(JLabel.CENTER);
        addDocumentListener(txTimKiem);
        plTimKiem.add(txTimKiem);
        btnRefresh.addActionListener((ae) -> {
            refreshTable();
        });
        plTimKiem.add(btnRefresh);
        plTour.add(plTimKiem, BorderLayout.NORTH);

        tbTour.setHeaders(new String[]{"Mã", "Loại", "Tên", "Đơn giá", "Số lượng"});
        tbTour.setColumnsWidth(new double[]{.5, .5, 3, 1, .5});
        tbTour.setAlignment(3, JLabel.RIGHT);
        tbTour.setAlignment(4, JLabel.RIGHT);
        tbTour.setupSort();
        plTour.add(tbTour, BorderLayout.CENTER);

        this.add(plTour, BorderLayout.CENTER);

        // =========== panel chi tiết sản phẩm chọn ================
        JPanel plChiTiet = new JPanel();
        plChiTiet.setPreferredSize(new Dimension(_width - 10, 258));
        plChiTiet.setBackground(new Color(240, 240, 240));
        plChiTiet.setLayout(new BorderLayout());

        lbImage.setBackground(Color.yellow);
        lbImage.setPreferredSize(new Dimension(250, 250));
        plChiTiet.add(lbImage, BorderLayout.WEST);

        JPanel plTextField = new JPanel();
        plTextField.setLayout(new FlowLayout());
        plTextField.setBackground(new Color(240, 240, 240));
        // border
        txMaT.setBorder(BorderFactory.createTitledBorder("Mã tour"));
        txLoaiT.setBorder(BorderFactory.createTitledBorder("Loại tour"));
        txTenT.setBorder(BorderFactory.createTitledBorder("Tên tour"));
        txDonGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        // disable
        txMaT.setEditable(false);
        txLoaiT.setEditable(false);
        txTenT.setEditable(false);
        txDonGia.setEditable(false);
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        txMaT.setFont(f);
        txLoaiT.setFont(f);
        txTenT.setFont(f);
        txDonGia.setFont(f);
        txSoLuong.setFont(f);
        // add to panel
        plTextField.add(txMaT);
        plTextField.add(txLoaiT);
        plTextField.add(txTenT);
        plTextField.add(txDonGia);
        plTextField.add(txSoLuong);

        btnThem.addActionListener((ae) -> {
            try {
                String mat = txMaT.getText();
                int soluong = Integer.parseInt(txSoLuong.getText());
                if (soluong > 0) {
                    this.target.addChiTiet(mat, soluong);
                } else {
                    JOptionPane.showMessageDialog(txSoLuong, "Số lượng phải là số dương!");
                    txSoLuong.requestFocus();
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(txSoLuong, "Số lượng phải là số nguyên!");
                txSoLuong.requestFocus();
            }
        });

        plChiTiet.add(plTextField, BorderLayout.CENTER);
        plChiTiet.add(btnThem, BorderLayout.SOUTH);

        this.add(plChiTiet, BorderLayout.SOUTH);

        // event
        tbTour.getTable().addMouseListener(new MouseAdapter() { // copy từ HienThiTour
            @Override
            public void mouseReleased(MouseEvent me) {
                String mat = getSelectedTour(0);
                if (mat != null) {
                    showInfo(mat, 1);
                }
            }
        });

        refreshAll();
    }

    public void setTarget(FormHang target) {
        this.target = target;
    }

    public void refreshTable() {
        qltBUS.readDB();
        setDataToTable(qltBUS.search("", "Tất cả", -1, -1, -1, -1, 0), tbTour);
    }

    public void refreshAll() {
        refreshTable();
        txMaT.setText("");
        txLoaiT.setText("");
        txTenT.setText("");
        txDonGia.setText("");
        txSoLuong.setText("");
        lbImage.setIcon(null);
    }

    public void showInfo(String mat, int soluong) {
        // https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
        if (mat != null) {
            // show hình
            for (Tour t : qltBUS.getDst()) {
                if (t.getMaT().equals(mat)) {
                    int w = lbImage.getWidth();
                    int h = lbImage.getHeight();
                    ImageIcon img = new ImageIcon(getClass().getResource("/giaodienchuan/images/Product Images/" + t.getFileNameHinhAnh()));
                    Image imgScaled = img.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT);
                    lbImage.setIcon(new ImageIcon(imgScaled));

                    // show info
                    String loai = qlltBUS.getLoaiTour(t.getMaLT()).getTenLT();
                    txMaT.setText(t.getMaT());
                    txTenT.setText(t.getTenT());
                    txLoaiT.setText(loai + " - " + t.getMaLT());
                    txDonGia.setText(PriceFormatter.format(t.getDonGia()));
                    txSoLuong.setText(String.valueOf(soluong));
                    return;
                }
            }
        }
    }

    private void addDocumentListener(JTextField tx) {
        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        tx.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txSearchOnChange();
            }
        });
    }

    public void txSearchOnChange() {
        setDataToTable(qltBUS.search(txTimKiem.getText(), "Tất cả", -1, -1, -1, -1, 0), tbTour);
    }

    public String getSelectedTour(int col) {
        int i = tbTour.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = tbTour.getTable().convertRowIndexToModel(i);
            return tbTour.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    private void setDataToTable(ArrayList<Tour> data, MyTable table) {
        table.clear();
        for (Tour t : data) {
            if (t.getTrangThai() == 0) {
                table.addRow(new String[]{
                    t.getMaT(),
                    t.getMaLT(),
                    t.getTenT(),
                    PriceFormatter.format(t.getDonGia()),
                    String.valueOf(t.getSoLuong()),});
            }
        }
    }
}

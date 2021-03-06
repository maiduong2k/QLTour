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
import giaodienchuan.model.FrontEnd.FormChon.ChonKhachHangForm;
import giaodienchuan.model.FrontEnd.FormChon.ChonKhachSanForm;
import giaodienchuan.model.FrontEnd.FormChon.ChonNhanVienForm;
import giaodienchuan.model.FrontEnd.FormChon.ChonTourForm;
import giaodienchuan.model.FrontEnd.GiaoDienChuan.MyTable;
import giaodienchuan.model.FrontEnd.MyButton.DateButton;
import giaodienchuan.model.FrontEnd.MyButton.MoreButton;
import giaodienchuan.model.FrontEnd.MyButton.RefreshButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author nguye
 */
public class ThongKeForm extends JPanel {

    public static final int width = 1120, height = 740;

    public ThongKeForm() {
        this.setBackground(Color.darkGray);

        ThongKe_Hoang tkH = new ThongKe_Hoang();
        ThongKeForm_NewVersion tk2 = new ThongKeForm_NewVersion();

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.setPreferredSize(new Dimension(width, height));

        //add tab thong ke san pham
//        tabs.addTab("Th???ng k?? ver2", getIcon("icons8_company_30px.png"), tk2);
        tabs.addTab("Th???ng k?? t???ng qu??t", getIcon("icons8_pie_chart_30px.png"), tkH);
        tabs.addTab("Tour", getIcon("icons8_dossier_folder_30px.png"), null);
        tabs.addTab("Nh??n vi??n", getIcon("icons8_assistant_30px.png"), null);
        tabs.addTab("Kh??ch h??ng", getIcon("icons8_user_30px.png"), null);
        tabs.addTab("Kh??ch s???n", getIcon("icons8_company_30px.png"), null);

        tabs.addChangeListener((ce) -> {
            int i = tabs.getSelectedIndex();
            if (tabs.getComponentAt(i) == null) {
                switch (tabs.getTitleAt(i)) {
                    case "Tour":
                        tabs.setComponentAt(i, new ThongKeTour());
                        break;
                    case "Nh??n vi??n":
                        tabs.setComponentAt(i, new ThongKeNhanVien());
                        break;
                    case "Kh??ch h??ng":
                        tabs.setComponentAt(i, new ThongKeKhachHang());
                        break;
                    case "Kh??ch s???n":
                        tabs.setComponentAt(i, new ThongKeNhaCungCap());
                        break;
                }
            }
        });

        this.add(tabs);
    }

    private ImageIcon getIcon(String filename) {
        return new ImageIcon(getClass().getResource("/giaodienchuan/images/" + filename));
    }
}

class ThongKe_Hoang extends JPanel {

    QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
    QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();
    QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
    QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();
    QuanLyChiTietHoaDonBUS qlcthdBUS = new QuanLyChiTietHoaDonBUS();
    QuanLyChiTietPhieuNhapBUS qlctpnBUS = new QuanLyChiTietPhieuNhapBUS();

    JTextField txNgay1 = new JTextField(7);
    JTextField txNgay2 = new JTextField(7);
    JTextField txNhanVien = new JTextField(10);
    JTextField txKhachHang = new JTextField(10);
    JTextField txKSan = new JTextField(10);
    JTextField txTour = new JTextField(10);

    DatePicker dPicker1;
    DatePicker dPicker2;
    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonKhachHang = new MoreButton();
    MoreButton btnChonKSan = new MoreButton();
    MoreButton btnChonTour = new MoreButton();

    JTabbedPane tabDoiTuongThongKe = new JTabbedPane();
    JPanel plThongKeHoaDon = new JPanel();
    JPanel plThongKePhieuNhap = new JPanel();
    JPanel plThongKeSoLuong = new JPanel();

    MyTable tbThongKeHoaDon = new MyTable();
    MyTable tbThongKePhieuNhap = new MyTable();

    MyTable tbKetQuaHoaDon = new MyTable();
    MyTable tbKetQuaPhieuNhap = new MyTable();

    JPanel plTour, plNhanVien, plKhachHang, plKSan;
    RefreshButton btnRefresh = new RefreshButton();

    public ThongKe_Hoang() {
        setLayout(new BorderLayout());

        // panel chon ngay
        DatePickerSettings ds = new DatePickerSettings();
        ds.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(ds);
        dPicker2 = new DatePicker(ds.copySettings());
        dPicker1.addDateChangeListener((dce) -> {
            txNgay1.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2.addDateChangeListener((dce) -> {
            txNgay2.setText(dPicker2.getDateStringOrEmptyString());
        });
        DateButton db = new DateButton(dPicker1);
        DateButton db2 = new DateButton(dPicker2);
        
        txNgay1.setBorder(BorderFactory.createTitledBorder("T???"));
        txNgay2.setBorder(BorderFactory.createTitledBorder("?????n"));

        JPanel plChonNgay = new JPanel();
        plChonNgay.setBorder(BorderFactory.createTitledBorder("Kho???ng ng??y"));

        addDocumentListener(txNgay1);
        addDocumentListener(txNgay2);
        plChonNgay.add(txNgay1);
        plChonNgay.add(dPicker1);
        plChonNgay.add(txNgay2);
        plChonNgay.add(dPicker2);
        
        // event
        btnChonTour.addActionListener((ae) -> {
            ChonTourForm cnv = new ChonTourForm(txTour, null, null, null, null, null, null);
        });
        btnChonNhanVien.addActionListener((ae) -> {
            ChonNhanVienForm cnv = new ChonNhanVienForm(txNhanVien);
        });
        btnChonKhachHang.addActionListener((ae) -> {
            ChonKhachHangForm ckh = new ChonKhachHangForm(txKhachHang);
        });
        btnChonKSan.addActionListener((ae) -> {
            ChonKhachSanForm cnv = new ChonKhachSanForm(txKSan);
        });
        btnRefresh.addActionListener((ae) -> {
            refresh();
        });

        plTour = getPanelTieuChi("Tour", txTour, btnChonTour);
        plNhanVien = getPanelTieuChi("Nh??n vi??n", txNhanVien, btnChonNhanVien);
        plKhachHang = getPanelTieuChi("Kh??ch h??ng", txKhachHang, btnChonKhachHang);
        plKSan = getPanelTieuChi("Kh??ch s???n", txKSan, btnChonKSan);

        // panel chon tieu chi
        JPanel plChonTieuChi = new JPanel();
        plChonTieuChi.add(plChonNgay);
        plChonTieuChi.add(plTour);
        plChonTieuChi.add(plNhanVien);
        plChonTieuChi.add(plKhachHang);
        plChonTieuChi.add(plKSan);
        plChonTieuChi.add(btnRefresh);
        this.add(plChonTieuChi, BorderLayout.NORTH);

        // panel thong ke hoa don (ban duoc)
        plThongKeHoaDon.setLayout(new BorderLayout());
        tbThongKeHoaDon.setHeaders(new String[]{"H??a ????n", "T??n nh??n vi??n", "T??n kh??ch h??ng", "T??n tour", "S??? l?????ng", "????n gi??", "Th??nh ti???n"});
        tbThongKeHoaDon.setAlignment(0, JLabel.CENTER);
        tbThongKeHoaDon.setAlignment(4, JLabel.CENTER);
        tbThongKeHoaDon.setAlignment(5, JLabel.RIGHT);
        tbThongKeHoaDon.setAlignment(6, JLabel.RIGHT);
        plThongKeHoaDon.add(tbThongKeHoaDon, BorderLayout.CENTER);

        tbKetQuaHoaDon.setHeaders(new String[]{"T???NG T???T C???", "", "", "", "", "", "T???NG B??N RA"});
        tbKetQuaHoaDon.setPreferredSize(new Dimension(ThongKeForm.width, 75));
        tbKetQuaHoaDon.setAlignment(0, JLabel.CENTER);
        tbKetQuaHoaDon.setAlignment(4, JLabel.CENTER);
        tbKetQuaHoaDon.setAlignment(5, JLabel.RIGHT);
        tbKetQuaHoaDon.setAlignment(6, JLabel.RIGHT);
        plThongKeHoaDon.add(tbKetQuaHoaDon, BorderLayout.SOUTH);

        // panal thong ke phieu nhap (nhap kho)
        plThongKePhieuNhap.setLayout(new BorderLayout());
        tbThongKePhieuNhap.setHeaders(new String[]{"Phi???u nh???p", "T??n nh??n vi??n", "T??n kh??ch s???n", "T??n tour", "S??? l?????ng", "????n gi??", "Th??nh ti???n"});
        tbThongKePhieuNhap.setAlignment(0, JLabel.CENTER);
        tbThongKePhieuNhap.setAlignment(4, JLabel.CENTER);
        tbThongKePhieuNhap.setAlignment(5, JLabel.RIGHT);
        tbThongKePhieuNhap.setAlignment(6, JLabel.RIGHT);
        plThongKePhieuNhap.add(tbThongKePhieuNhap, BorderLayout.CENTER);

        tbKetQuaPhieuNhap.setHeaders(new String[]{"T???NG T???T C???", "", "", "", "", "", "T???NG NH???P V??O"});
        tbKetQuaPhieuNhap.setPreferredSize(new Dimension(ThongKeForm.width, 75));
        tbKetQuaPhieuNhap.setAlignment(0, JLabel.CENTER);
        tbKetQuaPhieuNhap.setAlignment(4, JLabel.CENTER);
        tbKetQuaPhieuNhap.setAlignment(5, JLabel.RIGHT);
        tbKetQuaPhieuNhap.setAlignment(6, JLabel.RIGHT);
        plThongKePhieuNhap.add(tbKetQuaPhieuNhap, BorderLayout.SOUTH);
        
        // panel thong ke tong so
        plThongKeSoLuong = new JPanel();
        setDataToPanelTong();

        // tabpane doi tuong thong ke
        tabDoiTuongThongKe.setBackground(Color.yellow);
        tabDoiTuongThongKe.addTab("T???ng", getIcon("icons8_futures_30px.png"), plThongKeSoLuong);
        tabDoiTuongThongKe.addTab("B??n ra", getIcon("icons8_small_business_30px_3.png"), plThongKeHoaDon);
        tabDoiTuongThongKe.addTab("Nh???p v??o", getIcon("icons8_downloads_30px.png"), plThongKePhieuNhap);

        // event chuyen tab
        // tab ban dau la hoa don, nen c???n ???n nha cung cap 
        plKSan.setVisible(false);
        // event
        tabDoiTuongThongKe.addChangeListener((ce) -> {
            Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
            plKSan.setVisible(!HoaDon_isSelected);
            plKhachHang.setVisible(HoaDon_isSelected);
        });

        this.add(tabDoiTuongThongKe, BorderLayout.CENTER);

        // show gi?? tr??? ?????u
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
    }
    
    private void setDataToPanelTong() {
        plThongKeSoLuong.removeAll();
        plThongKeSoLuong.add(getJPanelTong("TOUR", "icons8-add-book-100.png", qltBUS.getDst().size(), Color.BLUE));
        plThongKeSoLuong.add(getJPanelTong("NH??N VI??N", "icons8_assistant_100px.png", qlnvBUS.getDsnv().size(), Color.BLUE));
        plThongKeSoLuong.add(getJPanelTong("KH??CH H??NG", "icons8_person_male_100px.png", qlkhBUS.getDskh().size(), Color.BLUE));
        plThongKeSoLuong.add(getJPanelTong("KH??CH S???N", "icons8_company_100px.png", qlksBUS.getDsks().size(), Color.BLUE));
    }

    public void refresh() {
        qlcthdBUS.readDB();
        qlhdBUS.readDB();
        qlkhBUS.readDB();
        qlksBUS.readDB();
        qlnvBUS.readDB();
        qltBUS.readDB();
        dPicker1.setDate(null);
        dPicker2.setDate(null);
        txTour.setText("");
        txNhanVien.setText("");
        txKhachHang.setText("");
        txKSan.setText("");

        Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
        if (HoaDon_isSelected) {
            onChangeThongKeBanHang();
        } else {
            onChangeThongKeNhapHang();
        }
        setDataToPanelTong();
    }

    private JPanel getPanelTieuChi(String name, JTextField tx, MoreButton b) {
        JPanel result = new JPanel();
        result.setBorder(BorderFactory.createTitledBorder(name));
        tx.setBorder(BorderFactory.createTitledBorder(" "));

        addDocumentListener(tx);

        result.add(tx);
        result.add(b);

        return result;
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                Boolean HoaDon_isSelected = (tabDoiTuongThongKe.getSelectedComponent() == plThongKeHoaDon);
                if (HoaDon_isSelected) {
                    onChangeThongKeBanHang();
                } else {
                    onChangeThongKeNhapHang();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
        });
    }

    private ImageIcon getIcon(String filename) {
        return new ImageIcon(getClass().getResource("/giaodienchuan/images/" + filename));
    }

    private void onChangeThongKeBanHang() {
        tbThongKeHoaDon.clear();

        int tongSLHoaDon = 0;
        int tongSLTour = 0;
        float tongTatCaTien = 0;

        String matLoc = txTour.getText();
        String manvLoc = txNhanVien.getText();
        String makhLoc = txKhachHang.getText();

        ArrayList<NhanVien> dsnv = new ArrayList<>(); // danh s??ch l??u c??c nh??n vi??n c?? l??m h??a ????n
        ArrayList<KhachHang> dskh = new ArrayList<>(); // danh s??ch l??u c??c kh??ch h??ng c?? l??m h??a ????n
        ArrayList<Tour> dst = new ArrayList<>(); // l??u c??c s???n ph???m

        MyCheckDate mcd = new MyCheckDate(txNgay1, txNgay2);

        for (HoaDon hd : qlhdBUS.search("T???t c???", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
            float tongTien = 0;
            ArrayList<ChiTietHoaDon> dscthd = qlcthdBUS.getAllChiTiet(hd.getMaHoaDon());

            if (dscthd.size() > 0) {
                NhanVien nv = qlnvBUS.getNhanVien(hd.getMaNhanVien());
                KhachHang kh = qlkhBUS.getKhachHang(hd.getMaKhachHang());

                // l???c theo textfield m??
                // b??? qua l???n l???p for n??y n???u nh??n vi??n ho???c kh??ch h??ng ko th???a b??? l???c
                if (!manvLoc.equals("") && !nv.getMaNV().equals(manvLoc)
                        || !makhLoc.equals("") && !kh.getMaKH().equals(makhLoc)) {
                    continue; // continue n??y s??? l???y v??ng l???p HoaDon ti???p theo
                }

                // th??m v??o arraylist ????? ?????m
                if (!dsnv.contains(nv)) {
                    dsnv.add(nv); // th??m v??o n???u ch??a c??
                }
                if (!dskh.contains(kh)) {
                    dskh.add(kh); // th??m v??o n???u ch??a c??
                }

                tbThongKeHoaDon.addRow(new String[]{
                    hd.getMaHoaDon() + " (" + hd.getNgayLap().toString() + ")",
                    nv.getTenNV() + " (" + nv.getMaNV() + ")",
                    kh.getTenKH() + " (" + kh.getMaKH() + ")",
                    "", "", "", ""
                });

                for (ChiTietHoaDon cthd : dscthd) {
                    Tour t = qltBUS.getTour(cthd.getMaTour());

                    // l???c
                    if (!matLoc.equals("") && !t.getMaT().equals(matLoc)) {
                        continue; // continue n??y s??? l???y v??ng l???p ChiTietHoaDon ti???p theo
                    }

                    // th??m v??o danh s??ch ????? ?????m
                    if (!dst.contains(t)) {
                        dst.add(t); // th??m v??o n???u ch??a c??
                    }

                    tbThongKeHoaDon.addRow(new String[]{"", "", "",
                        t.getTenT() + " (" + t.getMaT() + ")",
                        String.valueOf(cthd.getSoLuong()),
                        PriceFormatter.format(cthd.getDonGia()),
                        PriceFormatter.format(cthd.getSoLuong() * cthd.getDonGia())
                    });

                    tongTien += cthd.getDonGia() * cthd.getSoLuong();
                    tongSLTour += cthd.getSoLuong();
                }
            }
            tbThongKeHoaDon.addRow(new String[]{"", "", "", "", "", "T???ng c???ng", PriceFormatter.format(tongTien)});
            tbThongKeHoaDon.addRow(new String[]{"", "", "", "", "", "", ""});

            tongTatCaTien += tongTien;
            tongSLHoaDon++;
        }

        tbKetQuaHoaDon.clear();
        tbKetQuaHoaDon.addRow(new String[]{
            tongSLHoaDon + " h??a ????n",
            dsnv.size() + " nh??n vi??n",
            dskh.size() + " kh??ch h??ng",
            dst.size() + " m???t h??ng",
            tongSLTour + " tour",
            "",
            PriceFormatter.format(tongTatCaTien)
        });
    }

    private void onChangeThongKeNhapHang() {
        tbThongKePhieuNhap.clear();

        int tongSLPhieuNhap = 0;
        int tongSLTour = 0;
        float tongTatCaTien = 0;

        String matLoc = txTour.getText();
        String manvLoc = txNhanVien.getText();
        String maksLoc = txKSan.getText();

        ArrayList<NhanVien> dsnv = new ArrayList<>(); // danh s??ch l??u c??c nh??n vi??n c?? l??m phi???u nh???p
        ArrayList<KhachSan> dsks = new ArrayList<>(); // danh s??ch l??u c??c ks c?? l??m phi???u nh???p
        ArrayList<Tour> dst = new ArrayList<>(); // l??u c??c s???n ph???m

        MyCheckDate mcd = new MyCheckDate(txNgay1, txNgay2);

        for (PhieuNhap pn : qlpnBUS.search("T???t c???", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
            float tongTien = 0;
            ArrayList<ChiTietPhieuNhap> dsctpn = qlctpnBUS.getAllChiTiet(pn.getMaPN());

            if (dsctpn.size() > 0) {
                NhanVien nv = qlnvBUS.getNhanVien(pn.getMaNV());
                KhachSan ks = qlksBUS.getKhachSan(pn.getMaKS());

                // l???c theo textfield m??
                // b??? qua l???n l???p for n??y n???u nh??n vi??n ho???c nha cung cap ko th???a b??? l???c
                if (!manvLoc.equals("") && !nv.getMaNV().equals(manvLoc)
                        || !maksLoc.equals("") && !ks.getMaKS().equals(maksLoc)) {
                    continue; // continue n??y s??? l???y v??ng l???p PhieuNhap ti???p theo
                }

                // th??m v??o arraylist ????? ?????m
                if (!dsnv.contains(nv)) {
                    dsnv.add(nv); // th??m v??o n???u ch??a c??
                }
                if (!dsks.contains(ks)) {
                    dsks.add(ks); // th??m v??o n???u ch??a c??
                }

                tbThongKePhieuNhap.addRow(new String[]{
                    pn.getMaPN() + " (" + pn.getNgayNhap().toString() + ")",
                    nv.getTenNV() + " (" + nv.getMaNV() + ")",
                    ks.getTenKS() + " (" + ks.getMaKS() + ")",
                    "", "", "", ""
                });

                for (ChiTietPhieuNhap ctpn : dsctpn) {
                    Tour t = qltBUS.getTour(ctpn.getMaT());

                    // l???c
                    if (!matLoc.equals("") && !t.getMaT().equals(matLoc)) {
                        continue; // continue n??y s??? l???y v??ng l???p ChiTietPhieuNhap ti???p theo
                    }

                    // th??m v??o danh s??ch ????? ?????m
                    if (!dst.contains(t)) {
                        dst.add(t); // th??m v??o n???u ch??a c??
                    }

                    tbThongKePhieuNhap.addRow(new String[]{"", "", "",
                        t.getTenT() + " (" + t.getMaT() + ")",
                        String.valueOf(ctpn.getSoLuong()),
                        PriceFormatter.format(ctpn.getDonGia()),
                        PriceFormatter.format(ctpn.getSoLuong() * ctpn.getDonGia())
                    });

                    tongTien += ctpn.getDonGia() * ctpn.getSoLuong();
                    tongSLTour += ctpn.getSoLuong();
                }
            }
            tbThongKePhieuNhap.addRow(new String[]{"", "", "", "", "", "T???ng c???ng", PriceFormatter.format(tongTien)});
            tbThongKePhieuNhap.addRow(new String[]{"", "", "", "", "", "", ""});

            tongTatCaTien += tongTien;
            tongSLPhieuNhap++;
        }

        tbKetQuaPhieuNhap.clear();
        tbKetQuaPhieuNhap.addRow(new String[]{
            tongSLPhieuNhap + " phi???u nh???p",
            dsnv.size() + " nh??n vi??n",
            dsks.size() + " kh??ch s???n",
            dst.size() + " m???t h??ng",
            tongSLTour + " tour",
            "",
            PriceFormatter.format(tongTatCaTien)
        });
    }
    
    private JPanel getJPanelTong(String name, String iconName, int soluong, Color c) {
        JPanel result = new JPanel();
        result.setLayout(new BorderLayout());
        result.setPreferredSize(new Dimension(ThongKeForm.width / 4 - 15, 200));
        result.setBorder(BorderFactory.createLineBorder(c));
        
        // hinh anh
        JLabel lbIcon = new JLabel();
        lbIcon.setIcon(getIcon(iconName));
        result.add(lbIcon, BorderLayout.WEST);
        
        // tieu de
        JPanel plLeft = new JPanel();
        
        JLabel lbTieuDe = new JLabel(name, JLabel.CENTER);
        lbTieuDe.setPreferredSize(new Dimension(ThongKeForm.width / 4 - 100, 70));
        lbTieuDe.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        plLeft.add(lbTieuDe);
        
        JLabel lbSoLuong = new JLabel(String.valueOf(soluong), JLabel.CENTER);
        lbSoLuong.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
        lbSoLuong.setForeground(c);
        plLeft.add(lbSoLuong);
        
        result.add(plLeft, BorderLayout.CENTER);
        
        return result;
    }
}

class MyCheckDate {

    LocalDate fromDate = null;
    LocalDate toDate = null;
    String khoangTG = "";

    public MyCheckDate(JTextField txFrom, JTextField txTo) {
        try {
            fromDate = LocalDate.parse(txFrom.getText());
            txFrom.setForeground(Color.black);
            khoangTG += String.valueOf(fromDate);

        } catch (DateTimeParseException e) {
            txFrom.setForeground(Color.red);
            khoangTG += "T??? ng??y ?????u";
        }
        try {
            toDate = LocalDate.parse(txTo.getText());
            txTo.setForeground(Color.black);
            khoangTG += " ?????n " + String.valueOf(toDate);

        } catch (DateTimeParseException e) {
            txTo.setForeground(Color.red);
            khoangTG += " ?????n nay";
        }
    }

    public LocalDate getNgayTu() {
        return fromDate;
    }

    public LocalDate getNgayDen() {
        return toDate;
    }

    public String getKhoangTG() {
        return khoangTG;
    }
}

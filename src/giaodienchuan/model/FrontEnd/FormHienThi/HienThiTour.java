package giaodienchuan.model.FrontEnd.FormHienThi;

import giaodienchuan.model.BackEnd.PriceFormatter;
import giaodienchuan.model.BackEnd.QuanLyLoaiTour.QuanLyLoaiTourBUS;
import giaodienchuan.model.BackEnd.QuanLyDoan.QuanLyDoanBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.QuanLyTourBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.Tour;
import giaodienchuan.model.FrontEnd.GiaoDienChuan.LoginForm;
import giaodienchuan.model.FrontEnd.GiaoDienChuan.MyTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HienThiTour extends FormHienThi {

    QuanLyLoaiTourBUS qlltBUS = new QuanLyLoaiTourBUS();
    QuanLyDoanBUS qldoanBUS = new QuanLyDoanBUS();
    QuanLyTourBUS qltBUS = new QuanLyTourBUS();

    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");

    JLabel lbImage = new JLabel();
    JTextField txMaT = new JTextField(12);
    JTextField txLoaiT = new JTextField(12);
    JTextField txTenT = new JTextField(12);
    JTextField txDonGia = new JTextField(12);
    JTextField txSoLuong = new JTextField(7);
    JTextField txtD = new JTextField(12);

    JTextField txSoLuong1 = new JTextField(5);
    JTextField txSoLuong2 = new JTextField(5);
    JTextField txGia1 = new JTextField(6);
    JTextField txGia2 = new JTextField(6);

    // index
    final int MAT_I = 1, MALT_I = 2, TEN_I = 3, GIA_I = 4, SOLUONG_I = 5, MADoan_I = 6;

    public HienThiTour() {
        setLayout(new BorderLayout());

        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 350));
        mtb.setHeaders(new String[]{"STT", "Mã tour", "Mã loại", "Tên", "Đơn giá (triệu)", "Số lượng", "Hình ảnh", "Trạng thái", "Mã Đoàn"});
        mtb.setColumnsWidth(new double[]{.5, 1, 1, 2, 1.5, 1, 2, 1, 1, 1});
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setAlignment(4, JLabel.RIGHT);
        mtb.setAlignment(5, JLabel.CENTER);
        mtb.setAlignment(7, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qltBUS.getDst(), mtb);

        // ======== search panel ===========
        JPanel plHeader = new JPanel();
        
        cbTypeSearch = new JComboBox<>(new String[]{"Tất cả", "Mã tour", "Mã loại", "Tên", "Đơn giá (triệu)", "Số lượng", "Trạng thái", "Mã Đoàn"});
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả"));
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);

        // so sánh số lượng
        JPanel plSoSanhSoLuong = new JPanel();
        plSoSanhSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        txSoLuong1.setBorder(BorderFactory.createTitledBorder("Từ:"));
        txSoLuong2.setBorder(BorderFactory.createTitledBorder("Tới:"));
        plSoSanhSoLuong.add(txSoLuong1);
        plSoSanhSoLuong.add(txSoLuong2);
        plHeader.add(plSoSanhSoLuong);

        // so sánh giá
        JPanel plSoSanhGia = new JPanel();
        plSoSanhGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txGia1.setBorder(BorderFactory.createTitledBorder("Từ:"));
        txGia2.setBorder(BorderFactory.createTitledBorder("Tới:"));
        plSoSanhGia.add(txGia1);
        plSoSanhGia.add(txGia2);
        plHeader.add(plSoSanhGia);

        // btn refresh
        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_data_backup_30px.png")));
        plHeader.add(btnRefresh);

        // add action listener
        cbTypeSearch.addActionListener((ActionEvent e) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(cbTypeSearch.getSelectedItem().toString()));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });

        btnRefresh.addActionListener((ae) -> {
            refresh();
        });

        addDocumentListener(txTim);
        addDocumentListener(txSoLuong1);
        addDocumentListener(txSoLuong2);
        addDocumentListener(txGia1);
        addDocumentListener(txGia2);

        mtb.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                String mat = getSelectedRow(1);
                showInfo(mat);
            }
        });

        // panel image ----------- Copy From BanHangForm
        JPanel plImage = new JPanel();
        lbImage.setPreferredSize(new Dimension(250, 250));
        lbImage.setBorder(BorderFactory.createLineBorder(Color.black));
        plImage.add(lbImage);

        JPanel plTextField = new JPanel();
        plTextField.setPreferredSize(new Dimension(400, 250));
        plTextField.setLayout(new FlowLayout());
        plTextField.setBackground(new Color(240, 240, 240));
        // border
        txMaT.setBorder(BorderFactory.createTitledBorder("Mã tour"));
        txLoaiT.setBorder(BorderFactory.createTitledBorder("Loại tour"));
        txTenT.setBorder(BorderFactory.createTitledBorder("Tên tour"));
        txDonGia.setBorder(BorderFactory.createTitledBorder("Đơn giá"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        txtD.setBorder(BorderFactory.createTitledBorder("Đoàn"));
        
        // font
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        txMaT.setFont(f);
        txLoaiT.setFont(f);
        txTenT.setFont(f);
        txDonGia.setFont(f);
        txSoLuong.setFont(f);
       
        txtD.setFont(f);
        // add to panel
        plTextField.add(txMaT);
        plTextField.add(txLoaiT);
        plTextField.add(txTenT);
        plTextField.add(txDonGia);
        plTextField.add(txSoLuong);
        plTextField.add(txtD);

        plImage.add(plTextField);

        //=========== add all to this jpanel ===========
        this.add(plHeader, BorderLayout.NORTH);
        this.add(mtb, BorderLayout.SOUTH);
        this.add(plImage, BorderLayout.CENTER);
    }

    public void showInfo(String mat) { // copy from BanHangForm
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
                    String doan = qldoanBUS.getDoan(t.getMaDoan()).getTenDoan();
                    txMaT.setText(t.getMaT());
                    txTenT.setText(t.getTenT());
                    txLoaiT.setText(loai + " - " + t.getMaLT());
                    txtD.setText(doan + " - " + t.getMaDoan());
                    txDonGia.setText(PriceFormatter.format(t.getDonGia()));
                    txSoLuong.setText(String.valueOf(t.getSoLuong()));
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

    public void refresh() {
        qltBUS.readDB();
        setDataToTable(qltBUS.getDst(), mtb);
        txTim.setText("");
        txSoLuong1.setText("");
        txSoLuong2.setText("");
        txGia1.setText("");
        txGia2.setText("");
        lbImage.setIcon(null);
    }

    private void txSearchOnChange() {
        int soluong1 = -1, soluong2 = -1;
        float gia1 = -1, gia2 = -1;

        try {
            soluong1 = Integer.parseInt(txSoLuong1.getText());
            txSoLuong1.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txSoLuong1.setForeground(Color.red);
        }
        try {
            soluong2 = Integer.parseInt(txSoLuong2.getText());
            txSoLuong2.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txSoLuong2.setForeground(Color.red);
        }
        try {
            gia1 = Float.parseFloat(txGia1.getText());
            txGia1.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txGia1.setForeground(Color.red);
        }
        try {
            gia2 = Float.parseFloat(txGia2.getText());
            txGia2.setForeground(Color.black);
        } catch (NumberFormatException e) {
            txGia2.setForeground(Color.red);
        }

        setDataToTable(qltBUS.search(txTim.getText(),
                cbTypeSearch.getSelectedItem().toString(), soluong1, soluong2, gia1, gia2, -1), mtb);
    }

    private void setDataToTable(ArrayList<Tour> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        Boolean hienTourAn = LoginForm.quyenLogin.getChiTietQuyen().contains("qlTour");
        for (Tour t : data) {
            if (hienTourAn || t.getTrangThai() == 0) {
                table.addRow(new String[]{
                    String.valueOf(stt),
                    t.getMaT(),
                    t.getMaLT(),
                    t.getTenT(),
                    PriceFormatter.format(t.getDonGia()),
                    String.valueOf(t.getSoLuong()),
                    t.getFileNameHinhAnh(),
                    (t.getTrangThai() == 0 ? "Hiện" : "Ẩn"),
                    t.getMaDoan()
                });
                stt++;
            }
        }
    }
}

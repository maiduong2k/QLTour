package giaodienchuan.model.FrontEnd.FormThemSua;

import giaodienchuan.model.BackEnd.QuanLyTour.QuanLyTourBUS;
import giaodienchuan.model.BackEnd.QuanLyTour.Tour;
import giaodienchuan.model.FrontEnd.FormChon.ChonLoaiTourForm;
import giaodienchuan.model.FrontEnd.FormChon.ChonDoanForm;
import giaodienchuan.model.FrontEnd.MyButton.FileButton;
import giaodienchuan.model.FrontEnd.MyButton.MoreButton;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThemSuaTourForm extends JFrame {

    String type;
    QuanLyTourBUS qltBUS = new QuanLyTourBUS();
    Tour tSua;

    JTextField txMat = new JTextField(15);
    JTextField txMalt = new JTextField(15);
    JTextField txTen = new JTextField(15);
    JTextField txGia = new JTextField(15);
    JTextField txSoLuong = new JTextField(15);
    JTextField txHinhAnh = new JTextField(15);
    JTextField txMatg = new JTextField(15);
    JTextField txMadoan = new JTextField(15);

    FileButton btnChonAnh = new FileButton();
    MoreButton btnChonLoai = new MoreButton();
    MoreButton btnChonTG = new MoreButton();
    MoreButton btnChonDoan = new MoreButton();
    JComboBox<String> cbChonTrangThai;

    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

    public ThemSuaTourForm(String _type, String _mat) {
        this.setLayout(new BorderLayout());
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.type = _type;

        // inputs
        txMat.setBorder(BorderFactory.createTitledBorder("Mã tour"));
        txMalt.setBorder(BorderFactory.createTitledBorder("Mã loại tour "));
        txTen.setBorder(BorderFactory.createTitledBorder("Tên"));
        txGia.setBorder(BorderFactory.createTitledBorder("Đơn Giá (nghìn)"));
        txSoLuong.setBorder(BorderFactory.createTitledBorder("Số lượng"));
        txHinhAnh.setBorder(BorderFactory.createTitledBorder("Hình ảnh"));
        cbChonTrangThai = new JComboBox<>(new String[]{"Ẩn", "Hiện"});
        txMadoan.setBorder(BorderFactory.createTitledBorder("Mã đoàn "));

        JPanel plChonLoai = new JPanel();
        plChonLoai.setBorder(BorderFactory.createTitledBorder("   Mã loại, Mã đoàn"));
        plChonLoai.add(txMalt);
        plChonLoai.add(btnChonLoai);
        
        JPanel plChonDoan = new JPanel();
        plChonLoai.add(txMadoan);
        plChonLoai.add(btnChonDoan);

        JPanel plChonAnh = new JPanel();
        plChonAnh.setBorder(BorderFactory.createTitledBorder("Tên file ảnh"));
        plChonAnh.add(txHinhAnh);
        plChonAnh.add(btnChonAnh);

        // chon trang thai
        JPanel plChonTT = new JPanel();
        plChonTT.setBorder(BorderFactory.createTitledBorder("Trạng thái"));
        JLabel lbChonTT = new JLabel("Trạng thái: ");
        plChonTT.add(lbChonTT);
        plChonTT.add(cbChonTrangThai);

        JPanel plInput = new JPanel();
        plInput.add(txMat);
        plInput.add(plChonLoai);
        plInput.add(txTen);
        plInput.add(txGia);
        plInput.add(txSoLuong);
        plInput.add(plChonAnh);
        plInput.add(plChonTT);
        plInput.add(plChonDoan);

        // panel buttons
        JPanel plButton = new JPanel();

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm tour");
            txMat.setText(qltBUS.getNextID());

            cbChonTrangThai.setSelectedItem("Hiện");

            btnThem.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa tour");
            for (Tour t : qltBUS.getDst()) {
                if (t.getMaT().equals(_mat)) {
                    this.tSua = t;
                }
            }
            if (this.tSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy tour");
                this.dispose();
            }

            cbChonTrangThai.setSelectedItem(this.tSua.getTrangThai() == 0 ? "Hiện" : "Ẩn");
            txMat.setText(this.tSua.getMaT());
            txMalt.setText(this.tSua.getMaLT());
            txTen.setText(this.tSua.getTenT());
            txGia.setText(String.valueOf(this.tSua.getDonGia()));
            txSoLuong.setText(String.valueOf(this.tSua.getSoLuong()));
            txHinhAnh.setText(this.tSua.getFileNameHinhAnh());
            txMadoan.setText(this.tSua.getMaDoan());

            txMat.setEditable(false);

            btnSua.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_support_30px.png")));
            plButton.add(btnSua);
        }

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_cancel_30px_1.png")));
        plButton.add(btnHuy);

        this.add(plInput, BorderLayout.CENTER);
        this.add(plButton, BorderLayout.SOUTH);

        // mouse listener
        btnThem.addActionListener((ae) -> {
            btnThemMouseClicked();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaMouseClicked();
        });
        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        btnChonLoai.addActionListener((ActionEvent ae) -> {
            btnChonLoaiMouseClicked();
        });
        btnChonDoan.addActionListener((ActionEvent ae) -> {
            btnChonDoanMouseClicked();
        });
        btnChonAnh.addActionListener((ae) -> {
            btnChonAnhMouseClicked();
        });

        this.setVisible(true);
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            String mat = txMat.getText();
            String maloai = txMalt.getText();
            String ten = txTen.getText();
            float dongia = Float.parseFloat(txGia.getText());
            int soluong = Integer.parseInt(txSoLuong.getText());
            String url = txHinhAnh.getText();
            int trangthai = (cbChonTrangThai.getSelectedItem().toString().equals("Hiện") ? 0 : 1);
            String madoan = txMadoan.getText();

            if (qltBUS.add(mat, maloai, ten, dongia, soluong, url, trangthai, madoan)) {
                JOptionPane.showMessageDialog(this, "Thêm " + ten + " thành công!");
            }
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String mat = txMat.getText();
            String maloai = txMalt.getText();
            String ten = txTen.getText();
            float dongia = Float.parseFloat(txGia.getText());
            int soluong = Integer.parseInt(txSoLuong.getText());
            String url = txHinhAnh.getText();
            int trangthai = (cbChonTrangThai.getSelectedItem().toString().equals("Hiện") ? 0 : 1);
            String matg = txMatg.getText();
            String madoan = txMadoan.getText();

            if (qltBUS.update(mat, maloai, ten, dongia, soluong, url, trangthai, madoan)) {
                JOptionPane.showMessageDialog(this, "Sửa " + mat + " thành công!");
                this.dispose();
            }
        }
    }

    private void btnChonLoaiMouseClicked() {
        ChonLoaiTourForm clt = new ChonLoaiTourForm(txMalt); // truyền vào textfield
    }
    
    
    private void btnChonDoanMouseClicked() {
        ChonDoanForm cdoan = new ChonDoanForm(txMadoan); // truyền vào textfield
    }

    private void btnChonAnhMouseClicked() {
        FileDialog fd = new FileDialog(this);
        fd.setVisible(true);
        String filename = fd.getFile();
        if (filename != null) {
            txHinhAnh.setText(filename);
        }
    }

    private Boolean checkEmpty() {
        String mst = txMat.getText();
        String maloai = txMalt.getText();
        String ten = txTen.getText();
        String dongia = txGia.getText();
        String soluong = txSoLuong.getText();
        String url = txHinhAnh.getText();
        String matg = txMatg.getText();
        String madoan = txMadoan.getText();

        if (mst.trim().equals("")) {
            return showErrorTx(txMat, "Mã tour không được để trống");

        } else if (maloai.trim().equals("")) {
            return showErrorTx(txMalt, "Mã loại không được để trống");

        } else if (ten.trim().equals("")) {
            return showErrorTx(txTen, "Tên không được để trống");

        } else if (dongia.trim().equals("")) {
            return showErrorTx(txGia, "Đơn giá không được để trống");

        } else if (soluong.trim().equals("")) {
            return showErrorTx(txSoLuong, "Số lượng không được để trống");

        } else if (url.trim().equals("")) {
            return showErrorTx(txHinhAnh, "Đường dẫn ảnh không được để trống");
            
            
        } else if (madoan.trim().equals("")) {
            return showErrorTx(txMadoan, "Mã đoàn không được để trống");

        } else {
            try {
                float dg = Float.parseFloat(dongia);
            } catch (NumberFormatException e) {
                return showErrorTx(txGia, "Đơn giá không hợp lệ (phải là số thực)");
            }

            try {
                int sl = Integer.parseInt(soluong);
                if (sl < 0) {
                    return showErrorTx(txSoLuong, "Số lượng không hợp lệ (phải là số duơng)");
                }
            } catch (NumberFormatException e) {
                return showErrorTx(txSoLuong, "Số lượng không hợp lệ (phải là số nguyên)");
            }
        }
        return true;
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }
}

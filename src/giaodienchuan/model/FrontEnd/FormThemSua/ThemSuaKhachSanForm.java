package giaodienchuan.model.FrontEnd.FormThemSua;

import giaodienchuan.model.BackEnd.QuanLyKSan.KhachSan;
import giaodienchuan.model.BackEnd.QuanLyKSan.QuanLyKhachSanBUS;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThemSuaKhachSanForm extends JFrame {

    QuanLyKhachSanBUS qlksBUS = new QuanLyKhachSanBUS();
    String type;

    KhachSan ksSua;
    JTextField txMaKS = new JTextField(10);
    JTextField txTenKS = new JTextField(35);
    JTextField txDiaChi = new JTextField(35);
    JTextField txSDT = new JTextField(15);
    JTextField txFax = new JTextField(15);
   // JTextField txTim = new JTextField(15);

    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

    public ThemSuaKhachSanForm(String _type, String _maks) {

        this.setLayout(new BorderLayout());
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.type = _type;

        txMaKS.setBorder(BorderFactory.createTitledBorder("Mã khách sạn"));
        txTenKS.setBorder(BorderFactory.createTitledBorder("Tên khách sạn"));
        txDiaChi.setBorder(BorderFactory.createTitledBorder("Địa chỉ"));
        txSDT.setBorder(BorderFactory.createTitledBorder("SDT"));
        txFax.setBorder(BorderFactory.createTitledBorder("Fax"));

        JPanel plInput = new JPanel();
        plInput.add(txMaKS);
        plInput.add(txTenKS);
        plInput.add(txDiaChi);
        plInput.add(txSDT);
        plInput.add(txFax);

        JPanel plButton = new JPanel();
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm khách sạn");
            txMaKS.setText(qlksBUS.getNextID());

            btnThem.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa khách sạn");
            for (KhachSan ks : qlksBUS.getDsks()) {
                if (ks.getMaKS().equals(_maks)) {
                    this.ksSua = ks;
                }
            }
            if (this.ksSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy khách sạn");
                this.dispose();
            }

            txMaKS.setText(this.ksSua.getMaKS());
            txTenKS.setText(this.ksSua.getTenKS());
            txDiaChi.setText(this.ksSua.getDiaChi());
            txSDT.setText(String.valueOf(this.ksSua.getSDT()));
            txFax.setText(String.valueOf(this.ksSua.getFax()));

            txMaKS.setEditable(false);

            btnSua.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_support_30px.png")));
            plButton.add(btnSua);
        }
        
        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_cancel_30px_1.png")));
        plButton.add(btnHuy);

        this.add(plInput, BorderLayout.CENTER);
        this.add(plButton, BorderLayout.SOUTH);

        btnThem.addActionListener((ae) -> {
            btnThemMouseClicked();
        });
        btnSua.addActionListener((ae) -> {
            btnSuaMouseClicked();
        });
        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        this.setVisible(true);
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String maKS = txMaKS.getText();
            String tenKS = txTenKS.getText();
            String diaChi = txDiaChi.getText();
            String SDT = txSDT.getText();
            String Fax = txFax.getText();

            if (qlksBUS.update(maKS, tenKS, diaChi, SDT, Fax)) {
                JOptionPane.showMessageDialog(this, "Sửa " + maKS + " thành công!");
                this.dispose();
            }
        }
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            KhachSan ks = new KhachSan(txMaKS.getText(), txTenKS.getText(), txDiaChi.getText(), txSDT.getText(), txFax.getText());
            if (qlksBUS.add(ks)) {
                JOptionPane.showMessageDialog(this, "Thêm " + txTenKS.getText() + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String ma = txMaKS.getText();
        String ten = txTenKS.getText();
        String diachi = txDiaChi.getText();
        String sdt = txSDT.getText();
        String fax = txFax.getText();
        if (ma.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Mã khách sạn không được để trống");
            txMaKS.requestFocus();
            return false;
        } else if (ten.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Tên khách sạn không được để trống");
            txTenKS.requestFocus();
            return false;
        } else if (diachi.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Địa chỉ khách sạn không được để trống");
            txDiaChi.requestFocus();
            return false;
        } else if (sdt.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách sạn không được để trống");
            txSDT.requestFocus();
            return false;
        } else if (fax.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Số fax khách sạn không được để trống");
            txFax.requestFocus();
            return false;
        }
        return true;
    }
}

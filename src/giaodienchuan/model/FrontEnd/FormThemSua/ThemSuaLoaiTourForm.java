package giaodienchuan.model.FrontEnd.FormThemSua;

import giaodienchuan.model.BackEnd.QuanLyLoaiTour.LoaiTour;
import giaodienchuan.model.BackEnd.QuanLyLoaiTour.QuanLyLoaiTourBUS;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ThemSuaLoaiTourForm extends JFrame {

    String type;
    QuanLyLoaiTourBUS qlltBUS = new QuanLyLoaiTourBUS();
    LoaiTour ltSua;

    JTextField txMalt = new JTextField(15);
    JTextField txTenlt = new JTextField(15);
    JTextArea txMota = new JTextArea(2, 30);

    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

    public ThemSuaLoaiTourForm(String _type, String _malt) {
        this.setLayout(new BorderLayout());
        this.setSize(450, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.type = _type;

        // inputs
        txMalt.setBorder(BorderFactory.createTitledBorder("Mã loại"));
        txTenlt.setBorder(BorderFactory.createTitledBorder("Tên loại"));
        txMota.setBorder(BorderFactory.createTitledBorder("Mô tả"));

        JPanel plInput = new JPanel();
        plInput.add(txMalt);
        plInput.add(txTenlt);
        plInput.add(txMota);

        // panel buttons
        JPanel plButton = new JPanel();

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm loại");
            txMalt.setText(qlltBUS.getNextID());

            btnThem.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa loại tour");
            for (LoaiTour lt : qlltBUS.getDslt()) {
                if (lt.getMaLT().equals(_malt)) {
                    this.ltSua = lt;
                }
            }
            if (this.ltSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy thể loại");
                this.dispose();
            }

            txMalt.setText(this.ltSua.getMaLT());
            txTenlt.setText(this.ltSua.getTenLT());
            txMota.setText(this.ltSua.getMoTa());

            txMalt.setEditable(false);

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

        this.setVisible(true);
    }

    private void btnThemMouseClicked() {
        if (checkEmpty()) {
            String malt = txMalt.getText();
            String tenlt = txTenlt.getText();
            String mota = txMota.getText();

            if (qlltBUS.add(malt, tenlt, mota)) {
                JOptionPane.showMessageDialog(this, "Thêm " + tenlt + " thành công!");
            }
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String malt = txMalt.getText();
            String tenlt = txTenlt.getText();
            String mota = txMota.getText();

            if (qlltBUS.update(malt, tenlt, mota)) {
                JOptionPane.showMessageDialog(this, "Sửa " + malt + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String malt = txMalt.getText();
        String tenlt = txTenlt.getText();
        String mota = txMota.getText();

        if (malt.trim().equals("")) {
            return showErrorTx(txMalt, "Mã loại tour không được để trống");

        } else if (tenlt.trim().equals("")) {
            return showErrorTx(txTenlt, "Tên loại không được để trống");

        } else if (mota.trim().equals("")) {
            return showErrorTx(txMota, "Mô tả không được để trống");
        }

        return true;
    }

    private Boolean showErrorTx(JTextField tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }

    private Boolean showErrorTx(JTextArea tx, String errorInfo) {
        JOptionPane.showMessageDialog(tx, errorInfo);
        tx.requestFocus();
        return false;
    }
}

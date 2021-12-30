package giaodienchuan.model.FrontEnd.FormThemSua;

import giaodienchuan.model.BackEnd.QuanLyDoan.Doan;
import giaodienchuan.model.BackEnd.QuanLyDoan.QuanLyDoanBUS;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ThemSuaDoanForm extends JFrame {

    String type;
    QuanLyDoanBUS qldoanBUS = new QuanLyDoanBUS();
    Doan doanSua;

    JTextField txMadoan = new JTextField(15);
    JTextField txTendoan = new JTextField(15);
    JTextArea txDiachi = new JTextArea(2, 30);

    JButton btnThem = new JButton("Thêm");
    JButton btnSua = new JButton("Sửa");
    JButton btnHuy = new JButton("Hủy");

    public ThemSuaDoanForm(String _type, String _madoan) {
        this.setLayout(new BorderLayout());
        this.setSize(450, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.type = _type;

        // inputs
        txMadoan.setBorder(BorderFactory.createTitledBorder("Mã đoàn"));
        txTendoan.setBorder(BorderFactory.createTitledBorder("Tên đoàn"));
        txDiachi.setBorder(BorderFactory.createTitledBorder("Địa chỉ"));

        JPanel plInput = new JPanel();
        plInput.add(txMadoan);
        plInput.add(txTendoan);
        plInput.add(txDiachi);

        // panel buttons
        JPanel plButton = new JPanel();

        // 2 case Thêm - Sửa
        if (this.type.equals("Thêm")) {
            this.setTitle("Thêm đoàn");
            txMadoan.setText(qldoanBUS.getNextID());

            btnThem.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8_add_30px.png")));
            plButton.add(btnThem);

        } else {
            this.setTitle("Sửa đoàn");
            for (Doan doan : qldoanBUS.getDsdoan()) {
                if (doan.getMaDoan().equals(_madoan)) {
                    this.doanSua = doan;
                }
            }
            if (this.doanSua == null) {
                JOptionPane.showMessageDialog(null, "Lỗi, không tìm thấy đoàn");
                this.dispose();
            }

            txMadoan.setText(this.doanSua.getMaDoan());
            txTendoan.setText(this.doanSua.getTenDoan());
            txDiachi.setText(this.doanSua.getDiaChi());

            txMadoan.setEditable(false);

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
            String madoan = txMadoan.getText();
            String tendoan = txTendoan.getText();
            String diachi = txDiachi.getText();

            if (qldoanBUS.add(madoan, tendoan, diachi)) {
                JOptionPane.showMessageDialog(this, "Thêm " + tendoan + " thành công!");
            }
        }
    }

    private void btnSuaMouseClicked() {
        if (checkEmpty()) {
            String madoan = txMadoan.getText();
            String tendoan = txTendoan.getText();
            String diachi = txDiachi.getText();

            if (qldoanBUS.update(madoan, tendoan, diachi)) {
                JOptionPane.showMessageDialog(this, "Sửa " + madoan + " thành công!");
                this.dispose();
            }
        }
    }

    private Boolean checkEmpty() {
        String madoan = txMadoan.getText();
        String tendoan = txTendoan.getText();
        String diachi = txDiachi.getText();

        if (madoan.trim().equals("")) {
            return showErrorTx(txMadoan, "Mã đoàn không được để trống");

        } else if (tendoan.trim().equals("")) {
            return showErrorTx(txTendoan, "Tên đoàn không được để trống");

        } else if (diachi.trim().equals("")) {
            return showErrorTx(txDiachi, "Địa chỉ không được để trống");
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

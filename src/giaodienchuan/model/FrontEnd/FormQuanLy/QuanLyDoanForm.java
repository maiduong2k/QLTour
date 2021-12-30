package giaodienchuan.model.FrontEnd.FormQuanLy;

import giaodienchuan.model.BackEnd.QuanLyDoan.QuanLyDoanBUS;
import giaodienchuan.model.BackEnd.WorkWithExcel.DocExcel;
import giaodienchuan.model.BackEnd.WorkWithExcel.XuatExcel;
import giaodienchuan.model.FrontEnd.FormHienThi.HienThiDoan;
import giaodienchuan.model.FrontEnd.FormThemSua.ThemSuaDoanForm;
import giaodienchuan.model.FrontEnd.GiaoDienChuan.LoginForm;
import giaodienchuan.model.FrontEnd.MyButton.ExportExcelButton;
import giaodienchuan.model.FrontEnd.MyButton.ImportExcelButton;
import giaodienchuan.model.FrontEnd.MyButton.SuaButton;
import giaodienchuan.model.FrontEnd.MyButton.ThemButton;
import giaodienchuan.model.FrontEnd.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QuanLyDoanForm extends JPanel {

    HienThiDoan formHienThi = new HienThiDoan();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    JComboBox<String> cbTypeSearch;

    // index
    final int MANXB_I = 1, TENNXB_I = 2, DIACHI_I = 3;

    public QuanLyDoanForm() {
        setLayout(new BorderLayout());

        // buttons        
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlĐoan")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
            btnNhapExcel.setEnabled(false);
        }

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);
        plBtn.add(btnXuatExcel);
        plBtn.add(btnNhapExcel);

        //=========== add all to this jpanel ===========
        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtn, BorderLayout.NORTH);

        // actionlistener
        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
        });
        btnXuatExcel.addActionListener((ActionEvent ae) -> {
            new XuatExcel().xuatFileExcelLoaiTour();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelLoaiTour();
        });
    }

    private void btnSuaMouseClicked() {
        String madoan = formHienThi.getSelectedRow(1);
        if (madoan != null) {
            ThemSuaDoanForm suadoan = new ThemSuaDoanForm("Sửa", madoan);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suadoan.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn đoàn nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String madoan = formHienThi.getSelectedRow(1);
        if (madoan != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa đoàn " + madoan + " ?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new QuanLyDoanBUS().delete(madoan);
                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn đoàn nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaDoanForm themdoan = new ThemSuaDoanForm("Thêm", "");

        themdoan.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}

package giaodienchuan.model.FrontEnd.FormQuanLy;

import giaodienchuan.model.BackEnd.QuanLyTour.QuanLyTourBUS;
import giaodienchuan.model.BackEnd.WorkWithExcel.DocExcel;
import giaodienchuan.model.BackEnd.WorkWithExcel.XuatExcel;
import giaodienchuan.model.FrontEnd.FormHienThi.HienThiTour;
import giaodienchuan.model.FrontEnd.FormThemSua.ThemSuaTourForm;
import giaodienchuan.model.FrontEnd.GiaoDienChuan.LoginForm;
import giaodienchuan.model.FrontEnd.MyButton.ExportExcelButton;
import giaodienchuan.model.FrontEnd.MyButton.ImportExcelButton;
import giaodienchuan.model.FrontEnd.MyButton.SuaButton;
import giaodienchuan.model.FrontEnd.MyButton.ThemButton;
import giaodienchuan.model.FrontEnd.MyButton.XoaButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QuanLyTourForm extends JPanel {

    HienThiTour formHienThi = new HienThiTour();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    
    ExportExcelButton btnXuatExcel = new ExportExcelButton();
    ImportExcelButton btnNhapExcel = new ImportExcelButton();

    public QuanLyTourForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlTour")) {
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
            new XuatExcel().xuatFileExcelTour();
        });
        btnNhapExcel.addActionListener((ActionEvent ae) -> {
            new DocExcel().docFileExcelTour();
        });
    }

    private void btnSuaMouseClicked() {
        String masp = formHienThi.getSelectedRow(1);
        if (masp != null) {
            ThemSuaTourForm suasp = new ThemSuaTourForm("S???a", masp);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suasp.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Ch??a ch???n s???n ph???m n??o ????? s???a");
        }
    }

    private void btnXoaMouseClicked() {
        String masp = formHienThi.getSelectedRow(1);
        if (masp != null) {
            QuanLyTourBUS qlspBUS = new QuanLyTourBUS();
            if (qlspBUS.getTour(masp).getTrangThai() == 0) {
                if (JOptionPane.showConfirmDialog(null, "B???n c?? ch???c mu???n x??a s???n ph???m " + masp + " ? "
                        + "S???n ph???m s??? ???????c T???M ???N", "Ch?? ??", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlspBUS.updateTrangThai(masp, 1);
                    formHienThi.refresh();
                }
            } else {
                if (JOptionPane.showConfirmDialog(null, "B???n c?? ch???c mu???n X??A HO??N TO??N s???n ph???m " + masp + " ?", 
                        "Ch?? ??", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlspBUS.delete(masp);
                    formHienThi.refresh();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ch??a ch???n s???n ph???m n??o ????? x??a");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaTourForm themsp = new ThemSuaTourForm("Th??m", "");
        themsp.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}

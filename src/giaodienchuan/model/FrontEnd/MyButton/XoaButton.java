/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodienchuan.model.FrontEnd.MyButton;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author DELL
 */
public class XoaButton extends JButton {
    public XoaButton() {
        this.setText("Xóa");
        this.setIcon(new ImageIcon(this.getClass().getResource("/giaodienchuan/images/icons8-delete-bin-30.png")));
        this.setBackground(new Color(255,0,0));
        this.setForeground(Color.red);
    }
}

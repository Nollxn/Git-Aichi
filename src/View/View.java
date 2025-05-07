package View;

import Controler.Controler;

import javax.swing.*;

public class View extends JFrame{
    private JPanel panel1;
    private JTextField fieldIdentifiant;
    private JButton formSend;
    private JTextField fieldPassword;

    public View() {
        //Configuration de la fenetre
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        new Controler(this);
        // Centrer la fenetre
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public String getFieldPassword() {
        return fieldPassword.getText();
    }

    public String getFieldIdentifiant() {
        return fieldIdentifiant.getText();
    }

    public JButton getFormSend() {
        return formSend;
    }
}

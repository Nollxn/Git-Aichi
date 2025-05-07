package View;

import Controler.AccountAddController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountAddView extends JFrame{
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JTextField fieldIdentifiant;
    private JButton formSend;
    private JComboBox privilegeComboBox;

    public AccountAddView() {
        //Configuration de la fenetre
        this.setTitle("Création de compte");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 500);
        // Centrer la fenetre
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        new AccountAddController(this);
    }

    public JButton getFormSend() {
        return formSend;
    }

    public String getIdentifiant(){
        return fieldIdentifiant.getText();
    }

    public String getPassword(){
        return new String(passwordField1.getPassword());
    }

    public String getPrivilege(){
        return privilegeComboBox.getSelectedItem().toString();
    }
}

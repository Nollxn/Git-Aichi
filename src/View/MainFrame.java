package View;

import Controler.NavBarController;
import Model.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel navBarPanel;
    private JPanel contentPanel;
    private JButton accountsTablePageButton;
    private JButton clientsTablePageButton;
    private JButton articlesTablePageButton;
    private JButton commandesTablePageButton;
    private JButton seDeconnecterButton;
    private JButton paramètresButton;
    private CardLayout cardLayout;

    public MainFrame() throws SQLException {
        this.setTitle("Administration");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        new NavBarController(this);

        if(!UserDAO.est_superadmin){
            navBarPanel.remove(accountsTablePageButton);
        }
        // Centrer la fenetre
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void showView(String viewName) {
        cardLayout.show(contentPanel, viewName);
        System.out.println("Showing view: " + viewName);
    }



    public JButton getAccountsTablePageButton() {
        return accountsTablePageButton;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JButton getClientsTablePageButton() {
        return clientsTablePageButton;
    }

    public JButton getArticlesTablePageButton() {
        return articlesTablePageButton;
    }

    public JButton getCommandesTablePageButton() {
        return commandesTablePageButton;
    }

    public JButton getSeDeconnecterButton() {
        return seDeconnecterButton;
    }
}
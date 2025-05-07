package Controler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import Model.UserDAO;
import View.*;

public class NavBarController {
    private MainFrame mainFrame;

    public NavBarController(MainFrame mainFrame) throws SQLException {
        this.mainFrame = mainFrame;

        // Gestion des clics sur la navbar
        if(UserDAO.est_superadmin){
            mainFrame.getContentPanel().add(new AccountsTableView(), "AccountsTablePage");
            mainFrame.getAccountsTablePageButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPage("AccountsTablePage");
                }
            });
        }
        if (UserDAO.est_admin){
            mainFrame.getContentPanel().add(new ArticlesTableView(), "ArticlesTablePage");
            mainFrame.getArticlesTablePageButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPage("ArticlesTablePage");
                }
            });

            mainFrame.getContentPanel().add(new ClientsTableView(), "ClientsTablePage");
            mainFrame.getClientsTablePageButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPage("ClientsTablePage");
                }
            });

            //Do the same for CommandesTableView
            mainFrame.getContentPanel().add(new CommandesTableView(this), "CommandesTablePage");
            mainFrame.getCommandesTablePageButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showPage("CommandesTablePage");
                }
            });


        }

        // "Se déconnecter" qui ferme la fenêtre principale et ouvre la fenêtre de connexion
        mainFrame.getSeDeconnecterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                UserDAO.resetSession();
                new View();
            }
        });
    }

    public void showPage(String pageName) {
        CardLayout layout = (CardLayout) mainFrame.getContentPanel().getLayout();
        layout.show(mainFrame.getContentPanel(), pageName);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}

package Controler;

import Model.CommandeDAO;
import Model.ConnectionDAO;
import View.CommandeAddView;
import View.CommandesTableView;
import View.CommandeDetailViewV2;

import javax.swing.*;
import java.sql.SQLException;

public class CommandesTableController {
    private CommandeDAO commandeDAO = new CommandeDAO();
    private CommandesTableView commandesTableView;
    private CommandeDetailViewV2 commandeDetailViewV2;
    private ConnectionDAO connectionDAO = new ConnectionDAO();

    public CommandesTableController(CommandesTableView commandesTableView, NavBarController navBarController) {
        this.commandesTableView = commandesTableView;

        // Mettre à jour la table
        commandesTableView.getUpdateButton().addActionListener(e -> {
            commandesTableView.updateTable();
        });

        //Ouvrir le détail d'une commande
        commandesTableView.getOuvrirCommande().addActionListener(e -> {
            int selectedRow = commandesTableView.getTable().getSelectedRow();
            if (selectedRow != -1) {
                int commandeId = (int) commandesTableView.getTable().getValueAt(selectedRow, 0);
                // Afficher une nouvelle vue avec les détails de la commande
                navBarController.getMainFrame().getContentPanel().add(new CommandeDetailViewV2(commandeId), "CommandeDetailViewV2");
                navBarController.showPage("CommandeDetailViewV2");
            }
            else{
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une commande à ouvrir");
            }
        });

        //Mettre a jour la table
        commandesTableView.getUpdateButton().addActionListener(e -> {
            commandesTableView.updateTable();
        });

        //Ajouter une commande
        commandesTableView.getAjouterCommande().addActionListener(e -> {
            try {
                new CommandeAddView();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });



    }

}

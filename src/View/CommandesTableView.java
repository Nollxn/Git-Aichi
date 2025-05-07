package View;

import Controler.CommandesTableController;
import Controler.NavBarController;
import Model.CommandeDAO;
import Model.ConnectionDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import View.CommandeDetailViewV2;

public class CommandesTableView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton ajouterCommandeButton = new JButton("Ajouter Commande");
    private JButton ouvrirCommande = new JButton("Ouvrir Commande");
    private ConnectionDAO connection = new ConnectionDAO();
    private JButton updateButton = new JButton("Mettre à jour");
    private JPanel actionPanel = new JPanel();


    public CommandesTableView(NavBarController navBarController) {
        CommandeDAO commandeDAO = new CommandeDAO();
        CommandesTableController controller = new CommandesTableController(this, navBarController);

        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Date", "Client"};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        actionPanel.setLayout(new BorderLayout());
        actionPanel.add(updateButton, BorderLayout.WEST);
        actionPanel.add(ouvrirCommande);
        actionPanel.add(ajouterCommandeButton, BorderLayout.EAST);
        add(actionPanel, BorderLayout.NORTH);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        commandeDAO.getCommandes(connection.getConnection()).forEach(commande -> {
            Object[] row = {commande.getId(), commande.getDate(), commande.getClient().getClient_nom() + " " + commande.getClient().getClient_prenom()};
            tableModel.addRow(row);
        });




    }

    public void updateTable() {
        tableModel.setRowCount(0);
        CommandeDAO commandeDAO = new CommandeDAO();
        commandeDAO.getCommandes(connection.getConnection()).forEach(commande -> {
            Object[] row = {commande.getId(), commande.getDate(), commande.getClient().getClient_nom() + " " + commande.getClient().getClient_prenom()};
            tableModel.addRow(row);
        });
    }

    public JButton getUpdateButton() {
        return updateButton;
    }
    public JButton getOuvrirCommande(){return ouvrirCommande;}
    public JTable getTable() {return table;}

    public JButton getAjouterCommande() {
        return ajouterCommandeButton;
    }


}
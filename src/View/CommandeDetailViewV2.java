package View;

import Controler.CommandeDetailController;
import Model.Commande;
import Model.CommandeDAO;
import Model.ConnectionDAO;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandeDetailViewV2 extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel infoPanel;
    private JPanel actionPanel;
    private JLabel id_commande;
    private JLabel date_commande;
    private JLabel client;
    private JButton ajouterArticleButton= new JButton("Ajouter Article");
    private JButton supprimerArticleButton = new JButton("Supprimer Article");
    private JButton supprimerCommandeButton;
    private int commandeId;
    private JButton updateButton = new JButton("Mettre à jour");

    public CommandeDetailViewV2(int commandeId) {
        CommandeDAO commandeDAO = new CommandeDAO();
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Commande commande = commandeDAO.getCommandeById(commandeId, connectionDAO.getConnection());
        this.commandeId = commandeId;
        this.supprimerCommandeButton= new JButton("Supprimer Commande");

        //Controlleur
        new CommandeDetailController(commandeId, this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        actionPanel.setLayout(new BorderLayout());
        actionPanel.add(supprimerCommandeButton,BorderLayout.NORTH);
        actionPanel.add(ajouterArticleButton, BorderLayout.CENTER);
        actionPanel.add(supprimerArticleButton, BorderLayout.EAST);
        actionPanel.add(updateButton, BorderLayout.WEST);

        id_commande = new JLabel("ID: " + commande.getId());
        date_commande = new JLabel("Date: " + commande.getDate());
        client = new JLabel("Client: " + commande.getClient().getClient_nom() + " " + commande.getClient().getClient_prenom());
        infoPanel.setLayout(new BorderLayout());
        infoPanel.add(id_commande, BorderLayout.CENTER);
        infoPanel.add(date_commande, BorderLayout.WEST);
        infoPanel.add(client, BorderLayout.EAST);
        add(infoPanel);
        add(actionPanel);

        String[] columnNames = {"ID", "Nom", "Quantité", "Description"};
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        commandeDAO.getArticles(commande,connectionDAO.getConnection()).forEach(list-> {
            list.forEach(article -> {
                Object[] row = {article.get_ID(), article.getNom_article(), article.getQuantite_article(), article.getDescription_article()};
                tableModel.addRow(row);
            });
        });
    }

    public JButton getSupprimerCommandeButton() {
        return supprimerCommandeButton;
    }

    public void dispose() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    //Retourner l'id des articles sélectionner dans la table
    public List<Integer> getSelectedArticleIds() {
        List<Integer> selectedArticleIds = new ArrayList<>();
        int[] selectedRows = table.getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {
            selectedArticleIds.add((int) table.getValueAt(selectedRows[i], 0));
        }
        return selectedArticleIds;
    }

    public JButton getSupprimerArticleButton() {
        return supprimerArticleButton;
    }

    public JButton getAjouterArticleButton() {
        return ajouterArticleButton;
    }

    public void updateTable() {
        tableModel.setRowCount(0);
        CommandeDAO commandeDAO = new CommandeDAO();
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Commande commande = commandeDAO.getCommandeById(this.commandeId, connectionDAO.getConnection());
        commandeDAO.getArticles(commande, connectionDAO.getConnection()).forEach(list -> {
            list.forEach(article -> {
                Object[] row = {article.get_ID(), article.getNom_article(), article.getQuantite_article(), article.getDescription_article()};
                tableModel.addRow(row);
            });
        });
    }

    public JButton getUpdateButton() {
        return updateButton;
    }
}


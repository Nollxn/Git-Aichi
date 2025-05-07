package View;


import Controler.ArticleTableController;
import Model.ArticleDAO;
import Model.Articles;
import Model.ConnectionDAO;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Locale;

public class ArticlesTableView extends JPanel {
    private JLabel label1;
    private JTable table;
    private JPanel articleHeader = new JPanel();
    private JTextField searchTextField= new JTextField(30);
    private static DefaultTableModel tableModel;
    private static ConnectionDAO connection = new ConnectionDAO();
    private JButton ajouterArticle = new JButton("Ajouter un article");
    private int selectedRow;
    private JButton supprimerArticle = new JButton("Supprimer l'article");
    private ArrayList<Articles> articles = new ArrayList<Articles>();

    public ArticlesTableView() throws SQLException {

        new ArticleTableController(this);
        setLayout(new BorderLayout());

        // Définir les colonnes et les données de la table
        String [] columnNames = {"ID", "Nom", "Prix(en euros)", "Quantité", "Description"};

        //Data vide
        Object[][] data = {};

        // Initialiser le modèle de table
        tableModel = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Créer la JTable avec le modèle
        searchTextField.setSize(30,5);
        ajouterArticle.setSize(30,5);
        articleHeader.add(ajouterArticle,BorderLayout.WEST);
        articleHeader.add(searchTextField, BorderLayout.EAST);
        add(articleHeader, BorderLayout.NORTH);
        table = new JTable(tableModel);



        // Ajouter la JTable dans un JScrollPane pour le défilement
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        //Affichage des articles
        try {
            articles = ArticleDAO.getArticles(connection.getConnection());
            articles.forEach(article -> {
                Object[] row = {article.get_ID(), article.getNom_article(), article.getPrix_article(), article.getQuantite_article(), article.getDescription_article()};
                tableModel.addRow(row);
            });
        }catch(SQLException e){
            e.printStackTrace();
        }


        //Ajouter la possibiliter de supprimer un article avec une icone poubell
        this.add(supprimerArticle,BorderLayout.SOUTH);

    }

    public JButton getAjouterArticle() {return ajouterArticle;}

    public JButton getSupprimerArticle() {return supprimerArticle;}

    public JTable getTable() {return table;}

    public DefaultTableModel getTableModel() {return tableModel;}

    public static void refreshTable() {
        tableModel.setRowCount(0);
        try {
            ArticleDAO.getArticles(connection.getConnection()).forEach(article -> {
                Object[] row = {article.get_ID(), article.getNom_article(), article.getPrix_article(), article.getQuantite_article(), article.getDescription_article()};
                tableModel.addRow(row);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recherche(String input) throws SQLException {
        tableModel.setRowCount(0);
        ArticleDAO.getArticles(connection.getConnection()).forEach(article -> {
            if(article.getNom_article().toLowerCase().contains(input.toLowerCase())){
                Object[] row = {article.get_ID(), article.getNom_article(), article.getPrix_article(), article.getQuantite_article(), article.getDescription_article()};
                tableModel.addRow(row);
            }
        });
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }
}

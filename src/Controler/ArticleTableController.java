package Controler;

import Model.ArticleDAO;
import Model.ConnectionDAO;
import View.ArticleAddView;
import View.ArticlesTableView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ArticleTableController {

    private ArticlesTableView view;
    private ArticleDAO articleDAO= new ArticleDAO();
    private ConnectionDAO connection = new ConnectionDAO();

    public ArticleTableController(ArticlesTableView view) {
        this.view = view;

        // Ajouter un article
        if (this.view != null) {
            this.view.getAjouterArticle().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {new ArticleAddView();}
            });
        }

        //Rechercher un article
        if (this.view != null){
            this.view.getSearchTextField().getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    try {
                        view.recherche(view.getSearchTextField().getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    try {
                        view.recherche(view.getSearchTextField().getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    // Ne pas utiliser
                }
            });
        }

        // Supprimer un article
        if (this.view != null){
            this.view.getSupprimerArticle().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = view.getTable().getSelectedRow();
                    String nom_artcle = (String) view.getTableModel().getValueAt(selectedRow, 1);
                    int id = (int) view.getTableModel().getValueAt(selectedRow, 0);

                    if (selectedRow != -1) {
                        //Demande de confirmation avant suppression de l'article
                        int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer l'article '" + nom_artcle+"'", "Confirmation", JOptionPane.YES_NO_OPTION);

                        if (response == JOptionPane.YES_OPTION) {
                            view.getTableModel().removeRow(selectedRow);
                            try {
                                articleDAO.supprimerArticle(id, connection.getConnection());
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "L' article '"+nom_artcle+ "' a été supprimé avec succès");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Suppression annulée");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez sélectionner un article à supprimer");
                    }
                }
            });
        }
    }
}

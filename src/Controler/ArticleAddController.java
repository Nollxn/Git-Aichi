package Controler;

import Model.ConnectionDAO;
import Model.Articles;
import Model.ArticleDAO;
import View.ArticleAddView;
import View.ArticlesTableView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArticleAddController {
    private ArticleAddView view;
    private ConnectionDAO connectionDAO = new ConnectionDAO();
    private ArticleDAO articleDAO = new ArticleDAO();

    public ArticleAddController(ArticleAddView view) {
        this.view = view;

        view.getAjouterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajouter l'article
                String nom_article = view.getName();
                double prix_article = view.getPrice();
                int quantite_article = view.getStock();
                String description_article = view.getDescription();


                try {
                    if(articleDAO.ajouterArticle(new Articles(nom_article, prix_article, quantite_article, description_article), connectionDAO.getConnection())){
                        JOptionPane.showMessageDialog(null, "L'article '" + nom_article +"' a été ajouté avec succès");
                        ArticlesTableView.refreshTable();
                        view.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'article '" + nom_article+"'"); // Error when adding the article

                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    System.out.println(e1);
                }
            }
        });

        view.getAnnulerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

}

package Controler;

import Model.Commande;
import Model.CommandeDAO;
import Model.ConnectionDAO;
import View.AddArticleToCommandeView;
import View.CommandeAddView;
import View.CommandeDetailViewV2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CommandeDetailController {
    private CommandeDetailViewV2 commandeDetailViewV2;
    private ConnectionDAO connectionDAO;
    private CommandeDAO commandeDAO = new CommandeDAO();
    private int commandeId;

    public CommandeDetailController(int commandeId, CommandeDetailViewV2 commandeDetailViewV2) {
        connectionDAO = new ConnectionDAO();
        this.commandeId = commandeId;

        //Supprimer la commande
        commandeDetailViewV2.getSupprimerCommandeButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionDAO.getConnection();
                int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce compte ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    if (JOptionPane.YES_OPTION == 0) {
                        return;
                    }
                    commandeDAO.supprimerCommande(commandeId, connectionDAO.getConnection());
                    commandeDetailViewV2.dispose();
                }

            }
        });

        //Enlever l'article ou les articles sélectionner de la commande
        commandeDetailViewV2.getSupprimerArticleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionDAO.getConnection();
                commandeDetailViewV2.getSelectedArticleIds().forEach(article ->{
                    commandeDAO.supprimerArticleFromCommande(commandeId, article, connectionDAO.getConnection());
                });

                commandeDetailViewV2.updateTable();
            }
        });


        //Ajouter un article à la commande
        commandeDetailViewV2.getAjouterArticleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddArticleToCommandeView(commandeId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        //Mettre a jour la table
        commandeDetailViewV2.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandeDetailViewV2.updateTable();
            }
        });



    }

    public CommandeDetailController(int commandeId,AddArticleToCommandeView view) {
        connectionDAO = new ConnectionDAO();
        this.commandeId = commandeId;

        //Ajouter un article à la commande
        view.getAjouterArticleButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionDAO.getConnection();
                int articleId = (int) view.getTableArticles().getValueAt(view.getTableArticles().getSelectedRow(), 0);

                String input = JOptionPane.showInputDialog(null, "Entrez la quantité d'article:", "Quantité", JOptionPane.QUESTION_MESSAGE);
                if (input != null && !input.isEmpty()) {
                    try {
                        int quantite = Integer.parseInt(input);
                        System.out.println("Quantité saisie: " + quantite);
                        commandeDAO.ajouterArticleToCommande(commandeId, articleId, quantite, connectionDAO.getConnection());
                    } catch (NumberFormatException event) {
                        JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        JOptionPane.showMessageDialog(null, "L'article a été ajouté a la commande avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Aucune quantité saisie.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        //Rechercher un article
        if (view != null){
            view.getSearchTextField().getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    view.recherche(view.getSearchTextField().getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    view.recherche(view.getSearchTextField().getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    // Ne pas utiliser
                }
            });
        }

        //Cancel
        view.getAnnulerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    public CommandeDetailController(CommandeAddView commandeAddView) {
        connectionDAO = new ConnectionDAO();
        new JOptionPane().showMessageDialog(null, "Veuillez choisir un client, et vous pourrez ensuite agir sur la commande", "Ajouter une commande", JOptionPane.INFORMATION_MESSAGE);

        //Rechercher un client
        commandeAddView.getSearchTextFieldClient().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    commandeAddView.rechercheClient(commandeAddView.getSearchTextFieldClient().getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    commandeAddView.rechercheClient(commandeAddView.getSearchTextFieldClient().getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Ne pas utiliser
            }
        });

        //Cancel
        commandeAddView.getAnnulerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commandeAddView.dispose();
            }
        });

        //Valider
        commandeAddView.getValiderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectionDAO.getConnection();
                int clientId = (int) commandeAddView.getTableClient().getValueAt(commandeAddView.getTableClient().getSelectedRow(), 0);
                try {
                    commandeDAO.addCommande(clientId, connectionDAO.getConnection());
                } finally {
                    JOptionPane.showMessageDialog(null, "La commande a été ajoutée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    commandeAddView.dispose();
                }
            }
        });

    }
}

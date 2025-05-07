package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {

    public CommandeDAO() {
    }

    public boolean addCommande(int clientId, Connection connection) {
        try {
            String query = "CALL addCommande(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean deleteCommande(int id, Connection connection) {
        try {
            String query = "DELETE FROM commande WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Commande> getCommandes(Connection connection) {
        List<Commande> commandes = new ArrayList<>();
        try {
            String query = "CALL get_all_commande()";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Commande commande = new Commande(resultSet.getInt("id_commande"), resultSet.getString("date_commande"), resultSet.getInt("id_client"));
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    public Commande getCommandeById(int id, Connection connection) {
        Commande commande = null;
        try {
            String query = "CALL getCommandeById(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                commande = new Commande(resultSet.getInt("id_commande"), resultSet.getString("date_commande"), resultSet.getInt("id_client"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commande;
    }

    public List<List<Articles>> getArticles(Commande commande, Connection connection) {
        List<List<Articles>> articles = new ArrayList<>();
        try {
            String query = "CALL get_articles_by_commande(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commande.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<Articles> articleList = new ArrayList<>();
                Articles article = new Articles(resultSet.getInt("id_article"), resultSet.getString("nom_article"), resultSet.getInt("quantite_article"), resultSet.getString("description_article"), resultSet.getInt("prix_article"));
                articleList.add(article);
                articles.add(articleList);
                article.toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public void addArticleToCommande(Commande commande, Articles article,int quantite, Connection connection) {
        try {
            String query = "CALL add_article_to_commande(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commande.getId());
            preparedStatement.setInt(2, article.get_ID());
            preparedStatement.setInt(3, quantite);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerCommande(int commandeId, Connection connection) {
        try {
            String query = "CALL supprimer_commande(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commandeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerArticleFromCommande(int commandeId, int articleId, Connection connection) {
        try {
            String query = "CALL supprimer_article_from_commande(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commandeId);
            preparedStatement.setInt(2, articleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajouterArticleToCommande(int commandeId, int articleId, int quantite, Connection connection) {
        try {
            String query = "CALL add_article_to_commande(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commandeId);
            preparedStatement.setInt(2, articleId);
            preparedStatement.setInt(3, quantite);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }
}

    public void ajouterCommande(int clientId, Connection connection) {

    }
}

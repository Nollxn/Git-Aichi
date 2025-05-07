package Model;

import Model.ConnectionDAO;

import java.sql.*;
import java.util.ArrayList;

public class ArticleDAO {

    public boolean ajouterArticle(Articles article, Connection connection) throws SQLException {
        try {
            String query = "INSERT INTO articles (nom_article, prix_article, quantite_article, description_article) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, article.getNom_article());
            ps.setDouble(2, article.getPrix_article());
            ps.setInt(3, article.getQuantite_article());
            ps.setString(4, article.getDescription_article());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void supprimerArticle(int ID, Connection connection) throws SQLException {
        String query = "DELETE FROM articles WHERE id_article=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, ID);
        ps.executeUpdate();
    }

    public static ArrayList<Articles> getArticles(Connection connection) throws SQLException {
        ArrayList<Articles> articles = new ArrayList<>();
        try {
            String query = "SELECT * FROM articles";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Articles article = new Articles();
                article.setID(rs.getInt("id_article"));
                article.setNom_article(rs.getString("nom_article"));
                article.setPrix_article(rs.getDouble("prix_article"));
                article.setQuantite_article(rs.getInt("quantite_article"));
                article.setDescription_article(rs.getString("description_article"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public boolean rechercherArticle(String nom_article) throws SQLException {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        Connection connection = connectionDAO.getConnection();
        String query = "SELECT * FROM articles WHERE nom_article=?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nom_article);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
}

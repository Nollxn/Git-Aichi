package Model;

import java.sql.SQLException;
import java.util.List;

public class Commande {
    private int id;
    private String date;
    private Client client;
    private int quantite;
    private List<List<Articles>> articles;

    public Commande(String date,Client client) {
        this.date = date;
        this.client = client;
    }

    public Commande(int id, String date, int id_client) throws SQLException {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        this.setArticles(new CommandeDAO().getArticles(this, connectionDAO.getConnection()));
        this.id = id;
        this.date = date;
        ClientDAO clientDAO = new ClientDAO();
        this.client = clientDAO.getClient(id_client, connectionDAO.getConnection());

    }

    public Commande(){}

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Client getClient() {
        return client;
    }

    public int getQuantite() {
        return quantite;
    }

    public List<List<Articles>> getArticles() {
        return articles;
    }

    public void setArticles(List<List<Articles>> articles) {
        this.articles = articles;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}

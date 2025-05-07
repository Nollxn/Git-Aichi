package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDAO {

    public ClientDAO() {
    }

    public Client getClient(int id, Connection connection) throws SQLException {
        String query = "CALL getClientById(?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) { // Move the cursor to the first row
            return new Client(
                    rs.getInt("id_client"),
                    rs.getString("client_nom"),
                    rs.getString("client_prenom"),
                    rs.getString("client_adresse"),
                    rs.getString("client_mail"),
                    rs.getString("client_phone")
            );
        } else {
            throw new SQLException("Client not found with id: " + id);
        }
    }

    public ArrayList<Client> getClients(Connection connection) throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        String query = "CALL getAllClients()";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            clients.add(new Client(
                    rs.getInt("id_client"),
                    rs.getString("client_nom"),
                    rs.getString("client_prenom"),
                    rs.getString("client_adresse"),
                    rs.getString("client_mail"),
                    rs.getString("client_phone")
            ));
        }

        return clients;
    }
}
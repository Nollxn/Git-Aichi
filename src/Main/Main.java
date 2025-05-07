package Main;

import java.sql.SQLException;
import java.util.List;

import Controler.Controler;
import Model.*;
import View.View;

public class Main {

    public static void main(String[] args) throws SQLException {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        CommandeDAO commandeDAO = new CommandeDAO();
        new View();


    }

}


package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Client;

public class ClientRepository {
  
      public void insert(Client client){
        try {
    
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/iagea_ism_2024" 
                    , "root", "root");
            Statement statement = conn.createStatement();
            /*
             * String.format , on remplace les variables de la requete par des code format
             * %d => variable de Type int 
             * %s => variable de Type string
             * %f => variable de Type float
             */
             String sql=String.format("INSERT INTO `client` (`nom_client`, `prenom_client`, `tel_client`) "
                      + " VALUES ('%s', '%s', '%s')",
                      client.getNom(),client.getPrenom(),client.getTelephone());
             int nbreLigne=statement.executeUpdate(sql);
             statement.close();
             conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        }
       catch (SQLException e) {
          System.out.println("Erreur de Connexion a la BD");
      }
      }

      public List<Client> selectAll(){
         List<Client> clients=new ArrayList<>();
        try {
    
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/iagea_ism_2024" 
                   , "root", "root");
           Statement statement = conn.createStatement();
           String sql="Select * from client";
           ResultSet rs=statement.executeQuery(sql);
            while (rs.next()) {
               //Une ligne ==> rs de la requete
                Client client=new Client();
                client.setId(rs.getInt("id_client"));
                client.setNom(rs.getString("nom_client"));
                client.setPrenom(rs.getString("prenom_client"));
                client.setTelephone(rs.getString("tel_client"));
                clients.add(client);
            }
            statement.close();
            rs.close();
            conn.close();
       } catch (ClassNotFoundException e) {
           System.out.println("Erreur de chargement de Driver");
       }
       catch (SQLException e) {
         System.out.println("Erreur de Connexion a la BD");
       }
       return clients;
      }
      public Client selectClientByTel(String tel){
        Client client=null;
        try {
    
          Class.forName("com.mysql.cj.jdbc.Driver");
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/iagea_ism_2024" 
                   , "root", "root");
           Statement statement = conn.createStatement();
           String sql=String.format("Select * from client where tel_client like '%s' ",tel);
           ResultSet rs=statement.executeQuery(sql);
            if (rs.next()) {
               //Une ligne ==> rs de la requete
                client=new Client();
                client.setId(rs.getInt("id_client"));
                client.setNom(rs.getString("nom_client"));
                client.setPrenom(rs.getString("prenom_client"));
                client.setTelephone(rs.getString("tel_client"));
            }
            statement.close();
            rs.close();
            conn.close();
       } catch (ClassNotFoundException e) {
           System.out.println("Erreur de chargement de Driver");
       }
       catch (SQLException e) {
         System.out.println("Erreur de Connexion a la BD");
       }
           return client;
      }
}

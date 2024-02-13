package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Agence;
import entities.Cheque;
import entities.Client;
import entities.Compte;
import entities.ETypeCompte;
import entities.Epargne;

public class CompteRepository {
   
      public void insert(Compte compte){
        
      }

      public List<Compte> selectAll(){
         List<Compte> comptes=new ArrayList<>();
          try {
    
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/iagea_ism_2024" 
                   , "root", "root");
             Statement statement = conn.createStatement();
             String sql="SELECT * FROM `compte` c, client cl,agence a WHERE c.client_id=cl.id_client and c.agence_id=a.id_ag;";
             ResultSet rs=statement.executeQuery(sql);
            while (rs.next()) {
               //Une ligne ==> rs de la requete
                Client client=new Client();
                client.setId(rs.getInt("id_client"));
                client.setNom(rs.getString("nom_client"));
                client.setPrenom(rs.getString("prenom_client"));
                client.setTelephone(rs.getString("tel_client"));

                 Agence ag=new Agence();
                 ag.setId(rs.getInt("id_ag"));
                 ag.setNumero(rs.getString("numero_ag"));
                 ag.setAdresse(rs.getString("adresse_ag"));
                 ag.setTelephone(rs.getString("tel_ag"));
                int type=rs.getInt("type_cpte");
                 if (type==0) {
                    Cheque cpCheque=new Cheque();
                    cpCheque.setId(rs.getInt("id_cpte"));
                    cpCheque.setNumero(rs.getString("numero_cpte"));
                    cpCheque.setSolde(rs.getDouble("solde_cpte"));
                    cpCheque.setType(ETypeCompte.Cheque);
                    cpCheque.setFrais(rs.getDouble("frais_cpte"));
                    cpCheque.setClient(client);
                    cpCheque.setAgence(ag);
                    comptes.add(cpCheque);
                 } else {
                    Epargne cEpargne=new Epargne();
                    cEpargne.setId(rs.getInt("id_cpte"));
                    cEpargne.setNumero(rs.getString("numero_cpte"));
                    cEpargne.setSolde(rs.getDouble("solde_cpte"));
                    cEpargne.setType(ETypeCompte.Epargne);
                    cEpargne.setTaux(rs.getDouble("taux_cpte"));
                    cEpargne.setClient(client);
                    cEpargne.setAgence(ag);
                    comptes.add(cEpargne);
                }
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
       return  comptes;
      }
}

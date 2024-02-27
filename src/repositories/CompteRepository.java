package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import entities.Agence;
import entities.Cheque;
import entities.Client;
import entities.Compte;
import entities.ETypeCompte;
import entities.Epargne;

public class CompteRepository extends Database {
   private final  String SQL_SELECT_ALL="SELECT * FROM `compte` c, client cl,agence a WHERE c.client_id=cl.id_client and c.agence_id=a.id_ag;" ;
   private final  String SQL_SELECT_BY_TEL="Select * from client where tel_client like ? " ;
   private final  String SQL_INSERT="INSERT INTO `compte` ( `numero_cpte`, `solde_cpte`, `frais_cpte`, `type_cpte`, `taux_cpte`, `client_id`, `agence_id`)  VALUES (?,?,?,?,?,?,?)";
      public void insert(Compte compte){
         double frais=0,taux=0;
            if (compte.getType()==ETypeCompte.Cheque) {
                  Cheque cheque= (Cheque)compte;
                  frais=cheque.getFrais();
            } else {
                  Epargne epargne= (Epargne)compte;
                  taux=epargne.getTaux();
         }

         try {
            openConnexion();
            initPreparedStatement(SQL_INSERT);
             //Conversion
             statement.setString(1, compte.getNumero());
             statement.setDouble(2,compte.getSolde());
             statement.setDouble(3,frais);
             statement.setDouble(4,compte.getType().ordinal());
             statement.setDouble(5,taux);
             statement.setInt(7, compte.getAgence().getId());
             statement.setInt(6, compte.getClient().getId());
             int nbreLigne=statement.executeUpdate();
             statement.close();
             closeConnexion();
      }
     catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
    }

      }

      public List<Compte> selectAll(){
         List<Compte> comptes=new ArrayList<>();
          try {
            openConnexion();
            initPreparedStatement(SQL_SELECT_ALL);
            ResultSet rs= executeSelect();
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
       } 
       catch (SQLException e) {
         System.out.println("Erreur de Connexion a la BD");
       }
       return  comptes;
      }
}

package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Agence;
//SOLID
//Single Responsability
public class AgenceRepository extends Database implements IAgence {
     private final  String SQL_SELECT_ALL="select * from agence" ;
     private final  String SQL_SELECT_BY_NUM="select * from agence where numero_ag like ? " ;
     private final  String SQL_INSERT="INSERT INTO agence (numero_ag, adresse_ag, tel_ag) VALUES (?,?,?)";
    //select
    public  List<Agence> selectAll(){
         List<Agence> agences=new ArrayList<>();
       try {
           openConnexion();
           initPreparedStatement(SQL_SELECT_ALL);
           ResultSet rs= executeSelect();
             while (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
                 Agence ag=new Agence();
                 ag.setId(rs.getInt("id_ag"));
                 ag.setNumero(rs.getString("numero_ag"));
                 ag.setAdresse(rs.getString("adresse_ag"));
                 ag.setTelephone(rs.getString("tel_ag"));
                 agences.add(ag);
             }
             rs.close();
           closeConnexion();
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  agences;
    }
    public  Agence selectByNumero(String numero){
        Agence ag=null;
     try {
          openConnexion();
          initPreparedStatement(SQL_SELECT_BY_NUM);
          statement.setString(1, numero);
          ResultSet rs= executeSelect();
           if (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
                 ag=new Agence();
                 ag.setId(rs.getInt("id_ag"));
                 ag.setNumero(rs.getString("numero_ag"));
                 ag.setAdresse(rs.getString("adresse_ag"));
                 ag.setTelephone(rs.getString("tel_ag"));
           }
           rs.close();
           closeConnexion();
      } 
      catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
    }
      return  ag;
     }
   
    public  void insert(Agence agence){
            openConnexion();
            try {
                initPreparedStatement(SQL_INSERT);
                statement.setString(1, agence.getNumero());
                statement.setString(2, agence.getAdresse());
                statement.setString(3, agence.getTelephone());
                int nbreLigne=executeUpdate();
               closeConnexion();
             } catch (SQLException e) {
              e.printStackTrace();
             }
           
     }
}

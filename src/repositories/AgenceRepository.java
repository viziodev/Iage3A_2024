package repositories;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Agence;
//SOLID
//Single Responsability
public class AgenceRepository {
    //select
    public  List<Agence> selectAll(){
         List<Agence> agences=new ArrayList<>();
       try {
          //1-Chargement du Driver
          Class.forName("com.mysql.cj.jdbc.Driver");
          //2-Se Connecter a une BD
          //WAMP,XAMP ==> Port 3306 , user =root , MP=""
          //MAMP ==>           8889         root       root
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/iagea_ism_2024" 
                    , "root", "root");
          //3-Execution et Recuperation
           Statement statement = conn.createStatement();
           ResultSet rs=   statement.executeQuery("select * from agence");
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
             conn.close();
        } catch (ClassNotFoundException e) {
          System.out.println("Erreur de chargement de Driver");
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  agences;
    }
    public  Agence selectByNumero(String numero){
        Agence ag=null;
     try {
        //1-Chargement du Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2-Se Connecter a une BD
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/iagea_ism_2024" 
                  , "root", "root");
        //3-Execution et Recuperation
         Statement statement = conn.createStatement();
         ResultSet rs=   statement.executeQuery("select * from agence where numero_ag like '"+numero+"'");
           if (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
                 ag=new Agence();
                 ag.setId(rs.getInt("id_ag"));
                 ag.setNumero(rs.getString("numero_ag"));
                 ag.setAdresse(rs.getString("adresse_ag"));
                 ag.setTelephone(rs.getString("tel_ag"));
           }
           rs.close();
           conn.close();
      } catch (ClassNotFoundException e) {
          System.out.println("Erreur de chargement de Driver");
      }
     catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
    }
      return  ag;
     }
   
    public  void insert(Agence agence){
     try {
          //1-Chargement du Driver
          Class.forName("com.mysql.cj.jdbc.Driver");
          //2-Se Connecter a une BD
          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/iagea_ism_2024" 
                    , "root", "root");
            //3-Execution et Recuperation
             Statement statement = conn.createStatement();
             int nbreLigne=statement.executeUpdate("INSERT INTO `agence` ( `numero_ag`, `adresse_ag`, `tel_ag`) VALUES ('"+agence.getNumero()+"', '"+agence.getAdresse()+"', '"+agence.getTelephone()+"')");
             conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement de Driver");
        }
       catch (SQLException e) {
          System.out.println("Erreur de Connexion a la BD");
      }
     }
}

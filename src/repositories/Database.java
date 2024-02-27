package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * Principe Open and Close:
 *  Application informatique doit etre 
 *     Ouverte a l'Evolution et 
 *     Fermer a la Modification 
 *   App  1.0.0 Changement Mineur(correction de bugs)
 *   App  1.0.1 Evolution Fonctionnelle (Ajouter des use case)
 *   App  1.1.0
 *   App  2.0.0 Changement Majeur (Technologie,Architecture)
 *  
 * App 
 *   Modification 1.0.0    ->  1.1.0 
 *                F1,F2,F3     F1,F2,F4
 *   Evolution    1.0.0    ->  1.1.0 
 *                F1,F2,F3     F1,F2,F3,F4
 * 
 *   1-Inversion de controle: 
 *        Centraliser la creation des Objets(Repository,Service)
 *   2- Couplage Faible  
 *       a-Couplage Fort : Lorsque la dependance est une classe
 *       b-Couplage Faible : Lorsque la dependance est une interface
 *           Interface : 
 *             Classe n'ayant que des  declarations de methodes  
 *               ou methodes abstraites 
 *             methode somme : somme de 2 entiers
 *                ==> Declaration  ==> Interface
 *                   int somme(int a, int b) 
 *                ==> Definition ou implementation  ==> Classe
    *                 

                     int somme(int a, int b) {
                       if(a%2==0 && b%2==0  )return a+b;
                       return 0;
                     }

             
 * 
 * 
 * Dependancy Injection 
 *   => Injection de Dependance
 */
public class Database {
     protected Connection conn=null;
     protected  PreparedStatement statement=null;
       public void  openConnexion(){
             try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn= DriverManager.getConnection("jdbc:mysql://localhost:8889/iagea_ism_2024" 
                , "root", "root"); 
            } catch (ClassNotFoundException e) {
                System.out.println("Erreur de chargement du Driver");
            }
            catch (SQLException e) {
                System.out.println("Erreur Ouverture de la BD");
            } 
       }

    public void  closeConnexion(){
         if (conn!=null) {
            try {
                conn.close() ;
            } catch (SQLException e) {
                System.out.println("Erreur Fermeture de la BD");
            }   
         }
    }

     public void initPreparedStatement(String sql) throws SQLException{
            statement = conn.prepareStatement(sql);
     }

    public ResultSet  executeSelect(){
      ResultSet rs=null;
        try {
            rs= statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erreur Initialisation de Requete");
        }
        return rs;
    }

    public int  executeUpdate(){
        int nbreLigne=0;
        try {
            nbreLigne= statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur Initialisation de Requete");
        }
        return nbreLigne;
    }
}

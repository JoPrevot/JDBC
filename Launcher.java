import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Chargement du driver
		
		String url = "jdbc:postgresql://localhost/biblio";
		String user = "postgres";
		String password = "za!az59268";
		
		Connection con = null;
		Statement sta = null;
		ResultSet rs = null;
		
		// Afficher la liste des auteurs 
		
		List <Auteur> listeAuteurs = new ArrayList <Auteur>();
		
		try 
		{
			  Class.forName("org.postgresql.Driver");
			  
			  con = DriverManager.getConnection(url, user, password);
			  
			 sta = con.createStatement();
				 
				 String query = "Select * from auteur ORDER BY id";
				 
				 rs = sta.executeQuery(query);
				 
				 while(rs.next()) 
				 {
					 var id = rs.getLong("id");
					 String nom = rs.getString("nom");
					 String prenom = rs.getString("prenom");
					 System.out.println("Auteur " +id +" - " +nom+" "+prenom);
				 }
				 
				 rs.close();
				 sta.close();
				 con.close();
			  			  
	    } 
		catch(SQLException ex) 
		{
	    	ex.printStackTrace();
	    } 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("-------------------");
		
		//Récupérer un auteur à partir d'un ID
		
		Auteur auteur = new Auteur();
		try {
			  Class.forName("org.postgresql.Driver");
			  con = DriverManager.getConnection(url, user, password);
			  
			  sta = con.createStatement();
				 
				 String query = "Select nom,prenom from auteur where id=2";
				 
				 rs = sta.executeQuery(query);
				 
				 if(rs.next()) 
				 {
					 
					 String nom = rs.getString("nom");
					 String prenom = rs.getString("prenom");
					 System.out.println("Voici les détails de l'auteur d'ID = 2 : "+nom +" " +prenom);
				 }
				 
				 rs.close();
				 sta.close();
				 con.close();
			  
			  
	    } catch(SQLException ex) {
	    	ex.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("-------------------");
		
		//Ajouter un auteur
		
				auteur = new Auteur("Toto", "Tata", "0660606060", "toto@tata.fr");
				
				try {
					  Class.forName("org.postgresql.Driver");
					  con = DriverManager.getConnection(url, user, password);
					  
					  sta = con.createStatement();
						 
					  String query= String.format("INSERT INTO Auteur(nom,prenom,telephone,email) VALUES('%s','%s','%s','%s');",auteur.getNom(),auteur.getPrenom(),auteur.getTelephone(),auteur.getEmail());
					  
					  System.out.println(query);
						 
					  sta.executeUpdate(query);
					  
					  String query2 = "Select * from auteur ORDER BY id DESC";
						 
						 rs = sta.executeQuery(query2);
						 
						 if(rs.next()) 
						 {
							 var id = rs.getLong("id");
							 String nom = rs.getString("nom");
							 String prenom = rs.getString("prenom");
							 String telephone = rs.getString("Telephone");
							 String email = rs.getString("email");
							 System.out.println("Voici les détails de l'auteur ajouté : "+nom +" " +prenom +" " +telephone +" " +email);
						 }
					
						 rs.close();
						 sta.close();
						 con.close();
					  
					  
			    } catch(SQLException ex) {
			    	ex.printStackTrace();
			    } catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				
				System.out.println("-------------------");
				
				//Modifier un auteur
			
				try {
					  Class.forName("org.postgresql.Driver");
					  con = DriverManager.getConnection(url, user, password);
					  sta = con.createStatement();
						 
						 String query = "UPDATE Auteur SET nom = 'Tototu', prenom = 'Tatatu' WHERE id = 2;";
						 		
						 sta.executeUpdate(query);
						 
						 String query2 = "Select nom,prenom from auteur where id=2";
						 
						 rs = sta.executeQuery(query2);
						 
						 if(rs.next())
						 {
							 String nom = rs.getString("nom");
							 String prenom = rs.getString("prenom");
							 System.out.println("Voici les détails de l'auteur modifié : "+nom +" " +prenom);
						 }
					
						 rs.close();
						 sta.close();
						 con.close();
					  
			    } catch(SQLException ex) {
			    	ex.printStackTrace();
			    } catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			
				System.out.println("-------------------");
				
				//Supprimer un auteur
				
				try {
					  Class.forName("org.postgresql.Driver");
					  con = DriverManager.getConnection(url, user, password);
					  
					  sta = con.createStatement();
					  
					  String query = "DELETE FROM Auteur WHERE nom = 'Toto'";
				 		
					  sta.executeUpdate(query);
					  
					  String query2 = "Select * from auteur ORDER BY id";
						 
						 rs = sta.executeQuery(query2);
						 
						 while(rs.next()) 
						 {
							 var id = rs.getLong("id");
							 String nom = rs.getString("nom");
							 String prenom = rs.getString("prenom");
							 System.out.println("Auteur " +id +" - " +nom+" "+prenom);
						 }
						 
						 rs.close();
						 sta.close();
						 con.close();
					  
			    } catch(SQLException ex) {
			    	ex.printStackTrace();
			    } catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
	}
}

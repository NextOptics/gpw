package gpw.dao.banco;
  
import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
// import java.sql.Statement;  	 // Deve ir para a Dao
import java.util.Vector; 
import javafx.collections.*;
import java.sql.DriverManager;
import java.sql.SQLException;  
import javax.swing.JOptionPane;
import gpw.view.MessageBox;  
  
  
public class Conexao {  
	// Configura essas variaveis de acordo com o seu banco
	
	private static final String mySqlDriver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/dbfin1";
	private static final String user = "root";
	private static final String senha = "maragogi";

	private static Connection uniqueInstance;  
	// private Statement comando;  //Deve ir para a Dao  
   	
	
	private Conexao() {}
	
	public static Connection conectar() {
		if (uniqueInstance == null) {
			try {  
				uniqueInstance = conex(url, user, senha);  
				//comando = con.createStatement();    // Vai para o Conectar no chamador
				System.out.println("Conectado!");  
			} catch (ClassNotFoundException e) {  
				MessageBox.show("Erro ao carregar o driver", "Conexao");  
			} catch (SQLException e) {  
				MessageBox.show("Erro ao conectar", "Conexao");  
			}   
		}
		System.out.println();
		System.out.println("***  Conexao ja estabelecida *** ");
		System.out.println();
		return uniqueInstance;
	}
	
	public static Connection conex(String url, String user, String senha)
								throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println();
		System.out.println("*** ConFactory ** url: " + url +"   User: "+ user + " Pass: " + senha);
		return DriverManager.getConnection(url, user, senha);
	}
	

	public static void fechar() {  
		try {  
			//comando.close();      Deve ser fechado pel classe que criou o Statement
			uniqueInstance.close();
			uniqueInstance = null;
			System.out.println("Conexao Fechada");  
		} catch (SQLException e) {  
			MessageBox.show("Erro ao fechar conexao", "Conexao");  
		}  
	}
}  
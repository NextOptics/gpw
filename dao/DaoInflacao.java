package gpw.dao;  
 

import java.sql.Connection;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;
import java.sql.PreparedStatement;   
import javafx.collections.*;
import java.time.LocalDate; 
import java.util.*;    
  
import gpw.model.Inflacao;  
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;
import gpw.control.DateUtils; 

 /*
		public ObservableList<Inflacao> buscarUltimoRegistro()
		public ObservableList<Inflacao> buscarTodosRegistros()
		public void gravar(ArrayList dados)
		public ObservableList<Inflacao> buscarInflacao () 
*/	
  
public class DaoInflacao {  
	// Configura essas variaveis de acordo com o seu banco  
 
	private Connection con;  
	private Statement comando;

	private ResultSet rs = null;
	private PreparedStatement prepStmt = null;
	
	public ObservableList<Inflacao> buscarUltimoRegistro() {
		con = Conexao.conectar();
		ObservableList<Inflacao> resultadosIndices = FXCollections.observableArrayList();
		try {  
			comando = con.createStatement();
			rs = comando.executeQuery("SELECT * FROM inflacao WHERE datamoc = (SELECT MAX(datamoc) FROM inflacao)");  
			while (rs.next()) {  
				Inflacao temp = new Inflacao();  
						// pega todos os atributos da inflacao  
				temp.setDataMoc(DateUtils.asLocalDate(rs.getDate("dataMoc")));
				temp.setPoup(rs.getDouble("poup"));  
				temp.setPoupMoc(rs.getDouble("poupMoc")); 
			
				temp.setIgpm(rs.getDouble("igpm"));  
				temp.setIgpmMoc(rs.getDouble("igpmMoc"));
			
				temp.setInpc(rs.getDouble("inpc"));
				temp.setInpcMoc(rs.getDouble("inpcMoc"));
			
				temp.setIpca(rs.getDouble("ipca"));  
				temp.setIpcaMoc(rs.getDouble("ipcaMoc"));
			
				temp.setIpam(rs.getDouble("ipam"));  
				temp.setIpamMoc(rs.getDouble("ipamMoc"));
			
				temp.setMoc(rs.getDouble("moc")); 
				temp.setValidMoc(rs.getString("validmoc"));
				temp.setIdxMens(rs.getDouble("idxmens"));	
				resultadosIndices.add(temp);  
			}  
			return resultadosIndices;  
		} catch (SQLException e) {  
			MessageBox.show(e.getMessage(), "Erro ao buscar registro");
			return null;  
		}   	
	}
	
	public ObservableList<Inflacao> buscarTodosRegistros() {
		con = Conexao.conectar();
		ObservableList<Inflacao> resultadosIndices = FXCollections.observableArrayList(); 
		try {  
			comando = con.createStatement();
			rs = comando.executeQuery("SELECT * FROM inflacao");  
			while (rs.next()) {  
				Inflacao temp = new Inflacao();  
						// pega todos os atributos da inflacao  
	
				temp.setDataMoc(DateUtils.asLocalDate(rs.getDate("dataMoc")));
			
				temp.setPoup(rs.getDouble("poup"));  
				temp.setPoupMoc(rs.getDouble("poupMoc")); 
			
				temp.setIgpm(rs.getDouble("igpm"));  
				temp.setIgpmMoc(rs.getDouble("igpmMoc"));
			
				temp.setInpc(rs.getDouble("inpc"));
				temp.setInpcMoc(rs.getDouble("inpcMoc"));
			
				temp.setIpca(rs.getDouble("ipca"));  
				temp.setIpcaMoc(rs.getDouble("ipcaMoc"));
			
				temp.setIpam(rs.getDouble("ipam"));  
				temp.setIpamMoc(rs.getDouble("ipamMoc"));
			
				temp.setMoc(rs.getDouble("moc")); 
				temp.setValidMoc(rs.getString("validmoc"));
				temp.setIdxMens(rs.getDouble("idxmens"));	
				resultadosIndices.add(temp);  
			}  
			return resultadosIndices;  
		} catch (SQLException e) {
			MessageBox.show(e.getMessage(), "Erro ao buscar registros");
			return null;  
		}  
	}  	
 

	public void gravar(ArrayList dados) {

		try{
			con = Conexao.conectar();
			String query = "INSERT INTO inflacao(dataMoc, poup, poupMoc, igpm, igpmMoc,inpc, inpcMoc, ipca, ipcaMoc, ipam, ipamMoc, moc,validMoc,idxmens )"+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
					
			prepStmt = con.prepareStatement(query);
			prepStmt.setDate(1, java.sql.Date.valueOf((LocalDate)dados.get(0)));
			prepStmt.setDouble(2, (Double)dados.get(1));
			prepStmt.setDouble(3, (Double)dados.get(2));
			prepStmt.setDouble(4, (Double)dados.get(3));
			prepStmt.setDouble(5, (Double)dados.get(4));
			prepStmt.setDouble(6, (Double)dados.get(5));
			prepStmt.setDouble(7, (Double)dados.get(6));
			prepStmt.setDouble(8, (Double)dados.get(7));
			prepStmt.setDouble(9, (Double)dados.get(8));
			prepStmt.setDouble(10, (Double)dados.get(9));
			prepStmt.setDouble(11, (Double)dados.get(10));
			prepStmt.setDouble(12, (Double)dados.get(11));
			prepStmt.setString(13, (String)dados.get(12));
			prepStmt.setDouble(14, (Double)dados.get(13));
			prepStmt.execute();
			prepStmt.close();
		} catch(Exception e) {
			MessageBox.show("Erro ao gravar indices", e.getMessage());
		}
	}

	public ObservableList<Inflacao> buscarInflacao () {
		con = Conexao.conectar();
		ObservableList<Inflacao> resultadosIndices = FXCollections.observableArrayList();
		try {
			String query = "SELECT datamoc, moc, idxmens FROM inflacao ";
			prepStmt = con.prepareStatement(query);
			rs = prepStmt.executeQuery();
			while(rs.next()) {
				Inflacao temp = new Inflacao();
				temp.setDataMoc(DateUtils.asLocalDate(rs.getDate("datamoc")));
				temp.setMoc(rs.getDouble("moc"));
				temp.setIdxMens(rs.getDouble("idxmens"));
				resultadosIndices.add(temp);
			}
			return resultadosIndices;
		}catch (SQLException e) {
			MessageBox.show(e.getMessage(), "Erro ao ler tabela Inflacao");
			return null;
		}finally {
			try {
				rs.close();
				prepStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	public ArrayList<Inflacao> buscarInfla () {
		con = Conexao.conectar();
		ArrayList<Inflacao> resultadosIndices = new ArrayList<Inflacao>();
		try {
			String query = "SELECT datamoc, moc, idxmens FROM inflacao ";
			prepStmt = con.prepareStatement(query);
			rs = prepStmt.executeQuery();
			while(rs.next()) {
				Inflacao temp = new Inflacao();
				temp.setDataMoc(DateUtils.asLocalDate(rs.getDate("datamoc")));
				temp.setMoc(rs.getDouble("moc"));
				temp.setIdxMens(rs.getDouble("idxmens"));
				resultadosIndices.add(temp);
			}
			
			return resultadosIndices;
		}catch (SQLException e) {
			MessageBox.show(e.getMessage(), "Erro ao ler tabela Inflacao");
			return null;
		}finally {
			try {
				rs.close();
				prepStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
}  
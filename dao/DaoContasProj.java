package gpw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;

import gpw.dao.banco.Conexao;
import gpw.model.Projeto;
import gpw.view.MessageBox;

/*
		public ObservableList<Projeto> carregaContasProj(String valida) 
*/

public class DaoContasProj {
	private Connection con;
	ResultSet rs = null;
	private PreparedStatement prepStmt = null;
	
	public ObservableList<Projeto> carregaContasProj(String valida) {
		con = Conexao.conectar();
		ObservableList<Projeto> resultadoProjs = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM contasproj WHERE projestado < ?";
			prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, valida);
			rs = prepStmt.executeQuery();
			while(rs.next()) {
				Projeto temp = new Projeto();
				
				temp.setCodProj(rs.getInt("codproj"));
				temp.setProjNome(rs.getString("projnome"));
				temp.setProjPrevInic(rs.getDate("projprevinic")); 
				temp.setProjInicio(rs.getDate("projinicio"));
				temp.setProjPrevTermin(rs.getDate("projprevtermin"));
				temp.setProjTermino(rs.getDate("projtermino"));
				temp.setProjRespons(rs.getString("projrespons"));
				temp.setProjEstado(rs.getString("projestado"));
				temp.setProjCusPrev(rs.getDouble("projcusprev"));
				temp.setProjCusAtual(rs.getDouble("projcusatual"));
				temp.setProjDescr(rs.getString("projdescr"));
				resultadoProjs.add(temp);
			}
			return resultadoProjs;
		} catch(Exception e) {
			MessageBox.show("Erro ao ler contasproj", e.getMessage());
			return null;
		} finally {
			try {
				rs.close();
				prepStmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
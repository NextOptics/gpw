package gpw.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;

import gpw.model.ContasBanc;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;

/*
		public ObservableList<ContasBanc> carregarContasBanc(int valida)
*/


public class DaoContasBanc {
	private Connection con;
	ResultSet rs = null;
	private PreparedStatement prepStmt = null;
	
	public ObservableList<ContasBanc> carregarContasBanc(int valida) {
		con = Conexao.conectar();
		ObservableList<ContasBanc> resultadoContas = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM contasbanc WHERE contavalida = ?";
			prepStmt = con.prepareStatement(query);
			prepStmt.setInt(1, valida);
			rs = prepStmt.executeQuery();

			while(rs.next()) {
				ContasBanc temp = new ContasBanc();
				temp.setNomeBanco(rs.getString("banco"));
				temp.setCodBanco(rs.getInt("codbanco"));
				temp.setAgenBanco(rs.getString("agencia"));
				temp.setContaNumero(rs.getString("conta"));
				temp.setTipoAgencia(rs.getString("tipoagencia"));
				temp.setTitularConta(rs.getString("titular"));
				temp.setGerenteConta(rs.getString("gerente"));
				temp.setAgenCidade(rs.getString("agencidade"));
				temp.setAgenBairro(rs.getString("agenbairro"));
				temp.setAgenRua(rs.getString("agenrua"));
				temp.setAgenCep(rs.getString("agencep"));
				temp.setAgenTel(rs.getString("agentel"));
				temp.setCelGerente(rs.getString("celgerente"));
				temp.setContaValida(rs.getInt("contavalida"));
				temp.setCodConta(rs.getInt("codconta")); 
				temp.setContaTipo(rs.getString("contatipo"));
				resultadoContas.add(temp);
			}

			return resultadoContas;
		} catch(Exception e) {
			MessageBox.show("Erro ao ler contasbanc", e.getMessage());
			return null;
		} finally {
			try {
				rs.close();
				prepStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}

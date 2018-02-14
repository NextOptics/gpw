package gpw.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;
import java.util.ArrayList;
import java.time.LocalDate;

import gpw.model.ContasBanc;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;
import gpw.control.TestUtils;

/*
		public ObservableList<ContasBanc> carregarContasBanc(int valida)
		public void gravar(ArrayList dados) {
		public void editar(ArrayList dados) {
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

	public void gravar(ArrayList dados) {

		try {
			con = Conexao.conectar();
			String query = "INSERT INTO contasbanc(banco,codbanco,agencia,conta,tipoagencia,titular,gerente,agencidade,agenbairro,agenrua,agencep,agentel,celgerente,contavalida,contatipo)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, (String)dados.get(0));
			prepStmt.setInt(2, (Integer)dados.get(1));
			prepStmt.setString(3, (String)dados.get(2));
			prepStmt.setString(4, (String)dados.get(3));
			prepStmt.setString(5, (String)dados.get(4));

			prepStmt.setString(6, (String)dados.get(5));
			prepStmt.setString(7, (String)dados.get(6));
			prepStmt.setString(8, (String)dados.get(7));
			prepStmt.setString(9, (String)dados.get(8));
			prepStmt.setString(10, (String)dados.get(9));

			prepStmt.setString(11, (String)dados.get(10));
			prepStmt.setString(12, (String)dados.get(11));
			prepStmt.setString(13, (String)dados.get(12));
			prepStmt.setInt(14, (Integer)dados.get(13));
			prepStmt.setString(15, (String)dados.get(15));
			prepStmt.execute();
			prepStmt.close();
		} catch (Exception e) {
			MessageBox.show("Erro ao gravar indices", e.getMessage());
		}
	}

	public void editar(ArrayList dados) {

		try {
			con = Conexao.conectar();
			String query = "UPDATE contasbanc SET banco=?,codbanco=?,agencia=?,conta=?,tipoagencia=?,titular=?,gerente=?,agencidade=?,agenbairro=?,agenrua=?,agencep=?,agentel=?,celgerente=?,contavalida=?,contatipo=?" +" WHERE codconta = ?";
			
			PreparedStatement prepStmt = con.prepareStatement(query);
			
			prepStmt.setString(1, (String)dados.get(0));
			prepStmt.setInt(2, (Integer)dados.get(1));
			prepStmt.setString(3, (String)dados.get(2));
			prepStmt.setString(4, (String)dados.get(3));
			prepStmt.setString(5, (String)dados.get(4));

			prepStmt.setString(6, (String)dados.get(5));
			prepStmt.setString(7, (String)dados.get(6));
			prepStmt.setString(8, (String)dados.get(7));
			prepStmt.setString(9, (String)dados.get(8));
			prepStmt.setString(10, (String)dados.get(9));

			prepStmt.setString(11, (String)dados.get(10));
			prepStmt.setString(12, (String)dados.get(11));
			prepStmt.setString(13, (String)dados.get(12));
			prepStmt.setInt(14, (Integer)dados.get(13));
			prepStmt.setString(15, (String)dados.get(15));
			prepStmt.setInt(16, (Integer)dados.get(14));
			prepStmt.execute();
			prepStmt.close();
			
		} catch (Exception e) {
			MessageBox.show("Erro ao gravar Pagador", e.getMessage());
		}

	}
}

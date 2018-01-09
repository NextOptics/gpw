package gpw.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;
import java.time.LocalDate;
import java.util.*;



import gpw.model.Dsolicit;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;
import gpw.control.DateUtils;

/*
		public void gravar(ArrayList dados)
		public ObservableList<Dsolicit> carregarSolicAbertas()
*/


public class DaoDespesas {
	
	private Connection con;
	ResultSet rs = null;
	private PreparedStatement prepStmt = null;
	
	public void gravar(ArrayList dadosSolicitacao) {
		try {
			con = Conexao.conectar();
			String query = "INSERT INTO dsolicit(dataSolicit,item,aplicacao,valorPrev,prevPagam,quantidade,unidade, situacao,numParcelas,parcPagas,docum,classe,grupo,ultAltera,projeto )"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement prepStmt = con.prepareStatement(query);
			
			prepStmt.setDate(1, java.sql.Date.valueOf((LocalDate)dadosSolicitacao.get(0)));
			prepStmt.setString(2, (String)dadosSolicitacao.get(1));
			prepStmt.setString(3, (String)dadosSolicitacao.get(2));
			prepStmt.setDouble(4, (Double)dadosSolicitacao.get(3));
			prepStmt.setDate(5, java.sql.Date.valueOf((LocalDate)dadosSolicitacao.get(4)));
			prepStmt.setDouble(6, (Double)dadosSolicitacao.get(5));
			prepStmt.setInt(7, (Integer)dadosSolicitacao.get(6));
			prepStmt.setString(8, (String)dadosSolicitacao.get(7));
			prepStmt.setInt(9, (Integer)dadosSolicitacao.get(8));
			prepStmt.setInt(10, (Integer)dadosSolicitacao.get(9));
			prepStmt.setString(11, (String)dadosSolicitacao.get(10));
			prepStmt.setInt(12, (Integer)dadosSolicitacao.get(12));
			prepStmt.setInt(13, (Integer)dadosSolicitacao.get(13));
			prepStmt.setDate(14, java.sql.Date.valueOf((LocalDate)dadosSolicitacao.get(14)));
			prepStmt.setInt(15, (Integer)dadosSolicitacao.get(15));
			prepStmt.execute();
			prepStmt.close();	
		}catch (Exception e) {
			MessageBox.show( e.getMessage(), "ERRO AO GRAVAR");
		}
		
	}
		
	public ObservableList<Dsolicit> carregarSolicAbertas() {
		con = Conexao.conectar();
		ObservableList<Dsolicit> resultSolicitacoes = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM dsolicit WHERE situacao < ? ORDER BY prevpagam";
			prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, "C");  // carrega as solicitacoes com ao menos uma parcela nao paga
			rs = prepStmt.executeQuery();
			while(rs.next()) {
				Dsolicit temp = new Dsolicit();
				
				temp.setDataSolicit(DateUtils.asLocalDate(rs.getDate("datasolicit")));
				temp.setItem(rs.getString("item"));
				temp.setAplicacao(rs.getString("aplicacao"));
				temp.setValorPrev(rs.getDouble("valorprev"));
				temp.setPrevPagam(DateUtils.asLocalDate(rs.getDate("prevpagam")));
				temp.setQuantidade(rs.getDouble("quantidade"));
				temp.setUnidade(rs.getInt("unidade"));
				temp.setSituacao(rs.getString("situacao"));
				temp.setNumParcelas(rs.getInt("numParcelas"));
				temp.setParcPagas(rs.getInt("parcpagas"));
				temp.setDocum(rs.getString("docum"));
				temp.setNumSolicit(rs.getInt("numsolicit"));
				temp.setClasse(rs.getInt("classe"));
				temp.setGrupo(rs.getInt("grupo"));
				temp.setUltAltera(DateUtils.asLocalDate(rs.getDate("ultaltera")));	
				temp.setProjeto(rs.getInt("projeto"));
				resultSolicitacoes.add(temp);
			}
			return resultSolicitacoes;
		}catch(Exception e) {
			MessageBox.show("Erro ao ler tabela solicitacoes ", e.getMessage());
			return null;
		}finally {
			try{
				prepStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editar(ArrayList dadosSolicitacao) {
		try {
			con = Conexao.conectar();
			String query = "UPDATE dsolicit SET dataSolicit=?,item=?,aplicacao=?,valorPrev=?,prevPagam=?,quantidade=?,unidade=?, situacao=?,numParcelas=?,parcPagas=?,docum=?,classe=?,grupo=?,ultAltera=?,projeto=?" +" WHERE numsolicit = ?";
			PreparedStatement prepStmt = con.prepareStatement(query);
			
			prepStmt.setDate(1 , java.sql.Date.valueOf((LocalDate)dadosSolicitacao.get(0)));	
			prepStmt.setString(2 , (String)dadosSolicitacao.get(1));
			prepStmt.setString(3 , (String)dadosSolicitacao.get(2));
			prepStmt.setDouble(4 , (Double)dadosSolicitacao.get(3));
			prepStmt.setDate(5 , java.sql.Date.valueOf((LocalDate)dadosSolicitacao.get(4)));
			
			prepStmt.setDouble(6 , (Double)dadosSolicitacao.get(5));
			prepStmt.setInt(7 , (Integer)dadosSolicitacao.get(6));
			prepStmt.setString(8 , (String)dadosSolicitacao.get(7));
			prepStmt.setInt(9 , (Integer)dadosSolicitacao.get(8));
			prepStmt.setInt(10, (Integer)dadosSolicitacao.get(9));
			
			prepStmt.setString(11, (String)dadosSolicitacao.get(10));
			prepStmt.setInt(12, (Integer)dadosSolicitacao.get(12));
			prepStmt.setInt(13, (Integer)dadosSolicitacao.get(13));
			prepStmt.setDate(14, java.sql.Date.valueOf((LocalDate)dadosSolicitacao.get(14)));	
			prepStmt.setInt(15, (Integer)dadosSolicitacao.get(15));
			
			prepStmt.setInt(16, (Integer)dadosSolicitacao.get(11));
			
			prepStmt.execute();
			prepStmt.close();		
		} catch (Exception e) {
			MessageBox.show("Erro ao gravar dsolicit", e.getMessage());
		}
	}
}

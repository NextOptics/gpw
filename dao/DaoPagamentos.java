package gpw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;
import java.time.LocalDate;
import java.util.*;

import gpw.control.*;
import gpw.model.Dpagamen;
//import gpw.model.Dautorizacao;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;


public class DaoPagamentos {
	private Connection con;
	ResultSet rs = null;
	private PreparedStatement prepStmt = null;
	
	public ObservableList<Dpagamen> carregaPagsEmAberto() {
		
		ObservableList<Dpagamen> resultAutorizacoes = FXCollections.observableArrayList();
		try {
			con = Conexao.conectar();
			String query = "SELECT * FROM  dsolicit, dautorz WHERE dsolicit.numsolicit  = dautorz.numsolicit AND dautorz.situacao = ? ORDER BY dautorz.vencautorz";
			prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, "A");
			rs = prepStmt.executeQuery();
			while(rs.next()) {

				Dpagamen temp = new Dpagamen();
				temp.setNumAutorz(rs.getInt("numautorz"));
				temp.setNumSolicit(rs.getInt("numsolicit"));
				temp.setValorAutorz(rs.getDouble("valorautorz"));
				temp.setVencAutorz(DateUtils.asLocalDate(rs.getDate("vencautorz")));
				temp.setDatAutorz(DateUtils.asLocalDate(rs.getDate("datautorz")));
				temp.setSituacao(rs.getString("situacao"));
				temp.setTipoConta(rs.getString("tipoconta"));
				temp.setConta(rs.getInt("conta"));
				temp.setNumdaParcela(rs.getInt("numdaparcela"));
				resultAutorizacoes.add(temp);
				
				temp.setDataSolicit(DateUtils.asLocalDate(rs.getDate("datasolicit")));
				temp.setItem(rs.getString("item"));
				temp.setAplicacao(rs.getString("aplicacao"));
				temp.setValorPrev(rs.getDouble("valorprev"));
				temp.setPrevPagam(DateUtils.asLocalDate(rs.getDate("prevpagam")));
				temp.setQuantidade(rs.getDouble("quantidade"));
				temp.setUnidade(rs.getInt("unidade")); 
				temp.setSituacao(rs.getString("situacao"));
				temp.setNumParcelas(rs.getInt("numparcelas"));
				temp.setParcPagas(rs.getInt("parcpagas"));
				temp.setDocum(rs.getString("docum")); 
				temp.setNumSolicit(rs.getInt("numsolicit"));
				temp.setClasse(rs.getInt("classe"));
				temp.setGrupo(rs.getInt("grupo"));
				temp.setUltAltera(DateUtils.asLocalDate(rs.getDate("ultaltera")));
				temp.setProjeto(rs.getInt("projeto"));
			}
			return resultAutorizacoes;
		}catch(Exception e) {
			MessageBox.show("Erro ao ler tabelas  ", e.getMessage());
			return null;
		}finally {
			try{
				prepStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void gravar(ArrayList dados) {
		double valorMoc = 0.;
		
		try {
			con = Conexao.conectar();
			String query = "INSERT INTO dpagamen(numautorz, valorpago,datapagam,situacao,valorpagomoc)" + "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement prepStmt = con.prepareStatement(query);
			
			prepStmt.setInt(1, (Integer)dados.get(1));
			prepStmt.setDouble(2, (Double)dados.get(2));
			prepStmt.setDate(3, java.sql.Date.valueOf((LocalDate)dados.get(3)));
			prepStmt.setString(4, (String)dados.get(4));
			prepStmt.setDouble(5, (Double)dados.get(5));
			prepStmt.execute();
			prepStmt.close();
	
		}catch (Exception e) {
			MessageBox.show("Erro ao gravar indices", e.getMessage());
		}	
	}
	
	public ArrayList<Dpagamen> ultRegPagam() {
		ArrayList<Dpagamen> resultadoRegs = new ArrayList<Dpagamen>();
		try {
			con = Conexao.conectar();
			String query = "SELECT * FROM dpagamen WHERE numpagam = (SELECT MAX(numpagam) FROM dpagamen)";
			prepStmt = con.prepareStatement(query);
			rs = prepStmt.executeQuery();
			while(rs.next()) {
				Dpagamen temp = new Dpagamen();

				temp.setNumPagam(rs.getInt("numpagam"));
				temp.setNumAutorz(rs.getInt("numautorz"));
				temp.setValorPago(rs.getDouble("valorpago"));
				temp.setDataPagam(DateUtils.asLocalDate(rs.getDate("datapagam")));
				temp.setSituacao(rs.getString("situacao"));
				temp.setValorPagoMoc(rs.getDouble("valorpagomoc"));
				resultadoRegs.add(temp);
			}
			return resultadoRegs;
		}catch(Exception e) {
			MessageBox.show("Erro ao ler tabelas  ", e.getMessage());
			return null;
		}finally {
			try{
				prepStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}










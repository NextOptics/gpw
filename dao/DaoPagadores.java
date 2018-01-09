package gpw.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;
import java.time.LocalDate;
import java.util.*;

import gpw.model.Pagadores;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;
import gpw.control.DateUtils;

/*
		public ObservableList<Pagadores> carregaPagadores(int centro, int subCentro)
		public ObservableList<Pagadores> carregaPagadores()
		public void desativaUltimo (int centro, int subcentro)
		public void gravar(ArrayList dados)
		public void editar(ArrayList dados)
*/

public class DaoPagadores {
	private Connection con;
	ResultSet rs = null;
	private PreparedStatement prepStmt = null;
		
	public ObservableList<Pagadores> carregaPagadores(int centro, int subCentro) {
						// Medodo com overload 
						// Sem parametros carrega todos os todos os pagadores validos
						// Com parametros classe e grupo carreg apenas validos deste subgrupo
		con = Conexao.conectar();
		ObservableList<Pagadores> resultadoPagadores = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM pagadores WHERE estpagador > ? AND centroreceb = ? AND subcentrorec = ?";
			prepStmt = con.prepareStatement(query); // create a statement
			prepStmt.setString(1, "");
			prepStmt.setInt(2, centro);
			prepStmt.setInt(3, subCentro);
			rs = prepStmt.executeQuery();
						 // extract data from the ResultSet
			while(rs.next()) {
				Pagadores temp = new Pagadores();
				temp.setCodPagador(rs.getInt("codpagador"));
				temp.setEstPagador(rs.getString("estpagador"));
				temp.setRecDescr(rs.getString("recdescr"));
				
				temp.setValContrato(rs.getDouble("valcontrato"));
				temp.setContratoInic(DateUtils.asLocalDate(rs.getDate("contratoinic")));
				temp.setContratoFim(DateUtils.asLocalDate(rs.getDate("contratofim")));
				
				temp.setNomeContrato(rs.getString("nomecontrato"));
				temp.setContaVinc(rs.getInt("contavinc"));
				temp.setCentroReceb(rs.getInt("centroreceb"));
				
				temp.setSubCentroRec(rs.getInt("subcentrorec"));
				temp.setDataLanc(DateUtils.asLocalDate(rs.getDate("datalanc")));
				temp.setDiaVenc(rs.getInt("diavenc"));
				temp.setAgente(rs.getString("agente"));
				
				resultadoPagadores.add(temp);
			}
			return resultadoPagadores;
		} catch(Exception e) {
			MessageBox.show("Erro ao ler pagadores 59", e.getMessage());
			return null;
		} finally {
			try {
				rs.close();
				prepStmt.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ObservableList<Pagadores> carregaPagadores() {
						// Medodo com overload 
						// Sem parametros carrega todos os todos os pagadores validos
						// Com parametros classe e grupo carreg apenas validos deste subgrupo
		con = Conexao.conectar();
		ObservableList<Pagadores> resultadoPagadores = FXCollections.observableArrayList();
		try {
			String query = "SELECT * FROM pagadores WHERE estpagador = ?  ORDER BY agente";
			prepStmt = con.prepareStatement(query); // create a statement
			prepStmt.setString(1, "A");
			rs = prepStmt.executeQuery();
						 // extract data from the ResultSet
			while(rs.next()) {
				Pagadores temp = new Pagadores();
				temp.setCodPagador(rs.getInt("codpagador"));
				temp.setEstPagador(rs.getString("estpagador"));
				temp.setRecDescr(rs.getString("recdescr"));
				
				temp.setValContrato(rs.getDouble("valcontrato"));
				temp.setContratoInic(DateUtils.asLocalDate(rs.getDate("contratoinic")));
				temp.setContratoFim(DateUtils.asLocalDate(rs.getDate("contratofim")));
				
				temp.setNomeContrato(rs.getString("nomecontrato"));
				temp.setContaVinc(rs.getInt("contavinc"));
				temp.setCentroReceb(rs.getInt("centroreceb"));
				
				temp.setSubCentroRec(rs.getInt("subcentrorec"));
				temp.setDataLanc(DateUtils.asLocalDate(rs.getDate("datalanc")));
				temp.setDiaVenc(rs.getInt("diavenc"));
				temp.setAgente(rs.getString("agente"));
				
				resultadoPagadores.add(temp);
			}
			return resultadoPagadores;
		} catch(Exception e) {
			MessageBox.show("Erro ao ler pagadores 59", e.getMessage());
			return null;
		} finally {
			try {
				rs.close();
				prepStmt.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void desativaUltimo (int centro, int subcentro) {	
		try{
			con = Conexao.conectar();
			String query = "UPDATE pagadores SET estpagador = ? WHERE estpagador = ? AND centroreceb = ? AND subcentrorec = ?";
			PreparedStatement prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, "X");
			prepStmt.setString(2, "A");
			prepStmt.setInt(3, centro);
			prepStmt.setInt(4, subcentro);
			prepStmt.execute();
			prepStmt.close();
		} catch(Exception e) {
			MessageBox.show("Erro ao ler pagadores - 84", e.getMessage());
		}
	}
	
	public void gravar(ArrayList dados) {
		try {
			con = Conexao.conectar();
			String query = "INSERT INTO pagadores(estpagador,recdescr,valcontrato,contratoinic,contratofim,nomecontrato,contavinc,centroreceb,subcentrorec,datalanc,diavenc,agente)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, (String)dados.get(1));	
			prepStmt.setString(2, (String)dados.get(2));
			prepStmt.setDouble(3, (Double)dados.get(3));
			prepStmt.setDate(4, java.sql.Date.valueOf((LocalDate)dados.get(4)));	
			prepStmt.setDate(5, java.sql.Date.valueOf((LocalDate)dados.get(5)));
			prepStmt.setString(6, (String)dados.get(6));
			prepStmt.setInt(7, (Integer)dados.get(7));
			prepStmt.setInt(8, (Integer)dados.get(8));
			prepStmt.setInt(9, (Integer)dados.get(9));
			prepStmt.setDate(10, java.sql.Date.valueOf((LocalDate)dados.get(10)));
			prepStmt.setInt(11, (Integer)dados.get(11));
			prepStmt.setString(12, (String)dados.get(12));
			prepStmt.execute();
			prepStmt.close();
			
		} catch (Exception e) {
			MessageBox.show("Erro ao gravar indices", e.getMessage());
		}
	}
	
	public void editar(ArrayList dados) {	
		try {
			con = Conexao.conectar();
			String query = "UPDATE pagadores SET estpagador=?,recdescr=?,valcontrato=?,contratoinic=?,contratofim=?,nomecontrato=?,contavinc=?,centroreceb=?,subcentrorec=?,datalanc=?,diavenc=?,agente=?"+" WHERE codpagador = ?";
			
			PreparedStatement prepStmt = con.prepareStatement(query);
			
			prepStmt.setString(1, (String)dados.get(1));	
			prepStmt.setString(2, (String)dados.get(2));
			prepStmt.setDouble(3, (Double)dados.get(3));
			prepStmt.setDate(4, java.sql.Date.valueOf((LocalDate)dados.get(4)));	
			prepStmt.setDate(5, java.sql.Date.valueOf((LocalDate)dados.get(5)));
			prepStmt.setString(6, (String)dados.get(6));
			prepStmt.setInt(7, (Integer)dados.get(7));
			prepStmt.setInt(8, (Integer)dados.get(8));
			prepStmt.setInt(9, (Integer)dados.get(9));
			prepStmt.setDate(10, java.sql.Date.valueOf((LocalDate)dados.get(10)));
			prepStmt.setInt(11, (Integer)dados.get(11));
			prepStmt.setString(12, (String)dados.get(12));
			prepStmt.setInt(13, (Integer)dados.get(0));
			prepStmt.execute();
			prepStmt.close();
			
		} catch (Exception e) {
			MessageBox.show("Erro ao gravar Pagador", e.getMessage());
		}
	}
}


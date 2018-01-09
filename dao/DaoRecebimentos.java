package gpw.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;
import java.time.LocalDate;
import java.util.*;

import gpw.model.Recebimentos;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;
import gpw.control.DateUtils;

/*
		public void gravar(ArrayList dados)
		public ObservableList<PagRecebimentos> carregaPagRecebimentos(int centro, int subCentro)
		public void editar(ArrayList dados)

*/


public class DaoRecebimentos {
	private Connection con;
	ResultSet rs = null;
	private PreparedStatement prepStmt = null;

	
	public void gravar(ArrayList dados) {
		try {
			con = Conexao.conectar();
			String query = "INSERT INTO recebimentos(recestado,datalanc,prevpagam,datarecebido,valorprev,valrecebido,codpagador,docum,recebidomoc,imprenda,comisrec,outrasdesp)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, (String)dados.get(1));	
			prepStmt.setDate(2, java.sql.Date.valueOf((LocalDate)dados.get(2)));
			prepStmt.setDate(3, java.sql.Date.valueOf((LocalDate)dados.get(3)));
			prepStmt.setDate(4, java.sql.Date.valueOf((LocalDate)dados.get(4)));	
			prepStmt.setDouble(5, (Double)dados.get(5));
			prepStmt.setDouble(6, (Double)dados.get(6));
			prepStmt.setInt(7, (Integer)dados.get(7));
			prepStmt.setString(8, (String)dados.get(8));
			prepStmt.setDouble(9, (Double)dados.get(9));
			prepStmt.setDouble(10, (Double)dados.get(10));
			prepStmt.setDouble(11, (Double)dados.get(11));
			prepStmt.setDouble(12, (Double)dados.get(12));
			prepStmt.execute();
			prepStmt.close();
			
		} catch (Exception e) {
			MessageBox.show("Erro ao gravar indices", e.getMessage());
		}
	}
	
	public ObservableList<Recebimentos> carregaRecebimentos(int centro, int subCentro) {	
		
						// Medodo com overload 
						// Sem parametros carrega todos os todos os recebimentos validos
						// Com parametros classe e grupo carreg apenas validos deste subgrupo
		con = Conexao.conectar();
		ObservableList<Recebimentos> resultadoRecebimentos = FXCollections.observableArrayList();
		
		try{

			String query = "SELECT * FROM pagadores, recebimentos WHERE pagadores.estpagador = ? AND " +
				"recebimentos.recestado = ? AND recebimentos.codpagador = pagadores.codpagador AND " +
				"pagadores.centroreceb = ? AND pagadores.subcentrorec = ?";	
			
			prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, "A");
			prepStmt.setString(2, "A");
			prepStmt.setInt(3, centro);
			prepStmt.setInt(4, subCentro);
			rs = prepStmt.executeQuery();
			
			while(rs.next()) {
				Recebimentos temp = new Recebimentos();
		
				temp.setCodReceb(rs.getInt("codreceb"));	
				temp.setRecEstado(rs.getString("recestado"));				
				temp.setDataLanc(DateUtils.asLocalDate(rs.getDate("recebimentos.datalanc")));
				
				temp.setPrevPagam(DateUtils.asLocalDate(rs.getDate("prevpagam")));
				temp.setDataRecebido(DateUtils.asLocalDate(rs.getDate("datarecebido")));
				
				temp.setValorPrev(rs.getDouble("valorprev"));
				temp.setValRecebido(rs.getDouble("valrecebido"));
				temp.setCodPagador(rs.getInt("codpagador"));
				temp.setDocumento(rs.getString("docum"));
				temp.setRecebidoMoc(rs.getDouble("recebidomoc"));
					
				temp.setImpRenda(rs.getDouble("imprenda"));
				temp.setComisRec(rs.getDouble("comisrec"));
				temp.setOutrasDesp(rs.getDouble("outrasdesp"));
					
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
		
				temp.setDiaVenc(rs.getInt("diavenc"));
				temp.setAgente(rs.getString("agente"));
				resultadoRecebimentos.add(temp);	
			}
			return resultadoRecebimentos;		
		} catch(Exception e) {
			MessageBox.show("Erro ao ler pagadores ", e.getMessage());
			return null;
		}finally {
			try {
//				rs.close();
				prepStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void editar(ArrayList dados) {
		try {
			con = Conexao.conectar();
			String query =  "UPDATE recebimentos SET recestado=?,datalanc=?, prevpagam=?,datarecebido=?,valorprev=?,valrecebido=?,codpagador=?,docum=?,recebidomoc=?,imprenda=?,comisrec=?,outrasdesp=?"+
									" WHERE codreceb= ?";

			PreparedStatement prepStmt = con.prepareStatement(query);
			
			prepStmt.setString(1, (String)dados.get(1));
			prepStmt.setDate(2,  java.sql.Date.valueOf((LocalDate)dados.get(2)));
			prepStmt.setDate(3, java.sql.Date.valueOf((LocalDate)dados.get(3)));
			prepStmt.setDate(4, java.sql.Date.valueOf((LocalDate)dados.get(4))); 
			
			prepStmt.setDouble(5, (Double)dados.get(5));
			prepStmt.setDouble(6, (Double)dados.get(6)); 
			prepStmt.setInt(7, (Integer)dados.get(7));
			prepStmt.setString(8, (String)dados.get(8));
			prepStmt.setDouble(9, (Double)dados.get(9)); 
			
			prepStmt.setDouble(10, (Double)dados.get(10));
			prepStmt.setDouble(11, (Double)dados.get(11));
			prepStmt.setDouble(12, (Double)dados.get(12));
			prepStmt.setInt(13, (Integer)dados.get(0));
			
			prepStmt.execute();
			prepStmt.close();
			
		}catch(Exception e){
			MessageBox.show("Erro ao gravar Recebimento ", e.getMessage());
		}
	}
}

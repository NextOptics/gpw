package gpw.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;
import java.util.*;
import java.time.LocalDate;

import gpw.model.Bconta;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;
import gpw.control.DateUtils;

/*
	public ObservableList<Bconta> carregaSaldoBanc(int numero) 
	public void gravar(ArrayList saldos) {
			
			ArrayList recebido como parametro
		datalanc		LocalDate	0
		docum			String		1
		valor			double		2
		natOpera		String		3
		saldoant		double		4
		saldoatual		double		5
		historico		String		6
		usuario			int			7
		lancanum		int			KEY Auto_increment
		contaorigdest	String		8
		numpagam		int			9
		conta			int			10
*/

public class DaoBconta {
	private Connection con;
	private ResultSet rs = null;
	private PreparedStatement prepStmt = null;
	
	public ObservableList<Bconta> carregaSaldoBanc(int numero) {
	
		con = Conexao.conectar();
		ObservableList<Bconta> ultimoRegistro = FXCollections.observableArrayList();
		String conta = "bconta" + numero;
		try{
			String query = "SELECT * FROM " + conta + " WHERE lancanum = (SELECT MAX(lancanum) FROM " + conta + ")";
			prepStmt = con.prepareStatement(query);
			rs = prepStmt.executeQuery();
			while(rs.next()) {
				Bconta temp = new Bconta();
				temp.setDataLanc(DateUtils.asLocalDate(rs.getDate("datalanc")));
				temp.setDocumento(rs.getString("docum"));
				temp.setValorMov(rs.getDouble("valor"));
				temp.setNatOpera(rs.getString("natopera"));
				temp.setSaldoAnt(rs.getDouble("saldoant"));
				temp.setSaldoAtual(rs.getDouble("saldoatual"));
				temp.setDescrLanc(rs.getString("historico"));
				temp.setUser(rs.getInt("usuario"));
				temp.setLancaNum(rs.getInt("lancanum"));
				temp.setContaOrigDest(rs.getString("contaorigdest"));
				temp.setNumPagam(rs.getInt("numpagam"));
				ultimoRegistro.add(temp);
			}
			return ultimoRegistro;
		}catch(Exception e) {
			MessageBox.show("Erro ao ler arquivo " + conta, e.getMessage());
			return null;
		}finally {
			try{
				rs.close();
				prepStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void gravar(ArrayList saldos) {
		String conta = "bconta" + saldos.get(10);
		PreparedStatement prepStmt = null;
			
		try{
			con = Conexao.conectar();
			String query = "INSERT INTO " + conta + 
			"(dataLanc,docum,valor,natOpera,saldoant,saldoatual,historico,usuario,contaorigdest,numpagam) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?)";
			
			prepStmt = con.prepareStatement(query);
			prepStmt.setDate(1, java.sql.Date.valueOf((LocalDate)saldos.get(0))); 
			prepStmt.setString(2,(String)saldos.get(1)); 
			prepStmt.setDouble(3,(Double)saldos.get(2));
			prepStmt.setString(4,(String)saldos.get(3));
			prepStmt.setDouble(5,(Double)saldos.get(4));
			prepStmt.setDouble(6,(Double)saldos.get(5));
			prepStmt.setString(7,(String)saldos.get(6));
			prepStmt.setInt(8,(Integer)saldos.get(7));
			prepStmt.setString(9,(String)saldos.get(8));
			prepStmt.setInt(10,(Integer)saldos.get(9));
			prepStmt.execute();
			prepStmt.close();
				
		}catch(Exception e){
			MessageBox.show(e.getMessage(), "ERRO");
		}
	}

	public boolean criarBconta(int codigo) { 

		boolean valida = true;
		String conta = "bconta" + codigo;
		PreparedStatement prepStmt = null;

		try{
			con = Conexao.conectar();
			String query = "CREATE TABLE IF NOT EXISTS " + conta + 
			" (dataLanc DATE NOT NULL, " +
			"docum VARCHAR(16), " +
			"valor DECIMAL(13,2), " +
			"natopera VARCHAR(1), " +
			"saldoant DECIMAL(13,2), " +
			"saldoatual DECIMAL(13,2), " +
			"historico VARCHAR(30), " +
			"usuario INTEGER, " +
			"lancanum INTEGER KEY AUTO_INCREMENT, " +
			"contaorigdest VARCHAR(16), " +
			"numpagam INTEGER)";
			
			prepStmt = con.prepareStatement(query);
			prepStmt.execute();
			prepStmt.close();
				
		}catch(Exception e){
			valida = false;
			MessageBox.show(e.getMessage(), "ERRO");
		}

		return valida;
	}
}



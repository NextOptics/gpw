package gpw.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;
import java.time.LocalDate;
import java.util.*;

import java.sql.Statement;
import java.sql.PreparedStatement;



import gpw.model.Dautorizacao;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;
import gpw.control.DateUtils;

public class DaoDespAutorz {
	private Connection con;
	ResultSet rs = null;
	private PreparedStatement prepStmt = null;
	
	
	public void gravar(ArrayList dadosAutorizacao) {

		try {
			con = Conexao.conectar();
			String query = "INSERT INTO dautorz(numsolicit,valorautorz,vencautorz,datautorz,situacao, tipoconta,conta,numdaparcela)" + "VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement prepStmt = con.prepareStatement(query);
			
			prepStmt.setInt(1, (Integer)dadosAutorizacao.get(1));
			prepStmt.setDouble(2, (Double)dadosAutorizacao.get(2));
			prepStmt.setDate(3, java.sql.Date.valueOf((LocalDate)dadosAutorizacao.get(3)));
			prepStmt.setDate(4, java.sql.Date.valueOf((LocalDate)dadosAutorizacao.get(4)));
			prepStmt.setString(5, (String)dadosAutorizacao.get(5));
			prepStmt.setString(6, (String)dadosAutorizacao.get(6));
			prepStmt.setInt(7, (Integer)dadosAutorizacao.get(7));
			prepStmt.setInt(8, (Integer)dadosAutorizacao.get(8));
			
			prepStmt.execute();
			prepStmt.close();

			
		}catch (Exception e) {
			MessageBox.show("Erro ao gravar indices", e.getMessage());
		}
	}
	
	public void editar(ArrayList dados) {
		System.out.println("****** Entrei no edita Autorz");
		
		try {
			con = Conexao.conectar();
			String query = "UPDATE dautorz set numsolicit =?, valorautorz =?,vencautorz =?,	datautorz =?,situacao =?, tipoconta =?, conta =?, numdaparcela = ? WHERE numautorz =?";
			
			PreparedStatement prepStmt = con.prepareStatement(query);
			
			prepStmt.setInt(1, (Integer)dados.get(7));
			prepStmt.setDouble(2, (Double)dados.get(8));
			prepStmt.setDate(3, java.sql.Date.valueOf((LocalDate)dados.get(9)));
			prepStmt.setDate(4, java.sql.Date.valueOf((LocalDate)dados.get(10)));
			prepStmt.setString(5, (String)dados.get(11));
			prepStmt.setString(6, (String)dados.get(12));
			prepStmt.setInt(7, (Integer)dados.get(13));
			prepStmt.setInt(8, (Integer)dados.get(14));
			prepStmt.setInt(9, (Integer)dados.get(6));
			
			prepStmt.execute();
			prepStmt.close();
		}catch(Exception e){
			MessageBox.show("Erro ao gravar Dautorz ", e.getMessage());
		}
	}
}

/*

		System.out.println();
		System.out.println("---- DaoDespAutorz -  gravar");
		System.out.println("************  NumAutorz:	" + dadosAutorizacao.get(0));
		System.out.println("************  NumSolicit:	" + dadosAutorizacao.get(1));
		System.out.println("************  gValorAutorz:	" + dadosAutorizacao.get(2));
		System.out.println("************  VencAutorz:	" + dadosAutorizacao.get(3));
		System.out.println("************  DatAutorz:	" + dadosAutorizacao.get(4));
		System.out.println("************  Situacao:		" + dadosAutorizacao.get(5));
		System.out.println("************  TipoConta:	" + dadosAutorizacao.get(6));
		System.out.println("************  Conta:		" + dadosAutorizacao.get(7));
		System.out.println("************  NumdaParcela:	" + dadosAutorizacao.get(8));
		System.out.println("Dautorizacao extends Dsolicit" );			
		System.out.println("************  DataSolicit:	" + dadosAutorizacao.get(9));
		System.out.println("************  Item:			" + dadosAutorizacao.get(10));
		System.out.println("************  Aplicacao:	" + dadosAutorizacao.get(11));
		System.out.println("************  ValorPrev:	" + dadosAutorizacao.get(12));
		System.out.println("************  PrevPagam:	" + dadosAutorizacao.get(13));
		System.out.println("************  Quantidade:	" + dadosAutorizacao.get(14));
		System.out.println("************  Unidade:		" + dadosAutorizacao.get(15));
		System.out.println("************  Situacao:		" + dadosAutorizacao.get(16));
		System.out.println("************  NumParcelas:	" + dadosAutorizacao.get(17));
		System.out.println("************  ParcPagas:	" + dadosAutorizacao.get(18));
		System.out.println("************  Docum:		" + dadosAutorizacao.get(19));
		System.out.println("************  NumSolicit:	" + dadosAutorizacao.get(20));
		System.out.println("************  Classe:		" + dadosAutorizacao.get(21));
		System.out.println("************  Grupo:		" + dadosAutorizacao.get(22));
		System.out.println("************  UltAltera:	" + dadosAutorizacao.get(23));
		System.out.println("************  Projeto:		" + dadosAutorizacao.get(24));

*/

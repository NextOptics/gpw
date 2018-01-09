package gpw.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;
import java.util.*;

import java.time.LocalDate;
import gpw.model.Dconta;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;
import gpw.control.DateUtils;
import gpw.control.TestUtils;


public class DaoDconta {
	private Connection con;
	private ResultSet rs = null;
	private PreparedStatement prepStmt = null;

	public void gravar(ArrayList dados) {
		String conta = "dconta" + dados.get(10);
		
		try {
			con = Conexao.conectar();
			String query = "INSERT INTO " + conta + 
			"(datacompra, docum, valor, natopera, usuario,estado,valormoc,dataparcela,numpagam) " +
			"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			prepStmt = con.prepareStatement(query);
			prepStmt.setDate(1, java.sql.Date.valueOf((LocalDate)dados.get(0))); // dataCompra
			prepStmt.setString(2, (String)dados.get(1));						 // docum
			prepStmt.setDouble(3, (Double)dados.get(2));						 // valor						
			prepStmt.setString(4, (String)dados.get(3));						 // natopera
			prepStmt.setInt(5, (Integer)dados.get(4));							// Usuario
			prepStmt.setString(6, (String)dados.get(5));						// Estado
			prepStmt.setDouble(7, (Double)dados.get(6)); 						// valorMoc        // valormoc é calculado quando do faturamento do cartão
			prepStmt.setDate(8, java.sql.Date.valueOf((LocalDate)dados.get(7)));   // dataParcela
			prepStmt.setInt(9, (Integer)dados.get(8));							// numPagam
			prepStmt.execute();
			prepStmt.close();		
			
		}catch(Exception e){
			MessageBox.show(e.getMessage(), "ERRO");		
		}
	}
}

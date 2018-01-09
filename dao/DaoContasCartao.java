package gpw.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javafx.collections.*;

import gpw.model.ContasCartao;
import gpw.dao.banco.Conexao;
import gpw.view.MessageBox;


/*
public ObservableList<ContasCartao> carregarContasCard(String valida)
*/

public class DaoContasCartao {
	private Connection con;
	ResultSet rs = null;
	private PreparedStatement prepStmt = null;
	
	public ObservableList<ContasCartao> carregarContasCard(String valida) {
		con = Conexao.conectar();
		ObservableList<ContasCartao> resultadoCard = FXCollections.observableArrayList();
		try{
			String query = "SELECT * FROM contascartao WHERE cardestado = ?";
			prepStmt = con.prepareStatement(query);
			prepStmt.setString(1, valida);
			rs = prepStmt.executeQuery();
			while(rs.next()) {
				ContasCartao temp = new ContasCartao();
				temp.setCodCard(rs.getInt("codcard"));

				temp.setCardBandeira(rs.getString("cardbandeira"));
				temp.setCardTipo(rs.getString("cardtipo"));
				temp.setCardNumero(rs.getString("cardnumero"));
				temp.setCardTitular(rs.getString("cardtitular"));

				temp.setCardEmissor(rs.getString("cardemissor"));
				
				temp.setCardVerifica(rs.getString("cardverific"));			
				temp.setCardVencDia(rs.getInt("cardvencdia"));			
				temp.setCardValidMes(rs.getInt("cardvalidmes"));
				temp.setCardContaVinc(rs.getInt("cardcontavinc"));
	
				temp.setCardComent(rs.getString("cardcoment"));
				temp.setCardValidAno(rs.getInt("cardvalidano"));
				temp.setCardBoss(rs.getInt("cardboss"));
				temp.setCardEstado(rs.getString("cardestado"));
				temp.setCardFechaDia(rs.getInt("cardfechadia"));

				resultadoCard.add(temp);
			}
			return resultadoCard;
		} catch(Exception e) {
			MessageBox.show("Erro ao ler contascartao", e.getMessage());
			return null;
		}finally {
			try {
				rs.close();
				prepStmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
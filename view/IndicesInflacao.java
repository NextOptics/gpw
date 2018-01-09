package gpw.view;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.scene.control.cell.*;
import java.util.Iterator;  
import javafx.collections.*;
import java.time.LocalDate;
  
import gpw.model.Inflacao;  
import gpw.dao.DaoInflacao;  
  
public class IndicesInflacao {

	public static void show() {
		Stage stagePrimary = new Stage();
//		stagePrimary.initModality(Modality.APPLICATION_MODAL);
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Indices de Inflacao");
		stagePrimary.setMinWidth(500);
		
		VBox paneTabela = new VBox();
		paneTabela.setSpacing(10);
		paneTabela.setPadding(new Insets(10, 10, 10, 10));
		//paneTabela.getChildren().addAll(tableIndices);
		
		DaoInflacao indices = new DaoInflacao();
		
		paneTabela.getChildren().addAll(mostraTabelaIndices(indices.buscarTodosRegistros()));
		
		paneTabela.setAlignment(Pos.CENTER);
		Scene scenePrimary = new Scene(paneTabela);
		stagePrimary.setScene(scenePrimary);
		stagePrimary.showAndWait();
	
	}
	
	public static TableView<Inflacao> mostraTabelaIndices(ObservableList<Inflacao> tabela)  {	
		TableView<Inflacao> tableIndices = new TableView<Inflacao>();
		tableIndices.setItems(tabela);
		
		TableColumn<Inflacao, String> colData = new TableColumn("Data");						// 26
		colData.setMinWidth(100);
		colData.setCellValueFactory(new PropertyValueFactory<Inflacao, String> ("DataMoc"));
		
		TableColumn<Inflacao, Double> colPoup = new TableColumn("Poup");						// 31
		colPoup.setMinWidth(80);
		colPoup.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Poup"));
		
		TableColumn<Inflacao, Double> colPoupMoc = new TableColumn("Poup(moc)");						// 36
		colPoupMoc.setMinWidth(80);
		colPoupMoc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("PoupMoc"));
		
		TableColumn<Inflacao, Double> colIgpm = new TableColumn("Igpm");						// 36
		colIgpm.setMinWidth(80);
		colIgpm.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Igpm"));
		
		TableColumn<Inflacao, Double> colIgpmMoc = new TableColumn("Igpm(moc)");						// 36
		colIgpmMoc.setMinWidth(80);
		colIgpmMoc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("IgpmMoc"));
		
		TableColumn<Inflacao, Double> colInpc = new TableColumn("Inpc");						// 36
		colInpc.setMinWidth(80);
		colInpc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Inpc"));
		
		TableColumn<Inflacao, Double> colInpcMoc = new TableColumn("Inpc(moc)");						// 36
		colInpcMoc.setMinWidth(80);
		colInpcMoc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("InpcMoc"));
		
		TableColumn<Inflacao, Double> colIpca = new TableColumn("Ipca");						// 36
		colIpca.setMinWidth(80);
		colIpca.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Ipca"));
		
		TableColumn<Inflacao, Double> colIpcaMoc = new TableColumn("Ipca(moc)");						// 36
		colIpcaMoc.setMinWidth(80);
		colIpcaMoc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("IpcaMoc"));
		
		TableColumn<Inflacao, Double> colIpam = new TableColumn("Ipam");						// 36
		colIpam.setMinWidth(80);
		colIpam.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Ipam"));
		
		TableColumn<Inflacao, Double> colIpamMoc = new TableColumn("Ipam(moc)");						// 36
		colIpamMoc.setMinWidth(80);
		colIpamMoc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("IpamMoc"));
		
		TableColumn<Inflacao, Double> colMoc = new TableColumn("Moc");						// 36
		colMoc.setMinWidth(80);
		colMoc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Moc"));
		
		TableColumn<Inflacao, Double> colIdxmens = new TableColumn("Idx-mensal");						// 36
		colIdxmens.setMinWidth(80);
		colIdxmens.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Idxmens"));
		
		tableIndices.getColumns().addAll(colData, colPoup, colPoupMoc, colIgpm, colIgpmMoc,
		colInpc, colInpcMoc, colIpca, colIpcaMoc, colIpam, colIpamMoc, colMoc, colIdxmens
		);
		
		return tableIndices;	
	}
} 
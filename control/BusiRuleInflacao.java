package gpw.control;

import java.util.*;
import gpw.control.BusiRuleSelec;
import java.time.LocalDate;
import gpw.view.MessageBox;

import javafx.collections.*;
import gpw.model.Inflacao;  
import gpw.dao.DaoInflacao;

/*
		public boolean validar(ArrayList dados)
		public boolean lancar(ArrayList dados)
		public boolean editar(ArrayList dados)
		public boolean confirmar(ArrayList dados)
		public boolean carregar(ArrayList dados)
*/

public class BusiRuleInflacao implements BusiRuleSelec {
	static Inflacao inflacaoMensal;
	static LocalDate dataSelecionada;

	public boolean validar(ArrayList dados) {
		boolean valida = true;
		LocalDate dataTempUm, dataTempDois;
		double temp, minimo = 0.00001, maximo = 1.99;
		
		temp = Math.abs((Double)dados.get(1));
		if((temp < minimo) || (temp > maximo)) {
			valida = false;
			MessageBox.show("Digitacao Invalida", "POUP");
		}
		temp = Math.abs((Double)dados.get(3));
		if((temp < minimo) || (temp > maximo)) {
			valida = false;
			MessageBox.show("Digitacao Invalida", "IGPM");
		}
		temp = Math.abs((Double)dados.get(5));
		if((temp < minimo) || (temp > maximo)) {
			valida = false;
			MessageBox.show("Digitacao Invalida", "INPC");
		}
		temp = Math.abs((Double)dados.get(7));
		if((temp < minimo) || (temp > maximo)) {
			valida = false;
			MessageBox.show("Digitacao Invalida", "IPCA");
		}
		temp = Math.abs((Double)dados.get(9));
		if((temp < minimo) || (temp > maximo)) {
			valida = false;
			MessageBox.show("Digitacao Invalida", "IPAM");
		}
				// dados.get(0) ----> dataMoc Old
				// dados.get(14)----> data selecionada
		if (((LocalDate)(dados.get(14)) != null) && (((LocalDate)dados.get(0)) != null))	{
			dataTempUm = DateUtils.lastDateOfNextMonth((LocalDate)(dados.get(0)));
			dataTempDois = DateUtils.lastDateOfPreviousMonth((LocalDate)(dados.get(14)));
			if (!dataTempUm.isEqual(dataTempDois)) {
				valida = false;
				MessageBox.show("Data de lancamento incompativel com ultimo lancamento", "ERRO");
			}	
		}else{
			valida = false;
			MessageBox.show("Datas incorretas ", "ERRO");
		}
		return valida;
	}
	
	public boolean lancar(ArrayList dados) {
		boolean valida = true;
		inflacaoMensal = new Inflacao();
			
		inflacaoMensal.setDataMoc((LocalDate)dados.get(0)); // ?
		inflacaoMensal.setPoup((Double)dados.get(1));
		inflacaoMensal.setPoupMoc((Double)dados.get(2));
		inflacaoMensal.setIgpm((Double)dados.get(3));
		inflacaoMensal.setIgpmMoc((Double)dados.get(4));		
		inflacaoMensal.setInpc((Double)dados.get(5));
		inflacaoMensal.setInpcMoc((Double)dados.get(6));
		inflacaoMensal.setIpca((Double)dados.get(7));
		inflacaoMensal.setIpcaMoc((Double)dados.get(8));
		inflacaoMensal.setIpam((Double)dados.get(9));	
		inflacaoMensal.setIpamMoc((Double)dados.get(10));
		inflacaoMensal.setMoc((Double)dados.get(11));
		inflacaoMensal.setValidMoc("A");
		inflacaoMensal.setIdxMens((Double)dados.get(13));
		dataSelecionada = (LocalDate)dados.get(14);
		
		inflacaoMensal.setDataMoc(DateUtils.lastDateOfNextMonth((LocalDate)(dados.get(0)))); //Data Novo Lancamento
		
		Double xPoup,xIgpm, xInpc,xIpca, xIpam, xMoc;
		Double xPoupMoc, xIgpmMoc, xInpcMoc, xIpcaMoc, xIpamMoc;
		
		xPoup = inflacaoMensal.getPoup();
		xIgpm = inflacaoMensal.getIgpm();
		xInpc = inflacaoMensal.getInpc();
		xIpca = inflacaoMensal.getIpca();
		xIpam = inflacaoMensal.getIpam();
		inflacaoMensal.setIdxMens(((1+xPoup/100.)+(1+xIgpm/100.)+(1+xInpc/100.)+(1+xIpca/100.)+(1+xIpam/100.))/5.);
		
		xPoupMoc = inflacaoMensal.getPoupMoc();
		xIgpmMoc = inflacaoMensal.getIgpmMoc();
		xInpcMoc = inflacaoMensal.getInpcMoc();
		xIpcaMoc = inflacaoMensal.getIpcaMoc();
		xIpamMoc = inflacaoMensal.getIpamMoc();	
		inflacaoMensal.setPoupMoc((1+xPoup/100.) * xPoupMoc);
		inflacaoMensal.setIgpmMoc((1+xIgpm/100.) * xIgpmMoc);
		inflacaoMensal.setInpcMoc((1+xInpc/100.) * xInpcMoc);
		inflacaoMensal.setIpcaMoc((1+xIpca/100.) * xIpcaMoc);
		inflacaoMensal.setIpamMoc((1+xIpam/100.) * xIpamMoc);
		
		inflacaoMensal.setMoc((inflacaoMensal.getPoupMoc() + inflacaoMensal.getIgpmMoc() +
				inflacaoMensal.getInpcMoc() + inflacaoMensal.getIpcaMoc() + inflacaoMensal.getIpamMoc())/5);
					
		ArrayList arrListRegistro = new ArrayList();
		
		arrListRegistro.add(inflacaoMensal.getDataMoc());
		arrListRegistro.add(inflacaoMensal.getPoup());
		arrListRegistro.add(inflacaoMensal.getPoupMoc());
		arrListRegistro.add(inflacaoMensal.getIgpm());
		arrListRegistro.add(inflacaoMensal.getIgpmMoc());
		arrListRegistro.add(inflacaoMensal.getInpc());
		arrListRegistro.add(inflacaoMensal.getInpcMoc());
		arrListRegistro.add(inflacaoMensal.getIpca());
		arrListRegistro.add(inflacaoMensal.getIpcaMoc());
		arrListRegistro.add(inflacaoMensal.getIpam());
		arrListRegistro.add(inflacaoMensal.getIpamMoc());
		arrListRegistro.add(inflacaoMensal.getMoc());
		arrListRegistro.add(inflacaoMensal.getValidMoc());
		arrListRegistro.add(inflacaoMensal.getIdxMens());
		
		DaoInflacao registro = new DaoInflacao();
			registro.gravar(arrListRegistro);							 	
		return valida;
	}
	
	public boolean editar(ArrayList dados) {
		boolean valida = true;
		return valida;
	}
	
	public boolean confirmar(ArrayList dados) {
		boolean valida = true;
		return valida;
	}
	public boolean carregar(ArrayList dados) {
		boolean valida = true;
		return valida;
	}

	public boolean liberar(ArrayList dados) {
		boolean valida = false;	
		return valida;
	}
	
	public boolean criar(ArrayList dados) {
		boolean valida = false;	
		return valida;
	}	
}
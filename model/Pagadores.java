package gpw.model;

import java.time.LocalDate;

public class Pagadores {
	private int codPagador;
	private String estPagador;
	private String recDescr;
	private double valContrato;
	private LocalDate contratoInic;
	private LocalDate contratoFim;
	private String nomeContrato;
	private int contaVinc;
	private int centroReceb;
	private int subCentroRec;
	private LocalDate dataLanc;
	private int diaVenc;
	private String agente;
	
	public Pagadores () {
		this.codPagador = 0;
		this.estPagador = "";
		this.recDescr = "";
		this.valContrato = 0.0;
//		this.contratoInic;
//		this.contratoFim;
		this.nomeContrato = "";
		this.contaVinc = 0;
		this.centroReceb = 0;
		this.subCentroRec = 0;
//		this.dataLanc;
		this.diaVenc = 0;
		this.agente = "";
	}
	
	
	public Pagadores(int codPagador, String estPagador, String recDescr, double valContrato,
			LocalDate contratoInic, LocalDate contratoFim, String nomeContrato, int contaVinc, int centroReceb,
			int subCentroRec, LocalDate dataLanc, int diaVenc, String agente) {
			
		this.codPagador = codPagador;
		this.estPagador = estPagador;
		this.recDescr = recDescr;
		this.valContrato = valContrato;
		this.contratoInic = contratoInic;
		this.contratoFim = contratoFim;
		this.nomeContrato = nomeContrato;
		this.contaVinc = contaVinc;
		this.centroReceb = centroReceb;
		this.subCentroRec = subCentroRec;
		this.dataLanc = dataLanc;
		this.diaVenc = diaVenc;
		this.agente = agente;
	}
	
	public int getCodPagador() {return this.codPagador;}
	public void setCodPagador(int codPagador) {this.codPagador = codPagador;}
	
	public String getEstPagador() {return this.estPagador;}
	public void setEstPagador(String estPagador) {this.estPagador = estPagador;}
	
	public String getRecDescr() {return this.recDescr;}
	public void setRecDescr(String recDescr) {this.recDescr = recDescr;}
	
	public double getValContrato() {return this.valContrato;}
	public void setValContrato(double valContrato) {this.valContrato = valContrato;}
	
	public LocalDate getContratoInic() {return this.contratoInic;}
	public void setContratoInic(LocalDate contratoInic) {this.contratoInic = contratoInic;}

	public LocalDate getContratoFim() {return this.contratoFim;}
	public void setContratoFim(LocalDate contratoFim) {this.contratoFim = contratoFim;}
	
	public String getNomeContrato() {return this.nomeContrato;}
	public void setNomeContrato(String nomeContrato) {this.nomeContrato = nomeContrato;}
	
	public int getContaVinc() {return this.contaVinc;}
	public void setContaVinc(int contaVinc) {this.contaVinc = contaVinc;}
	
	public int getCentroReceb() {return this.centroReceb;}
	public void setCentroReceb(int centroReceb) {this.centroReceb = centroReceb;}
	
	public int getSubCentroRec() {return this.subCentroRec;}
	public void setSubCentroRec(int subCentroRec) {this.subCentroRec = subCentroRec;}
	
	public LocalDate getDataLanc() {return this.dataLanc;}
	public void setDataLanc(LocalDate dataLanc) {this.dataLanc = dataLanc;}
	
	public int getDiaVenc() {return this.diaVenc;}
	public void setDiaVenc(int diaVenc) {this.diaVenc = diaVenc;}
	
	public String getAgente() {return this.agente;}
	public void setAgente(String agente){
		this.agente = "";
		if(agente != null){
			this.agente = agente;
		}
	}
	
}
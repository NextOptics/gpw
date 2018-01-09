package gpw.model;

import java.time.LocalDate;

public class Dpagamen extends Dautorizacao {
	
	private int numPagam;
	private int numAutorz;
	private double valorPago;
	private LocalDate dataPagam;
	private String situacao;
	private double valorPagoMoc;
	
	public Dpagamen() {
		this.numPagam = 0;
		this.numAutorz = 0;
		this.valorPago = 0.;
		this.dataPagam = LocalDate.now();
		this.situacao = "";
		this.valorPagoMoc = 0;
	}
	
	public Dpagamen(int numPagam, int numAutorz, double valorPago, LocalDate dataPagam, String situacao, double valorPagoMoc){
		this.numPagam = numPagam;
		this.numAutorz = numAutorz;
		this.valorPago = valorPago;
		this.dataPagam = dataPagam;
		this.situacao = situacao;
		this.valorPagoMoc = valorPagoMoc;
	}
	
	
	public int getNumPagam() {return this.numPagam;}
	public void setNumPagam(int numPagam) {this.numPagam = numPagam;}
	
	public int getNumAutorz() {return this.numAutorz;}
	public void setNumAutorz(int numAutorz) {this.numAutorz = numAutorz;}
	
	public double getValorPago() {return this.valorPago;}
	public void setValorPago(double valorPago) {this.valorPago = valorPago;}
	
	public LocalDate getDataPagam() {return this.dataPagam;}
	public void setDataPagam(LocalDate dataPagam) {this.dataPagam = dataPagam;}

	public String getSituacao() {return this.situacao;}
	public void setSituacao(String situacao) {this.situacao = situacao;}
	
	public double getValorPagoMoc() {return this.valorPagoMoc;}
	public void setValorPagoMoc(double valorPagoMoc) {this.valorPagoMoc = valorPagoMoc;}
}

/*
	1 – numpagam		INTEGER	KEY 	AUTO_INCREMENT
	2 – numautorz		INTEGER
	3 – valorpago		DECIMAL (12.2)
	4 – datapagam		DATE
	5 – situação		VARCHAR (1)
	6 – valorpagomoc	DECIMAL(12,2)

*/
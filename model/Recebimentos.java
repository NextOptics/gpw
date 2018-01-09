package gpw.model;

import java.time.LocalDate;

public class Recebimentos extends Pagadores{
	private int codReceb;
	private String recEstado;
	private LocalDate dataLanc;
	private LocalDate prevPagam;
	private LocalDate dataRecebido;
	
	private double valorPrev;
	private double valRecebido;
	private int codPagador;
	private String documento;
	private double recebidoMoc;
	
	private double impRenda;
	private double comisRec;
	private double outrasDesp;
	
	public Recebimentos() {
		this.codReceb = 0;
		this.recEstado = "";
		this.dataLanc = LocalDate.now();
		this.prevPagam = LocalDate.now();
		this.dataRecebido = LocalDate.now();
	
		this.valorPrev = 0.;
		this.valRecebido = 0.;
		this.codPagador = 0;
		this.documento = "";
		this.recebidoMoc = 0.;
	
		this.impRenda = 0.;
		this.comisRec = 0.;
		this.outrasDesp = 0.;
	}
	
/*	
	int codReceb;
	String recEstado;
	LocalDate dataLanc;
	LocalDate prevPagam;
	LocalDate dataRecebido;
	
	double valorPrev;
	double valRecebido;
	int codPagador;
	String documento
	double recebidoMoc;
	
	double impRenda;
	double comisRec;
	double outrasRec;
*/	
	
	
	public Recebimentos(int codReceb,String recEstado,LocalDate dataLanc,LocalDate prevPagam,LocalDate dataRecebido,
						double valorPrev,double valRecebido,int codPagador,String documento,double recebidoMoc,
						double impRenda,double comisRec,double outrasDesp) {
							
		this.codReceb = codReceb;
		this.recEstado = recEstado;
		this.dataLanc = dataLanc;
		this.prevPagam = prevPagam;
		this.dataRecebido = dataRecebido;
	
		this.valorPrev = valorPrev;
		this.valRecebido = valRecebido;
		this.codPagador = codPagador;
		this.documento = documento;
		this.recebidoMoc = recebidoMoc;
	
		this.impRenda = impRenda;
		this.comisRec = comisRec;
		this.outrasDesp = outrasDesp;
	}
	
	public int getCodReceb() {return this.codReceb;}
	public void setCodReceb(int codReceb) {this.codReceb = codReceb;}
	
	public String getRecEstado() {return this.recEstado;}
	public void setRecEstado(String recEstado) {this.recEstado = recEstado;}
	
	public LocalDate getDataLanc() {return this.dataLanc;}
	public void setDataLanc(LocalDate dataLanc) {this.dataLanc = dataLanc;}
	
	public LocalDate getPrevPagam() {return this.prevPagam;}
	public void setPrevPagam(LocalDate prevPagam) {this.prevPagam = prevPagam;}
	
	public LocalDate getDataRecebido() {return this.dataRecebido;}
	public void setDataRecebido(LocalDate dataRecebido) {this.dataRecebido = dataRecebido;}
	
	public double getValorPrev() {return this.valorPrev;}
	public void setValorPrev(double valorPrev) {this.valorPrev = valorPrev;}
	
	public double getValRecebido() {return this.valRecebido;}
	public void setValRecebido(double valRecebido) {this.valRecebido = valRecebido;}
	
	public int getCodPagador() {return this.codPagador;}
	public void setCodPagador(int codPagador) {this.codPagador = codPagador;}
	
	public String getDocumento() {return this.documento;}
	public void setDocumento(String documento) {this.documento = documento;}
	
	public double getRecebidoMoc() {return this.recebidoMoc;}
	public void setRecebidoMoc(double recebidoMoc) {this.recebidoMoc = recebidoMoc;}
	
	public double getImpRenda() {return this.impRenda;}
	public void setImpRenda(double impRenda) {this.impRenda = impRenda;}
	
	public double getComisRec() {return this.comisRec;}
	public void setComisRec(double comisRec) {this.comisRec = comisRec;}
	
	public double getOutrasDesp() {return this.outrasDesp;}
	public void setOutrasDesp(double outrasDesp) {this.outrasDesp = outrasDesp;}

/*
7 – Campos da tabela  recebimentos :
	1 – codreceb		INTEGER		AUTO INCREMENT
	2 – recestado		VARCHAR(1)
	3 – datalanc		DATE
	4 – prevpagam	DATE-
	5 – datarecebido	DATE
	6 – valorprev		DECIMAL(12.2)
	7 – valrecebido	DECIMAL(12.2)
	8 – codpagador	INTEGER
	9 - docum		VARCHAR(20)
         10 -  recebidomoc	DECIMAL(12,2)
         11 – imprenda		DECIMAL(12,2)
         12 – comisrec		DECIMAL(12,2)
         13 – outrasdesp		DECIMAL(12,2)
	

recestado:
	A : Lançado
	B : Pago
	X : Cancelado

*/
	
}
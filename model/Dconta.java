package gpw.model;

import java.time.LocalDate;

public class Dconta {
	private LocalDate dataCompra;
	private String documento;
	private double valorMov;
	private String natOpera;
	private int user;
	private int lancaNum;
	private String estado;
	private double valorMoc;
	private LocalDate dataParcela;
	private int numPagam;
	
	public Dconta() {
		this.dataCompra = LocalDate.now();
		this.documento = "";
		this.valorMov = 0.;
		this.natOpera = "";
		this.user = 0;
		this.lancaNum = 0;
		this.estado = "";
		this.valorMoc = 0.;
		this.dataParcela = LocalDate.now();
		this.numPagam = 0;
		
	}
	
	public Dconta(LocalDate dataCompra,String documento,double valorMov,String natOpera,int user,
					int lancaNum,String estado,double valorMoc,LocalDate dataParcela, int numPaga) {
		this.dataCompra = dataCompra;
		this.documento = documento;
		this.valorMov = valorMov;
		this.natOpera = natOpera;
		this.user = user;
		this.lancaNum = lancaNum;
		this.estado = estado;
		this.valorMoc = valorMoc;
		this.dataParcela = dataParcela;
		this.numPagam = numPagam;
	}
	
	public LocalDate getDataCompra() {return this.dataCompra;}
	public void setDataCompra(LocalDate dataCompra) {this.dataCompra = dataCompra;}
	
	public String getDocumento() {return this.documento;}
	public void setDocumento(String documento) {this.documento = documento;};
	
	public double getValorMov(){return this.valorMov;}
	public void setValorMov(double valorMov) {this.valorMov = valorMov;};
	
	public String getNatOpera(){return this.natOpera;}
	public void setNatOpera(String natOpera){this.natOpera = natOpera;}
	
	public int getUser(){return this.user;}
	public void setUser(int user){this.user = user;}
	
	public int getLancaNum(){return this.lancaNum;}
	public void setLancaNum(int lancaNum){this.lancaNum = lancaNum;};
	
	public String getEstado(){return this.estado;}
	public void setEstado(String estado){this.estado = estado;}
	
	public double getValorMoc(){return this.valorMoc;}
	public void setValorMoc(double valorMoc){this.valorMoc = valorMoc;};
	
	public LocalDate getDataParcela(){return this.dataParcela;}
	public void setDataParcela(LocalDate dataParcela){this.dataParcela = dataParcela;};
	
	public int getNumPagam(){return this.numPagam;}
	public void setNumPagam(int numPagam){this.numPagam = numPagam;} 

/*
dcontaX

datacompra	DATE			Data da compra
docum		VARCHAR(16)		Número do documento	
valor		DECIMAL(13,2)	Valor do lançamento
natopera	VARCHAR(1)		Tipo da operação
usuário		INTEGER			Username do usuário que fez o lançamento
lancanum	INTEGER			Número do lançamento	
estado		VARCHAR(1)		Estado do lançamento
valormoc	DECIMAL(13,2)	Valor do lançamento em moc no dia do faturamento
dataparcela	DATE			Data vencimento da parcela
numpagam	INTEGER			Numero do pagamento


estado:
	A – Ainda não debitado
	B – Debitado
	X - Cancelado

OBS: valormoc É  CALCULADO  NO  DIA  DO  FATURAMENTO  DO  CARTÃO
*/	
}
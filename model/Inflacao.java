package gpw.model;

import java.time.LocalDate;

/*
inflacaoMensal
	0	getDataMoc();
	1	getPoup());
	2	getPoupMoc());
	3	getIgpm());
	4	getIgpmMoc());		
	5	getInpc());
	6	getInpcMoc());
	7	getIpca());
	8	getIpcaMoc());
	9	getIpam());	
	10	getIpamMoc());
	11	getMoc());
	12	getValidMoc());
	13	getIdxMens());
	14	dataSelecionada;
*/


public class Inflacao{
	private LocalDate dataMoc;
	private double poup;
	private double poupMoc;
	private double igpm;
	private double igpmMoc;
	private double inpc;
	private double inpcMoc;
	private double ipca;
	private double ipcaMoc;
	private double ipam;
	private double ipamMoc;
	private double moc;
	private String validMoc;
	private double idxmens;
	
	public Inflacao() {
		this.poup = 0.0;
		this.poupMoc = 0.0;
		this.igpm = 0.0;
		this.igpmMoc = 0.0;
		this.inpc = 0.0;
		this.inpcMoc = 0.0;
		this.ipca = 0.0;
		this.ipcaMoc = 0.0;
		this.ipam = 0.0;
		this.ipamMoc = 0.0;
		this.moc = 0.0;
		this.validMoc = "";
		this.idxmens = 0.0;
	}
	
	public Inflacao(LocalDate dataMoc, double poup, double poupMoc, double igpm, double igpmMoc,
				double inpc, double inpcMoc, double ipca, double ipcaMoc,double ipam,
				double ipamMoc, double moc, String validMoc, double idxmens) {
		this.dataMoc = dataMoc;
		this.poup = poup;
		this.poupMoc = poupMoc;
		this.igpm = igpm;
		this.igpmMoc = igpmMoc;
		this.inpc = inpc;
		this.inpcMoc = inpcMoc;
		this.ipca = ipca;
		this.ipcaMoc = ipcaMoc;
		this.ipam = ipam;
		this.ipamMoc = ipamMoc;
		this.moc = moc;
		this.validMoc = validMoc;
		this.idxmens = idxmens;
	}
	
	
	public LocalDate getDataMoc() {return this.dataMoc;}
	public void setDataMoc(LocalDate dataMoc) {this.dataMoc = dataMoc;}
	
	public double getPoup() {return this.poup;}
	public void setPoup(double poup) {this.poup = poup;}
	
	public double getPoupMoc() {return this.poupMoc;}
	public void setPoupMoc(double poupMoc) {this.poupMoc = poupMoc;}
	
	public double getIgpm() {return this.igpm;}
	public void setIgpm(double igpm) {this.igpm = igpm;}
	
	public double getIgpmMoc() {return this.igpmMoc;}
	public void setIgpmMoc(double igpmMoc) {this.igpmMoc = igpmMoc;}
	
	public double getInpc() {return this.inpc;}
	public void setInpc(double inpc) {this.inpc = inpc;}
	
	public double getInpcMoc() {return this.inpcMoc;}
	public void setInpcMoc(double inpcMoc) {this.inpcMoc = inpcMoc;}
	
	public double getIpca() {return this.ipca;}
	public void setIpca(double ipca) {this.ipca = ipca;}
	
	public double getIpcaMoc() {return this.ipcaMoc;}
	public void setIpcaMoc(double ipcaMoc) {this.ipcaMoc = ipcaMoc;}
	
	public double getIpam() {return this.ipam;}
	public void setIpam(double ipam) {this.ipam = ipam;}
	
	public double getIpamMoc() {return this.ipamMoc;}
	public void setIpamMoc(double ipamMoc) {this.ipamMoc = ipamMoc;}
	
	public double getMoc() {return this.moc;}
	public void setMoc(double moc) {this.moc = moc;}
	
	public String getValidMoc() {return this.validMoc;}
	public void setValidMoc(String validMoc) {this.validMoc = validMoc;}
	
	public double getIdxMens() {return this.idxmens;}
	public void setIdxMens(double idxmens) {this.idxmens = idxmens;}
}
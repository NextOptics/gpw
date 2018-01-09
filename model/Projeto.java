package gpw.model;

import java.util.Date;

public class Projeto {
	private int codProj;
	private String projNome;
	private Date projPrevInic;
	private Date projInicio;
	private Date projPrevTermin;
	private Date projTermino;
	private String projRespons;
	private String projEstado;
	private double projCusPrev;
	private double projCusAtual;
	private String projDescr;
	
	public Projeto() {
		this.codProj = 0;
		this.projNome = "";
//		this.projPrevInic;
//		this.projInicio;
//		this.projPrevTermin;
//		this.projTermino;
		this.projRespons = "";
		this.projEstado = "";
		this.projCusPrev = 0.;
		this.projCusAtual = 0.;
		this.projDescr = "";
	}
	
	public Projeto(int codProj,String projNome,Date projPrevInic,Date projInicio,Date projPrevTermin,Date projTermino,
					String projRespons,String projEstado,double projCusPrev,double projCusAtual,String projDescr) {
	
		this.codProj = codProj;
		this.projNome = projNome;
		this.projPrevInic = projPrevInic;
		this.projInicio = projInicio;
		this.projPrevTermin = projPrevTermin;
		this.projTermino = projTermino;
		this.projRespons = projRespons;
		this.projEstado = projEstado;
		this.projCusPrev = projCusPrev;
		this.projCusAtual = projCusAtual;
		this.projDescr = projDescr;
	}
	
	public int getCodProj() {return this.codProj;}
	public void setCodProj(int codProj) {this.codProj = codProj;}
	
	public String getProjNome() {return this.projNome;}
	public void setProjNome(String projNome) {this. projNome = projNome;}
	
	public Date getProjPrevInic() {return this.projPrevInic;}
	public void setProjPrevInic(Date projPrevInic) {this.projPrevInic = projPrevInic;}
	
	public Date getProjInicio() {return this.projInicio;}
	public void setProjInicio(Date projInicio) {this.projInicio = projInicio;}
	
	public Date getProjPrevTermin() {return this.projPrevTermin;}
	public void setProjPrevTermin(Date projPrevTermin) {this.projPrevTermin = projPrevTermin;}
	
	public Date getProjTermino() {return this.projTermino;}
	public void setProjTermino(Date projTermino) {this.projTermino = projTermino;}
	
	public String getProjRespons() {return this.projRespons;}
	public void setProjRespons(String projRespons) {this.projRespons = projRespons;}
	
	public String getProjEstado() {return this.projEstado;}
	public void setProjEstado(String projEstado) {this.projEstado = projEstado;}
	
	public double getProjCusPrev() {return this.projCusPrev;}
	public void setProjCusPrev(double projCusPrev) {this.projCusPrev = projCusPrev;}
	
	public double getProjCusAtual() {return this.projCusAtual;}
	public void setProjCusAtual(double projCusAtual) {this.projCusAtual = projCusAtual;}
	
	public String getProjDescr() {return this.projDescr;}
	public void setProjDescr(String projDescr) {this.projDescr = projDescr;}	
}
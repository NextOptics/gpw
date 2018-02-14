package gpw.model;

public class ContasBanc {
	private String nomeBanco;
	private int codBanco;
	private String agenBanco;
	private String contaNumero;
	private String tipoAgencia;

	private String titularConta;
	private String gerenteConta;
	private String agenCidade;
	private String agenBairro;
	private String agenRua;

	private String agenCep;
	private String agenTel;
	private String celGerente;
	private int contaValida;
	private int codConta;
	
	private String contaTipo;
	
	public ContasBanc() {
		this.nomeBanco = "";
		this.codBanco = 0;
		this.agenBanco = "";
		this.contaNumero = "";
		this.tipoAgencia = "";
		this.titularConta = "";
		this.gerenteConta = "";
		this.agenCidade = "";
		this.agenBairro = "";
		this.agenRua = "";
		this.agenCep = "";
		this.agenTel = "";
		this.celGerente = "";
		this.contaValida = 0 ;
		this.codConta = 0;
		this.contaTipo = "";
	}
	
	public ContasBanc( String nomeBanco, int codBanco, String agenBanco, String contaNumero,
					String tipoAgencia, String titularConta, String gerenteConta, String agenCidade,
					String agenBairro, String agenRua, String agenCep, String agenTel,
					String celGerente, int contaValida, int codConta, String contaTipo) {
						
		this.nomeBanco = nomeBanco;
		this.codBanco = codBanco;
		this.agenBanco = agenBanco;
		this.contaNumero = contaNumero;
		this.tipoAgencia = tipoAgencia;
		this.titularConta = titularConta;
		this.gerenteConta = gerenteConta;
		this.agenCidade = agenCidade;
		this.agenBairro = agenBairro;
		this.agenRua = agenRua;
		this.agenCep = agenCep;
		this.agenTel = agenTel;
		this.celGerente = celGerente;
		this.contaValida = contaValida;
		this.codConta = codConta;
		this.contaTipo = contaTipo;
						
	}
	

	public String getNomeBanco() {return this.nomeBanco;}
	public void setNomeBanco(String nomeBanco) { this.nomeBanco = nomeBanco;}
	
	public int getCodBanco() {return this.codBanco;}
	public void setCodBanco(int codBanco) {this.codBanco = codBanco;}
	
	public String getAgenBanco() {return this.agenBanco;}
	public void setAgenBanco(String agenBanco) {this.agenBanco = agenBanco;}
	
	public String getContaNumero() {return this.contaNumero;}
	public void setContaNumero(String contaNumero) {this.contaNumero = contaNumero;}
	
	public String getTipoAgencia() {return this.tipoAgencia;}
	public void setTipoAgencia(String tipoAgencia) {this.tipoAgencia = tipoAgencia;}
	
	public String getTitularConta() {return this.titularConta;}
	public void setTitularConta(String titularConta) {this.titularConta = titularConta;}
	
	public String getGerenteConta() {return this.gerenteConta;}
	public void setGerenteConta(String gerenteConta) {this.gerenteConta = gerenteConta;}
	
	public String getAgenCidade() {return this.agenCidade;}
	public void setAgenCidade(String agenCidade) {this.agenCidade = agenCidade;}
	
	public String getAgenBairro() {return this.agenBairro;}
	public void setAgenBairro(String agenBairro) {this.agenBairro = agenBairro;}
	
	public String getAgenRua() {return this.agenRua;}
	public void setAgenRua(String agenRua) {this.agenRua = agenRua;}
	
	public String getAgenCep() {return this.agenCep;}
	public void setAgenCep(String agenCep) {this.agenCep = agenCep;}
	
	public String getAgenTel() {return this.agenTel;}
	public void setAgenTel(String agenTel) {this.agenTel = agenTel;}
	
	public String getCelGerente() {return this.celGerente;}
	public void setCelGerente(String celGerente) {this.celGerente = celGerente;}
	
	public int getContaValida() {return this.contaValida;}
	public void setContaValida(int contaValida) {this.contaValida = contaValida;}
	
	public int getCodConta() {return this.codConta;}
	public void setCodConta(int codConta) {this.codConta = codConta;}
	
	public String getContaTipo() {return this.contaTipo;}
	public void setContaTipo(String contaTipo) {this.contaTipo = contaTipo;}
	
}


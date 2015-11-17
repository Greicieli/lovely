package modelo;

public class Produto {

	private int idProduto;
	private String descricao;
	private double valor;
	private int quantidade;
	private TipoDeProduto tipoDeProduto;
	
	public Produto(){
		tipoDeProduto = new TipoDeProduto();
	}

	public Produto(int idPtoduto, String descricao, double valor,
			int quantidade, TipoDeProduto idTipoDeProduto) {
		super();
		this.idProduto = idPtoduto;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.tipoDeProduto = idTipoDeProduto;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public TipoDeProduto getTipoDeProduto() {
		return tipoDeProduto;
	}

	public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}

	
}

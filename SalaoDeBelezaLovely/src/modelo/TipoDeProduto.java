package modelo;

public class TipoDeProduto {

	private int idTipoDeProduto;
	private String tipoDeProduto;
	
	public TipoDeProduto(){
	}

	public TipoDeProduto(int idTipoDeProduto, String tipoDeProduto) {
		super();
		this.idTipoDeProduto = idTipoDeProduto;
		this.tipoDeProduto = tipoDeProduto;
	}

	public int getIdTipoDeProduto() {
		return idTipoDeProduto;
	}

	public void setIdTipoDeProduto(int idTipoDeProduto) {
		this.idTipoDeProduto = idTipoDeProduto;
	}

	public String getTipoDeProduto() {
		return tipoDeProduto;
	}

	public void setTipoDeProduto(String tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}

	
	
	
}

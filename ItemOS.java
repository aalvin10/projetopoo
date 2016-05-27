import java.io.Serializable;
public class ItemOS implements Serializable {
	private char tipoItem;
	private Servico servico;
	private Peca peca;
	private double preco;
	private int qtde;
	
	ItemOS(Peca peca, int qtde){
		this.peca = peca;
		this.qtde = qtde;
		this.tipoItem = 'P';
		this.preco = peca.getPreco();
	}
	
	ItemOS(Servico servico){
		this.servico = servico;
		this.tipoItem = 'S';
		this.preco = servico.getPreco();
	}
	
	public char getTipoItem(){
		return this.tipoItem;
	}
	
	public double getPreco(){
		return this.preco;
	}
	
	public int getQtde(){
			return this.qtde;
	}
	
	public Peca getPeca() {
		return this.peca;
	}
	
	public Servico getServico() {
		return this.servico;
	}
	
	public void setQtd(int qtde) {
		this.qtde = qtde;
	}
}


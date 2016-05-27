import java.io.Serializable;
public class Peca extends Produto implements Serializable{
 private int codPeca;
 private String descricao;
 private double preco;
 private int qtdeEstoque;
 
	Peca(int codPeca, String descricao, double preco){
		this.codPeca = codPeca;
		this.descricao = descricao;
		this.preco = preco;
		this.qtdeEstoque = 0;
	}
 
	Peca(int codPeca, String descricao, double preco, int qtdeEstoque){
		this.codPeca = codPeca;
		this.descricao = descricao;
		this.preco = preco;
		this.qtdeEstoque = qtdeEstoque;
	}
 

 
	public int getQtdeEstoque(){
		return this.qtdeEstoque;
	}
 
	public void incrementaQtdeEstoque(int incremento){
		this.qtdeEstoque =  this.qtdeEstoque+incremento;
	}
 
	public void decrementaQtdeEstoque(int decremento) throws EIException{
		if(this.qtdeEstoque-decremento >= 0)
			this.qtdeEstoque =  getQtdeEstoque()-decremento;
		else
			throw new EIException();
		}
	
	public int getCod(){
		return this.codPeca;
	}
	public String getDescricao(){
		return this.descricao;
	}
	public void setPreco(Double preco){
		this.preco = preco;
	}
	public double getPreco(){
		return this.preco;
	}
}

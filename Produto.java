import java.io.Serializable;
abstract class Produto implements Serializable{
	protected int cod;
	protected String descricao;
	protected double preco;
	
	public abstract int getCod();
	public abstract String getDescricao();
	public abstract void setPreco(Double preco);
	public abstract double getPreco();
	
}

import java.io.Serializable;
public class Servico extends Produto implements Serializable{
	private int codServico;
	private String descricao;
	private double preco;
	private double tempoExecucao;
	
	Servico(int codServico, String descricao, double preco, double tempoExecucao){
		this.codServico = codServico;
		this.descricao = descricao;
		this.preco = preco;
		this.tempoExecucao = tempoExecucao;
	}
	double getTempoExecucao(){
		return this.tempoExecucao;
	}
	void setTempoExecucao(double tempoExecucao){
		this.tempoExecucao = tempoExecucao;
	}
	public int getCod(){
		return this.codServico;
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

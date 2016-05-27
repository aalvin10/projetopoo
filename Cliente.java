import java.io.Serializable;

public class Cliente implements Serializable{
	private String nome;
	private String cpf;
	private String endereco;
	private String fone;
	
	Cliente(String nome, String cpf, String endereco, String fone){
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.fone = fone;
	}
	
	String getNome(){
		return this.nome;
	}
	void setNome(String nome){
		this.nome = nome;
	}
	
	String getCpf(){
		return this.cpf;
	}
	void setCpf(String cpf){
		this.cpf = cpf;
	}
	
	String getEndereco(){
		return this.endereco;
	}
	void setEndereco(String endereco){
		this.endereco = endereco;
	}
	
	String getFone(){
		return this.fone;
	}
	void setFone(String fone){
		this.fone = fone;
	}
}

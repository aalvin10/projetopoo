import java.util.*;
import java.text.DateFormat;
import java.text.*;
import java.io.Serializable;

public class OrdemServico implements Serializable{
	private int numeroOS;
	private GregorianCalendar dataOS;
	private GregorianCalendar dataPrevTermino;
	private GregorianCalendar dataTermino;
	private String placaCarro;
	private char situacao;
	private Cliente usuario;
	private Vector <ItemOS> itens = new Vector<ItemOS>();
	static int proximoNumero = 0;
	
	OrdemServico(String placa, GregorianCalendar dataPrevTermino, Cliente usuario) {
		this.numeroOS = proximoNumero();
		this.dataOS = new GregorianCalendar();
		this.dataPrevTermino = dataPrevTermino;
		this.placaCarro = new String(placa);
		this.dataTermino = new GregorianCalendar(0,0,0);
		this.situacao = 'A';
		this.usuario = usuario;
	}
	
	OrdemServico(int numeroOS,GregorianCalendar DataOS,GregorianCalendar dataPrevTermino,
	GregorianCalendar dataTermino, String placa, char situacao,Cliente cliente, Vector<ItemOS> itens){
		this.numeroOS = numeroOS;
		this.dataOS = DataOS;
		this.dataPrevTermino = dataPrevTermino;
		this.placaCarro = new String(placa);
		this.dataTermino = dataTermino;
		this.situacao = situacao;
		this.usuario = cliente;
		this.itens = itens;
	}
		
	
	public int proximoNumero(){
		return ++this.proximoNumero;
	}
	
	public int getNumeroOS() {
		return numeroOS;	
	}
	
	public Date getDataOS() {
		return dataOS.getTime();
	}
	
	public void setDataPrevTermino(int dia, int mes, int ano){
		dataPrevTermino.add(GregorianCalendar.DATE, dia);
		dataPrevTermino.add(GregorianCalendar.MONTH, mes-1);
		dataPrevTermino.add(GregorianCalendar.YEAR, ano);	
	}
	
	public Date getDataPrevTermino() {	
		return dataPrevTermino.getTime();
	}
	
	public void setDataTermino() {
		dataTermino = new GregorianCalendar();
	}
	
	public Date getDataTermino(){
		return dataTermino.getTime();
	}
	
	public String getPlacaCarro() {
		return placaCarro;
	}
	
	public char getSituacao () {
		return situacao;
	}
	
	public void setSituacao (char processo) {
		situacao = processo;
	}
	
	public Cliente getCliente(){
		return this.usuario;
	}
	
	public Vector<ItemOS> getItens() {
		return itens;
	}
	
	
		
	public void setPecaAdicionar(Peca peca, int quantidade) throws EIException{
		if (situacao == 'A'){
			itens.add(new ItemOS(peca, quantidade));		
			peca.decrementaQtdeEstoque(quantidade);
		}
	}
	
	public void setPecaExcluir(Peca peca) {
		ItemOS aux;
		if (situacao == 'A'){
			for(int i=0; i<itens.size(); i++){
				aux = itens.get(i);
				if (aux.getTipoItem() == 'P'){
					if (peca.getCod() == aux.getPeca().getCod()){
						peca.incrementaQtdeEstoque(itens.get(i).getQtde());
						itens.remove(i);
					}
				}
			}
		}
	}
	
	public void setServicoAdicionar(Servico servico){ // O SERVIcO DEVE SER PASSADO COMO PARAMETRO
		if (situacao == 'A'){
			itens.add(new ItemOS(servico));
		}
	}
	
	public void setServicoExcluir(Servico servico){ 
		ItemOS aux;
		if (situacao == 'A'){
			for (int i=0;i<itens.size();i++){
				aux = itens.get(i);
				if (aux.getTipoItem() == 'S'){
					if (servico.getCod() == aux.getServico().getCod()){
						itens.remove(i);
					}
				}
			}
		}
	}
	public void finalizarOS () {
		if (situacao == 'A'){
			situacao = 'F';
			dataTermino = new GregorianCalendar();
		}
	}
	
	public void cancelarOS () {
		if (situacao == 'A') {
			situacao = 'C';
		}
		else {
			
		}
	}	
}


import java.util.*;
import java.util.Vector;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Oficina {
    private Vector <Cliente> vetorclientes;
    private Vector <Peca> vetorpeca;
    private Vector <Servico> vetorservico;
    private Vector <OrdemServico> vetorOS;
    
    Oficina(){
        vetorclientes = new Vector<Cliente>();
        vetorpeca = new Vector<Peca>();
        vetorservico = new Vector<Servico>();
        vetorOS = new Vector<OrdemServico>();
    }
    
    public Vector<Peca> getVectorPeca(){
		return this.vetorpeca;
	}
	public Vector<Cliente> getVectorClientes(){
		return this.vetorclientes;
	}
	public Vector<Servico> getVectorServico(){
		return this.vetorservico;
	}
	public Vector<OrdemServico> getVectorOS(){
		return this.vetorOS;
	} 
    
    public void inserirCliente(Cliente cliente){
		boolean saber = false;
		for (int i=0; i<vetorclientes.size(); i++){
			if (cliente.getCpf().equals(vetorclientes.get(i).getCpf())){
				saber = true;
			}
		}
		if (saber == false){
			boolean certo = vetorclientes.add(cliente);
		}else{
			System.out.println("Erro! Cpf já cadastrado no banco de dados!");
		}		
	}
    public void inserirPeca (Peca peca){
        boolean saber = false;
		for (int i=0; i<vetorpeca.size(); i++){
			if (vetorpeca.get(i).getCod() == peca.getCod()){
				saber = true;
			}
		}
		if (saber == false){
			boolean certo = vetorpeca.add(peca);
		}else{
			System.out.println("Erro! Peca já cadastrada no banco de dados!");
		}  
	}
 
	public void inserirServico (Servico servico){
	boolean saber = false;
		for (int i=0; i<vetorservico.size(); i++){
			if (vetorservico.get(i).getCod() == servico.getCod()){
				saber = true;
			}
		}
		if (saber == false){
			boolean certo = vetorservico.add(servico);
		}else{
			System.out.println("Erro! Peca já cadastrada no banco de dados!");
		}  
    }
	public void inserirOS (OrdemServico ordem){
		boolean saber = false;
		for (int i=0; i<vetorOS.size(); i++){
			if (vetorOS.get(i).getNumeroOS() == ordem.getNumeroOS()){
				saber = true;
			}
		}
		if (saber == false){
		boolean certo= vetorOS.add(ordem);   
		}else{
			System.out.println("Erro! Ordem de Serviço já cadastrada no banco de dados!");
		}  
	}
    public void inserirItemOS (OrdemServico ordem, Peca peca, int qtd) throws EIException{
        boolean tem = vetorOS.contains(ordem);
        if (tem){
            int indice = vetorOS.indexOf(ordem);
            vetorOS.removeElementAt(indice);
        }
        ordem.setPecaAdicionar(peca,qtd);
        vetorOS.add(ordem);
    }
    
    public void inserirSevicoOrdemServico(OrdemServico ordem, Servico servico){
        boolean tem = vetorOS.contains(ordem);
        if (tem){
            int indice = vetorOS.indexOf(ordem);
            vetorOS.removeElementAt(indice);
        }
        ordem.setServicoAdicionar(servico);
        vetorOS.add(ordem);
    }
    public void removerCliente(Cliente cliente){
		OrdemServico servico;
        boolean tem = vetorclientes.contains(cliente);
        if (tem){
            boolean tem2 = true;
            for (int i=0;i<vetorOS.size();i++){
                servico=vetorOS.get(i);
                if (servico.getCliente().getCpf().equals(cliente.getCpf())){
                    tem2 = false;
                }
            }
            if (tem2){
                int indice = vetorclientes.indexOf(cliente);
                vetorclientes.removeElementAt(indice);
                System.out.println("Cliente removido com sucesso");
            }
            else{
                System.out.println("Cliente possui ordem de serviço");
            }
        }
        else{
            System.out.println("Cliente não encontrado");
        }
    }
    // metodo para remover a peça de uma ordem de serviço, mas se alguma ordem de servico estiver utilizando essa
	// peça, ela nao pode ser removida.. 
    public void removerPeca(Peca peca){
        boolean tem = vetorpeca.contains(peca);
        if (tem){
            boolean tem2 = false;
            int tam = vetorOS.size();
            OrdemServico servico;
            for (int i=0;i<tam;i++){
                servico=vetorOS.get(i);
    for (int j=0;j<servico.getItens().size(); j++){
     if (servico.getItens().get(j).getPeca().getCod()==peca.getCod()){
      tem2 = true;
     }
    }
            }
            if (tem2){
                int indice = vetorpeca.indexOf(peca);
                vetorpeca.removeElementAt(indice);
                System.out.println("Peça removido com sucesso");
            }
            else{
                System.out.println("Peça possui ordem de serviço");
            }
        }
        else{
            System.out.println("Peça não encontrado");
        }
    }

    public void removerServico(Servico servico){
        boolean tem = vetorservico.contains(servico);
        if (tem){
            boolean tem2 = true;
            for (int i=0;i<vetorOS.size();i++){
                OrdemServico OS =vetorOS.get(i);
                for (int j=0;j<OS.getItens().size();j++){
					if (OS.getItens().get(j).getServico()==servico){
						tem2 = false;
					}
				}
            }
            if (tem2){
                int indice = vetorservico.indexOf(servico);
                vetorservico.removeElementAt(indice);
                System.out.println("Serviço removido com sucesso");
            }
            else{
                System.out.println("Serviço possui ordem de serviço");
            }
        }
        else{
            System.out.println("Serviço não encontrado");
        }
    }
    public void removerOS (OrdemServico oss){
        boolean tem = vetorOS.contains(oss);
        if (tem){
            int pos = vetorOS.indexOf(oss);
            OrdemServico oss2 = vetorOS.get(pos);
            if (oss2.getSituacao()=='A'){
                vetorOS.remove(pos);
                System.out.println("Ordem de serviço removida");
            }
            else{
                System.out.println("Impossivel remover ordem de serviço, situação invalida");
            }
        }
        else{
            System.out.println("Ordem de serviço não encotrada");
        }
    }
    public void removerPecaOS(Peca peca, OrdemServico ordem){
        boolean tem = vetorOS.contains(ordem);
        if (tem){
            int pos = vetorOS.indexOf(ordem);
            OrdemServico ordem2 = vetorOS.get(pos);
            vetorOS.remove(pos);
            ordem2.setPecaExcluir(peca);
            vetorOS.add(ordem2);
            System.out.println("Peça removida com sucesso");
        }
        else{
            System.out.println("Ordem de serviço não encontrada");
        }
    }
    public void removerServicoOS(Servico servico, OrdemServico ordem){
        boolean tem = vetorOS.contains(ordem);
        if (tem){
            int pos = vetorOS.indexOf(ordem);
            OrdemServico ordem2 = vetorOS.get(pos);
            vetorOS.remove(pos);
            ordem2.setServicoExcluir(servico);
            vetorOS.add(ordem2);
            System.out.println("Serviço removida com sucesso");
        }
        else{
            System.out.println("Ordem de serviço não encontrada");
        }
    }
    public void finalizarOrdemServico(OrdemServico ordem){
        boolean tem = vetorOS.contains(ordem);
        if (tem){
            int pos = vetorOS.indexOf(ordem);
            OrdemServico ordem2 = vetorOS.get(pos);
            vetorOS.remove(pos);
            ordem2.finalizarOS();
            vetorOS.add(ordem2);
            System.out.println("Ordem se servico fializada com sucesso");
        }
        else{
            System.out.println("Ordem de serviço não encontrada");
        }
    }
    // Cancelar OS e retorna para o estoque a quantidade de peças
    public void cancelarOS(OrdemServico ordem){
        boolean tem = vetorOS.contains(ordem);
        if (tem){
            int pos = vetorOS.indexOf(ordem);
            OrdemServico ordem2 = vetorOS.get(pos);
            vetorOS.remove(pos);
            Vector <ItemOS> aux = new Vector<ItemOS>();
            aux = ordem2.getItens();
            for (int i=0; i<aux.size(); i++){
				if (aux.get(i).getTipoItem()=='P'){
					aux.get(i).getPeca().incrementaQtdeEstoque(aux.get(i).getQtde());
					aux.get(i).setQtd(0);
				}
			}
			ordem2.cancelarOS();
            vetorOS.add(ordem2);
            System.out.println("Ordem se servico cancelada com sucesso");
        }
        else{
            System.out.println("Ordem de serviço não encontrada");
        }
    }
    public void listarServico(){
        int tam = vetorservico.size();
        for (int i=0;i<tam;i++){
            Servico servico = vetorservico.get(i);
            System.out.println("Codigo do serviço: " + servico.getCod());
            System.out.println("Descrição: " + servico.getDescricao());
            System.out.println("Preço: " + servico.getPreco());
            System.out.println("Tempo de execução: " + servico.getTempoExecucao());
        }
    }
    public void ListarOrdemServico(){
        int tam = vetorOS.size();
        for (int i=0;i<tam;i++){
            OrdemServico ordem = vetorOS.get(i);
            System.out.println("Numero da ordem de serviço: " + ordem.getNumeroOS());
            System.out.println("Data: " + ordem.getDataOS());
            System.out.println("Data previsto para termino: " + ordem.getDataPrevTermino());
            System.out.println("Data de termino: " + ordem.getDataTermino());
            System.out.println("Placa do carro: " + ordem.getPlacaCarro());
            System.out.println("Situação do pedido: " + ordem.getSituacao());
            System.out.println("Nome do cliente: " + ordem.getCliente().getNome());
            System.out.println("Cpf do cliente: " + ordem.getCliente().getCpf());
            System.out.println("Endereço do cliente:" + ordem.getCliente().getEndereco());
            System.out.println("Telefone do cliente" + ordem.getCliente().getFone());
            for (int j=0;j<ordem.getItens().size(); j++){
				if (ordem.getItens().get(j).getTipoItem() == 'P'){
					System.out.println("Peças" + ordem.getItens().get(j));
				}
			}
        }
    }
   	
    public void lerDados(){
        //clientes
		try

        {
            //Carrega o arquivo
            FileInputStream arquivoLeituraClientes = new FileInputStream("cliente.dat");
            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeituraClientes  = new ObjectInputStream(arquivoLeituraClientes);
			vetorclientes = (Vector <Cliente>)objLeituraClientes.readObject();
            objLeituraClientes.close();
            arquivoLeituraClientes.close();

        }

        catch( Exception e ){

                e.printStackTrace( );

        }

        //peça
        
        try

        {
            //Carrega o arquivo
            FileInputStream arquivoLeituraPecas = new FileInputStream("peca.dat");
            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeituraPecas  = new ObjectInputStream(arquivoLeituraPecas);
			vetorpeca = (Vector <Peca>)objLeituraPecas.readObject();
            objLeituraPecas.close();
            arquivoLeituraPecas.close();

        }

        catch( Exception e ){

                e.printStackTrace( );

        }

        //serciço
		 try

        {
            //Carrega o arquivo
            FileInputStream arquivoLeituraServicos = new FileInputStream("servico.dat");
            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeituraServicos = new ObjectInputStream(arquivoLeituraServicos);
			vetorservico = (Vector <Servico>)objLeituraServicos.readObject();
            objLeituraServicos.close();
            arquivoLeituraServicos.close();

        }

        catch( Exception e ){

                e.printStackTrace( );

        }
    // Ordem se serviço
		 try

        {
            //Carrega o arquivo
            FileInputStream arquivoLeituraOS = new FileInputStream("OS.dat");
            //Classe responsavel por recuperar os objetos do arquivo
            ObjectInputStream objLeituraOS= new ObjectInputStream(arquivoLeituraOS);
			vetorOS = (Vector <OrdemServico>)objLeituraOS.readObject();
            objLeituraOS.close();
            arquivoLeituraOS.close();

        }

        catch( Exception e ){

                e.printStackTrace( );

        }
    }
    
    public void escreveArquivo(){
        //cliente
        
           //Gera o arquivo para armazenar o objeto
		  try{
            FileOutputStream arqGravCliente =new FileOutputStream("cliente.dat");

            //Responsavel por inserir os objetos
            ObjectOutputStream objGravarCliente = new ObjectOutputStream(arqGravCliente);

            //Grava o objeto cliente no arquivo
            objGravarCliente.writeObject(vetorclientes);
            objGravarCliente.flush();
            objGravarCliente.close();
            arqGravCliente.flush();
            arqGravCliente.close();
            System.out.println("Objeto gravado com sucesso!");
		}
        catch( Exception e ){

                e.printStackTrace( );

        }
        //peça
        
         try{
            FileOutputStream arqGravPeca =new FileOutputStream("peca.dat");

            //Responsavel por inserir os objetos
            ObjectOutputStream objGravarPeca = new ObjectOutputStream(arqGravPeca);

            //Grava o objeto cliente no arquivo
            objGravarPeca.writeObject(vetorpeca);
            objGravarPeca.flush();
            objGravarPeca.close();
            arqGravPeca.flush();
            arqGravPeca.close();
            System.out.println("Objeto gravado com sucesso!");
		}
        catch( Exception e ){

                e.printStackTrace( );

        }
        
        //servico
        
        try{
            FileOutputStream arqGravServico =new FileOutputStream("servico.dat");

            //Responsavel por inserir os objetos
            ObjectOutputStream objGravarServico = new ObjectOutputStream(arqGravServico);

            //Grava o objeto cliente no arquivo
            objGravarServico.writeObject(vetorservico);
            objGravarServico.flush();
            objGravarServico.close();
            arqGravServico.flush();
            arqGravServico.close();
            System.out.println("Objeto gravado com sucesso!");
		}
        catch( Exception e ){

                e.printStackTrace( );

        }
        
        //Ordem servico
        
         try{
            FileOutputStream arqGravOS =new FileOutputStream("OS.dat");

            //Responsavel por inserir os objetos
            ObjectOutputStream objGravarOS = new ObjectOutputStream(arqGravOS);

            //Grava o objeto cliente no arquivo
            objGravarOS.writeObject(vetorOS);
            objGravarOS.flush();
            objGravarOS.close();
            arqGravOS.flush();
            arqGravOS.close();
            System.out.println("Objeto gravado com sucesso!");
		}
        catch( Exception e ){

                e.printStackTrace( );

        }
        
	}
}

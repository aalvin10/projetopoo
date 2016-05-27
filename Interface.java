import java.util.Scanner;
import java.util.*;

class Interface
{
    public static Scanner leia = new Scanner(System.in);

    public static void cadastraCliente(Oficina oficina)
    {
        String nome, cpf, endereco, fone;
        leia.nextLine();
        System.out.println("Digite o nome do cliente: ");
        nome = leia.nextLine();
        System.out.println("Digite o cpf do cliente: ");
        cpf = leia.nextLine();
        System.out.println("Digite o endereço do cliente: ");
        endereco = leia.nextLine();
        System.out.println("Digite o telefone do cliente: ");
        fone = leia.nextLine();
        /*Cadastra o cliente*/
        oficina.inserirCliente(new Cliente(nome,cpf,endereco,fone));
    }

    public static void cadastraPeca(Oficina oficina)
    {
        //Peca(int codPeca, String descricao, double preco)
        int codPeca;
        String descricao;
        double preco;
        int quantidade;
        System.out.println("Digite o codigo da peca: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado.. 
        codPeca = leia.nextInt(); 
        leia.nextLine();
        System.out.println("Digite a descricao da peca: ");
        descricao = leia.nextLine();
        System.out.println("Digite o preco da peça: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado.. 
        preco = leia.nextDouble();
        leia.nextLine();
        System.out.println("Digite a quantidade: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado.. 
        quantidade = leia.nextInt();
        /*Cadastra peca*/
        oficina.inserirPeca(new Peca(codPeca,descricao,preco,quantidade));
    }

    public static void cadastraServico(Oficina oficina)
    {
        //Servico(int codServico, String descricao, double preco, double tempoExecucao)
        int codServico;
        String descricao;
        double preco, tempoExecucao;

        System.out.println("Digite o código do serviço: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        codServico = leia.nextInt();
        System.out.println("Digite a descricao do serviço: ");
        leia.nextLine();
        descricao = leia.nextLine();
        System.out.println("Digite o preco do servico: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        preco = leia.nextDouble();
        leia.nextLine();
        System.out.println("Digite o tempo de execução do serviço");
        // Se digitar uma string aqui vai dar errado..
        tempoExecucao = leia.nextDouble();
        /*Cadastra servico*/
        oficina.inserirServico(new Servico(codServico,descricao,preco,tempoExecucao));

	}
	
    public static void cadastraOrdemServico(Oficina oficina)
    {
        String placa;
        GregorianCalendar dataPrevTermino;
        int d,m,a;

        Vector <Cliente> vetcliente = new Vector<Cliente>();

        leia.nextLine();
        System.out.println("Digite a placa do carro:");
        placa = leia.nextLine();

        System.out.println("Digite a data da previsão de termino (dd mm aaaa):");
        // numero muito grande da erro tbm java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        d = leia.nextInt();
        m = leia.nextInt();
        a = leia.nextInt();
        dataPrevTermino = new GregorianCalendar(a,m-1,d);
    

        Cliente cliente = null;
        String cpf;
        leia.nextLine();
        System.out.println("Digite o cpf do cliente:");
        cpf = leia.nextLine();
        vetcliente = oficina.getVectorClientes();/*Recebe o vetor de clientes*/

        for(int i = 0; i < vetcliente.size(); i++)
        {
            if(vetcliente.get(i).getCpf().equals(cpf)) /*Compara o cpf com a lista de clientes*/
            {
                cliente = vetcliente.get(i);
            }
        }
        /*Pega a ultima posiçã do vetor que será o numero sequencial da OS*/
        int numOS;
        if(oficina.getVectorOS().size() > 0)
			numOS = oficina.getVectorOS().get(oficina.getVectorOS().size() - 1).getNumeroOS() + 1;
		else
			numOS = 0;
        /*Tem que fazer um tratamento de excessão caso o cliente não exista*/
        oficina.inserirOS(new OrdemServico(placa,dataPrevTermino,cliente, numOS));
    }

    public static void inserirPecaOS(Oficina oficina) throws EIException
    {
        Vector<Peca> vetorPeca = new Vector<Peca>();
        Peca peca = null;
        System.out.println("Digite o codigo da peca: ");
        // Se digitar uma string aqui vai dar errado.. java.util.InputMismatchException
        // Se o codigo for um numero muito grande vai dar errado

        int cod = leia.nextInt();
        vetorPeca = oficina.getVectorPeca();
        for (int i=0; i<vetorPeca.size(); i++)
        {
            if (vetorPeca.get(i).getCod() == cod)
            {
                peca = vetorPeca.get(i);
            }
        }
        
        // Se o numero for muito grande da errado, mas se for um numero pequeno nao.. java.util.InputMismatchException
        System.out.println("Digite o numero da ordem de serviço: ");
        Vector<OrdemServico> vetorServico = new Vector<OrdemServico>();
        OrdemServico OS = null;
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        int numOS = leia.nextInt();
        vetorServico = oficina.getVectorOS();
        for (int i=0; i<vetorServico.size(); i++)
        {
            if (vetorServico.get(i).getNumeroOS() == numOS)
            {
                OS = vetorServico.get(i);
            }
        }
        System.out.println("Digite a quantidade: ");
        // Se for um numero muito grande vai dar errado java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        int quantidade = leia.nextInt();
        oficina.inserirItemOS(OS, peca, quantidade);
    }

    public static void excluirServico(Oficina oficina)
    {
        Vector<Servico> vetorS = new Vector<Servico>();
        Servico var_servico = null;
        int cod_s;
        System.out.println("Digite o codigo do serviço: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        cod_s = leia.nextInt();
        vetorS = oficina.getVectorServico();
        for(int i = 0; i < vetorS.size(); i++)
        {
            if(vetorS.get(i).getCod() == cod_s)
                var_servico = vetorS.get(i);
        }
        oficina.removerServico(var_servico);
    }

    public static void excluirOS(Oficina oficina)
    {
        System.out.println("Digite o numero da ordem de serviço: ");
        Vector<OrdemServico> vetorOS = new Vector<OrdemServico>();
        OrdemServico OS = null;
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        int numOS = leia.nextInt();
        vetorOS = oficina.getVectorOS();
        for (int i=0; i<vetorOS.size(); i++)
        {
            if (vetorOS.get(i).getNumeroOS() == numOS)
            {
                OS = vetorOS.get(i);
            }
        }
        oficina.removerOS(OS);
    }

    public static void excluirPecaOS(Oficina oficina)
    {
        System.out.println("Digite o numero da ordem de serviço: ");
        Vector<OrdemServico> vetorOS = new Vector<OrdemServico>();
        OrdemServico OS = null;
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        int numOS = leia.nextInt();
        vetorOS = oficina.getVectorOS();
        for (int i=0; i<vetorOS.size(); i++)
        {
            if (vetorOS.get(i).getNumeroOS() == numOS)
            {
                OS = vetorOS.get(i);
            }
        }
        Vector<Peca> vetorPeca = new Vector<Peca>();
        Peca peca = null;
        System.out.println("Digite o codigo da peca: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        int cod = leia.nextInt();
        vetorPeca = oficina.getVectorPeca();
        for (int i=0; i<vetorPeca.size(); i++)
        {
            if (vetorPeca.get(i).getCod() == cod)
            {
                peca = vetorPeca.get(i);
            }
        }
        oficina.removerPecaOS(peca, OS);
    }

    public static void listarPecas(Oficina oficina)
    {
        for (int i=0; i<oficina.getVectorPeca().size(); i++)
        {
            System.out.println(oficina.getVectorPeca().get(i).getCod() + "   " + oficina.getVectorPeca().get(i).getDescricao() + "  " +
                               oficina.getVectorPeca().get(i).getPreco() + "  " + oficina.getVectorPeca().get(i).getQtdeEstoque());
        }
    }

    public static void listarServico(Oficina oficina)
    {
        for (int i=0; i<oficina.getVectorServico().size(); i++)
        {
            System.out.println(oficina.getVectorServico().get(i).getCod() + "  " + oficina.getVectorServico().get(i).getDescricao() + "  " + oficina.getVectorServico().get(i).getPreco() + "  " +
                               oficina.getVectorServico().get(i).getTempoExecucao());
        }
    }

    public static void inserirServicoOS(Oficina oficina)
    {
        Vector<Servico> vetorS = new Vector<Servico>();
        Servico var_servico = null;
        int cod_s;
        System.out.println("Digite o codigo do serviço: ");
        // se for um numero muito grande da erro tbm, deve ser que passa dos limites. :: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        cod_s = leia.nextInt();
        vetorS = oficina.getVectorServico();
        for(int i = 0; i < vetorS.size(); i++)
        {
            if(vetorS.get(i).getCod() == cod_s)
                var_servico = vetorS.get(i);
        }

        Vector <OrdemServico> vetorOrdServ = new Vector <OrdemServico>();
        OrdemServico var_OS = null;
        int cod_OS;
        System.out.println("Digite o numero da ordem de serviço: ");
        // numero muito grande da erro   ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        cod_OS = leia.nextInt();
        vetorOrdServ = oficina.getVectorOS();
        for(int i = 0; i < vetorOrdServ.size(); i++)
        {
            if(vetorOrdServ.get(i).getNumeroOS() == cod_OS)
                var_OS = vetorOrdServ.get(i);
        }

        oficina.inserirSevicoOrdemServico(var_OS,var_servico);
    }

    public static void excluirCliente(Oficina oficina)
    {
        Vector <Cliente> vetcliente = new Vector<Cliente>();
        Cliente cliente = null;
        String cpf;
        leia.nextLine();
        System.out.println("Digite o cpf do cliente:");
        cpf = leia.nextLine();
        vetcliente = oficina.getVectorClientes();/*Recebe o vetor de clientes*/

        for(int i = 0; i < vetcliente.size(); i++)
        {
            if(vetcliente.get(i).getCpf().equals(cpf)) /*Compara o cpf com a lista de clientes*/
            {
                cliente = vetcliente.get(i);
            }
            System.out.println("Cpf: " + vetcliente.get(i).getCpf());
        }

        oficina.removerCliente(cliente);
    }

    public static void excluirPeca(Oficina oficina)throws EIException
    {
        Vector<Peca> vetorPeca = new Vector<Peca>();
        vetorPeca = oficina.getVectorPeca();
        Peca peca = null;
        System.out.println("Digite o codigo da peca: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        int cod = leia.nextInt();
        vetorPeca = oficina.getVectorPeca();
        for (int i=0; i<vetorPeca.size(); i++)
        {
            if (vetorPeca.get(i).getCod() == cod)
            {
                peca = vetorPeca.get(i);
            }
        }
		//System.out.println("Peca: "+peca.getCod());
        oficina.removerPeca(peca);
    }

    public static void ListarOrdemServico(Oficina od)
    {
        for (int i=0; i<od.getVectorOS().size(); i++)
        {
            System.out.println(od.getVectorOS().get(i).getNumeroOS() + "  " +
                               od.getVectorOS().get(i).getDataOS() + "  " +
                               od.getVectorOS().get(i).getDataPrevTermino() + "  " +
                               od.getVectorOS().get(i).getDataTermino()+ "  " +
                               od.getVectorOS().get(i).getPlacaCarro()+ "  " +
                               od.getVectorOS().get(i).getSituacao()+ "  " +
                               od.getVectorOS().get(i).getCliente().getNome()+ "  " +
                               od.getVectorOS().get(i).getCliente().getCpf()+ "  " +
                               od.getVectorOS().get(i).getCliente().getEndereco()+ "  " +
                               od.getVectorOS().get(i).getCliente().getFone());
        }
    }

    public static void listarOS(Oficina oficina)
    {
        for (int i=0; i<oficina.getVectorOS().size(); i++)
        {
            System.out.println("Numero OS: " + oficina.getVectorOS().get(i).getNumeroOS() + "\nData OS: " +
                               oficina.getVectorOS().get(i).getDataOS() + "\nData Prevista de Termino: " +
                               oficina.getVectorOS().get(i).getDataPrevTermino() + "\nData Termino: " +
                               oficina.getVectorOS().get(i).getDataTermino()+ "\nPlaca Carro: " +
                               oficina.getVectorOS().get(i).getPlacaCarro()+ "\nSituação: " +
                               oficina.getVectorOS().get(i).getSituacao()+ "\nCliente: " +
                               oficina.getVectorOS().get(i).getCliente().getNome()+ "\nCPF: " +
                               oficina.getVectorOS().get(i).getCliente().getCpf()+ "\nEndereço: " +
                               oficina.getVectorOS().get(i).getCliente().getEndereco()+ "\nTelefone: " +
                               oficina.getVectorOS().get(i).getCliente().getFone());

            for (int j=0; j<oficina.getVectorOS().get(i).getItens().size(); j++)
            {
                if (oficina.getVectorOS().get(i).getItens().get(j).getTipoItem() == 'P')
                {
                    System.out.println("Situação: " + oficina.getVectorOS().get(i).getItens().get(j).getTipoItem() + "\nDescrição: " +
                                       oficina.getVectorOS().get(i).getItens().get(j).getProduto().getDescricao() + "\nPreço: " +
                                       oficina.getVectorOS().get(i).getItens().get(j).getPreco() + "\nQuantidade: " +
                                       oficina.getVectorOS().get(i).getItens().get(j).getQtde());
                }
                if (oficina.getVectorOS().get(i).getItens().get(j).getTipoItem() == 'S')
                {
                    System.out.println("Situação: " + oficina.getVectorOS().get(i).getItens().get(j).getTipoItem() + "\nDescrição: " +
                                       oficina.getVectorOS().get(i).getItens().get(j).getProduto().getDescricao() + "\nPreço:  " +
                                       oficina.getVectorOS().get(i).getItens().get(j).getPreco());
                }

            }
        }
    }

    public static void listarCliente(Oficina oficina)
    {
        for (int i=0; i<oficina.getVectorClientes().size(); i++)
        {
            System.out.println(oficina.getVectorClientes().get(i).getNome() + "  " + oficina.getVectorClientes().get(i).getCpf() +"  "+ oficina.getVectorClientes().get(i).getEndereco() +"  "+oficina.getVectorClientes().get(i).getFone());
        }

    }

    public static void exluirServicoOrdemServico(Oficina oficina)
    {

        Vector<Servico> vetorS = new Vector<Servico>();
        Servico var_servico = null;
        int cod_s;
        System.out.println("Digite o codigo do serviço: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        cod_s = leia.nextInt();
        vetorS = oficina.getVectorServico();
        for(int i = 0; i < vetorS.size(); i++)
        {
            if(vetorS.get(i).getCod() == cod_s)
                var_servico = vetorS.get(i);
        }


        Vector <OrdemServico> vetorOrdServ = new Vector <OrdemServico>();
        OrdemServico var_OS = null;
        int cod_OS;
        System.out.println("Digite o numero da ordem de serviço: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        cod_OS = leia.nextInt();
        vetorOrdServ = oficina.getVectorOS();
        for(int i = 0; i < vetorOrdServ.size(); i++)
        {
            if(vetorOrdServ.get(i).getNumeroOS() == cod_OS)
                var_OS = vetorOrdServ.get(i);
        }
        oficina.removerServicoOS(var_servico,var_OS);

    }
    public static void finalizarOrdemServico(Oficina oficina)
    {
        Vector <OrdemServico> vetorOrdServ = new Vector <OrdemServico>();
        OrdemServico var_OS = null;
        int cod_OS;
        System.out.println("Digite o numero da ordem de serviço: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        cod_OS = leia.nextInt();
        vetorOrdServ = oficina.getVectorOS();
        for(int i = 0; i < vetorOrdServ.size(); i++)
        {
            if(vetorOrdServ.get(i).getNumeroOS() == cod_OS)
                var_OS = vetorOrdServ.get(i);
        }


        oficina.finalizarOrdemServico(var_OS);
    }
    public static void cancelarOrdemServico(Oficina oficina)
    {

        Vector <OrdemServico> vetorOrdServ = new Vector <OrdemServico>();
        OrdemServico var_OS = null;
        int cod_OS;
        System.out.println("Digite o numero da ordem de serviço: ");
        // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
        // Se digitar uma string aqui vai dar errado..
        cod_OS = leia.nextInt();
        vetorOrdServ = oficina.getVectorOS();
        for(int i = 0; i < vetorOrdServ.size(); i++)
        {
            if(vetorOrdServ.get(i).getNumeroOS() == cod_OS)
                var_OS = vetorOrdServ.get(i);
        }

        oficina.cancelarOS(var_OS);
    }

    public static void menu(Oficina oficina)
    {
        int op;
        do
        {
            do
            {
                System.out.println("Menu:");
                System.out.println("01 - Cadastrar novo cliente");
                System.out.println("02 - Cadastrar nova peça");
                System.out.println("03 - Cadastrar novo serviço");
                System.out.println("04 - Cadastrar nova ordem de serviço");
                System.out.println("05 - Inserir novo item (Peça) na Ordem de Serviço");
                System.out.println("06 - Inserir novo item (Serivço) na Ordem de Serviço");
                System.out.println("07 - Excluir um cliente");
                System.out.println("08 - Excluir uma  peça");
                System.out.println("09 - Excluir um serviço");
                System.out.println("10 - Excluir uma ordem de serviço");
                System.out.println("11 - Excluir um item (peça) da Ordem de Serviço");
                System.out.println("12 - Excluir um  item (serviço) da Ordem de Serviço");
                System.out.println("13 - Finalizar uma Ordem de Serviço");
                System.out.println("14 - Cancelar uma Ordem de Serviço");
                System.out.println("15 - Listar todos os clientes");
                System.out.println("16 - Listar todas as peças");
                System.out.println("17 - Listar todos os serviços");
                System.out.println("18 - Listar todas as ordens de serviço e seus itens");
                System.out.println("19 - Sair do programa");
                // se for um numero muito grande vai dar erro ::: java.util.InputMismatchException
                // Se digitar uma string aqui vai dar errado..
                op = leia.nextInt();
            }
            while(op < 1 && op > 19);
            try
            {
                if(op == 1)
                    cadastraCliente(oficina);
                if(op == 2)
                    cadastraPeca(oficina);
                if(op == 3)
                    cadastraServico(oficina);
                if(op == 4)
                    cadastraOrdemServico(oficina);
                if(op == 5)
                    inserirPecaOS(oficina);
                if(op == 6)
                    inserirServicoOS(oficina);
                if (op == 7)
                    excluirCliente(oficina);
                if (op == 8)
                    excluirPeca(oficina);
                if (op == 9)
                    excluirServico(oficina);
                if (op == 10)
                    excluirOS(oficina);
                if (op == 11)
                    excluirPecaOS(oficina);
                 if(op == 12)
					exluirServicoOrdemServico(oficina);
				if(op == 13)
					finalizarOrdemServico(oficina);
				if(op == 14)
					cancelarOrdemServico(oficina);
                if (op == 15)
                    listarCliente(oficina);
                if (op == 16)
                    listarPecas(oficina);
                if (op == 17)
                    listarServico(oficina);
                if (op == 18)
                    listarOS(oficina);
            }
            catch(EIException e)
            {
                // e.getMessage();
                System.out.println("Erroooooooooooooooo");
            }
        }
        while(op != 19);
    }


    public static void main (String args[]){
        Oficina oficina = new Oficina();
        oficina.lerDados();
        menu(oficina);
        oficina.escreveArquivo();
    }
}

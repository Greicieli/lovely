package dao;

public class DAOFactory {
	
	private static ClienteDao clienteDao;
	
	public static ClienteDao getClienteDao(){
		if(clienteDao ==null){
			clienteDao = new ClienteDaoImp();
		}
		
		return clienteDao;
	}
	
	private static FuncionarioDao funcionarioDao;
	
	public static FuncionarioDao getFuncionarioDao(){
		if(funcionarioDao ==null){
			funcionarioDao = new FuncionarioDaoImp();
		}
		
		return funcionarioDao;
	}

	private static TipoDeProdutoDao tipoDeProdutoDao;
	
	public static TipoDeProdutoDao getTipoDeProdutoDao() {
		if(tipoDeProdutoDao == null){
			tipoDeProdutoDao = new TipoDeProdutoDaoImp();
		}
		return tipoDeProdutoDao;
	}

	private static ProdutoDao produtoDao;
	
	public static ProdutoDao getProdutoDao() {
		if(produtoDao == null){
			produtoDao = new ProdutoDaoImp();
		}
		return produtoDao;
	}
	
private static AgendaDao agendaDao;
	
	public static AgendaDao getAgendaDao() {
		if(agendaDao == null){
			agendaDao = new AgendaDaoImp();
		}
		return agendaDao;
	}
	
}

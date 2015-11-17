package controle;

import java.util.List;

import modelo.Produto;
import dao.DAOFactory;
import dao.ProdutoDao;

public class ProdutoControle {

	public void salvar(Produto produto) throws Exception{
		if(produto.getDescricao().trim().isEmpty()){
			throw new Exception("A decrição é obrigatório!");
		}
		
		ProdutoDao produtoDao = DAOFactory.getProdutoDao();
		if (produto.getIdProduto() == 0) {
			produtoDao.salvar(produto);
		} else {
			produtoDao.alterar(produto);
		}
		
	}
	
	public void excluir(Produto produto){
		ProdutoDao produtoDao = DAOFactory.getProdutoDao();
		produtoDao.excluir(produto);
	}
	public List<Produto> listarTodos() {
		ProdutoDao produtoDao = DAOFactory.getProdutoDao();
		return produtoDao.listarTodos();
	}

}

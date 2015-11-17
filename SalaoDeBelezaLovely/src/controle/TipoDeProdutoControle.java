package controle;

import java.util.List;

import dao.DAOFactory;
import dao.TipoDeProdutoDao;
import modelo.TipoDeProduto;

public class TipoDeProdutoControle {

	public void salvar(TipoDeProduto tipoDeProduto) throws Exception {
		if (tipoDeProduto.getTipoDeProduto() == null || tipoDeProduto.getTipoDeProduto().trim().isEmpty()) {
			throw new Exception("O tipo do produto é obrigatório!");
		}

		TipoDeProdutoDao tipoDeProdutoDao = DAOFactory.getTipoDeProdutoDao();
		if (tipoDeProduto.getIdTipoDeProduto() == 0) {
			tipoDeProdutoDao.salvar(tipoDeProduto);
		} else {
			tipoDeProdutoDao.alterar(tipoDeProduto);
		}
	}

	public void excluir(TipoDeProduto tipoDeProduto) {
		TipoDeProdutoDao tipoDeProdutoDao = DAOFactory.getTipoDeProdutoDao();
		tipoDeProdutoDao.excluir(tipoDeProduto);
	}

	public List<TipoDeProduto> listarTodos() {
		TipoDeProdutoDao tipoDeProdutoDao = DAOFactory.getTipoDeProdutoDao();
		return tipoDeProdutoDao.listarTodos();
	}

}

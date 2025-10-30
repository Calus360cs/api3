package lab.crud.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lab.crud.api.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {	
	
 
	List<Produto> findByNome(String nome);	

	List<Produto> findByNomeLike(String padraoDeNome);
}

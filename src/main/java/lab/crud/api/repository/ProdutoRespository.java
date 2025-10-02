package lab.crud.api.repository;

import org.springframework.data.repository.CrudRepository;

import lab.crud.api.model.Produto;

public interface ProdutoRespository extends CrudRepository<Produto, Integer> {	
	
	

}

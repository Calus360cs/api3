package lab.crud.api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.crud.api.model.Produto;
import lab.crud.api.repository.ProdutoRespository;

@RestController
public class ProdutoController {

	
	@Autowired
	private ProdutoRespository repository;
	
	
	//curl -X POST http://localhost:8080/produtos -H "Content-Type: application/json; Charset=utf-8" -d @produto-pao.json
	
	//@RequestMapping(method = RequestMethod.POST, path = "/produto")
	@PostMapping("/produtos")
	public ResponseEntity<Produto> novo(
			@RequestBody Produto produto) {
		
		
		produto.setDataCriacao(LocalDate.now());
		
		repository.save(produto);
		
		System.out.println(produto.toString());
		
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(produto);
	}
	
	@GetMapping("/produtos")
	public ResponseEntity<Iterable<Produto>> obterTodos() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(repository.findAll());
	}
}

package lab.crud.api.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lab.crud.api.model.Produto;
import lab.crud.api.repository.ProdutoRespository;

@RestController
public class ProdutoController {

	
	@Autowired
	private ProdutoRespository repository;
	
	
	//curl -X POST http://localhost:8081/produtos -H "Content-Type: application/json; Charset=utf-8" -d @produto-pao.json
	
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
		
		@GetMapping("/produtos/{id}")
		 public ResponseEntity<Object> buscarPorId(@PathVariable Integer id) {

		  // Alt + SHIFT + L -> extrai variável local
		  Optional<Produto> produtoEncontrado = repository.findById(id);

		  // Empty = Vazio
		  if (produtoEncontrado.isEmpty()) {
		   return ResponseEntity
		     .status(HttpStatus.NOT_FOUND)
		     .body("Produto não encontrado");
		  }

		  return ResponseEntity
		    .status(HttpStatus.OK)
		    .body(produtoEncontrado.get());
		 }
		 
		 //Observação: para métodos que não sejam o GET e o POST é necessário colocar o -X(menos xis maiúsculo)
		 //curl -X PUT http://localhost:8080/produtos/1 -H "Content-Type: application/json; Charset=utf-8" -d @produto-mortadela2.json 
		 @PutMapping("/produtos/{id}")
		 public ResponseEntity<Object> atualizarProduto(
		   @PathVariable Integer id,
		   @RequestBody Produto prod) {
		  
		  Optional<Produto> produto = repository.findById(id);
		  
		  if (produto.isEmpty()) {

		   return ResponseEntity
		     .status(HttpStatus.NOT_FOUND)
		     .body("Produto não encontrado!");
		  }
		  
		  prod.setId(id);
		  prod.setDataCriacao(produto.get().getDataCriacao());
		  repository.save(prod);
		  
		  return ResponseEntity
		    .status(HttpStatus.OK)
		    .body("Produto atualizado com sucesso!");
		 } 
		 

		 //curl -X DELETE http://localhost:8080/produtos/1
		 @DeleteMapping("/produtos/{id}")
		 public ResponseEntity<Object> apagarProduto(
				 @PathVariable Integer id) {
			 
			 Optional<Produto> produto = repository.findById(id);
			 if (produto.isEmpty()) {
				 
				 return ResponseEntity
						 .status (HttpStatus.NOT_FOUND)
						 .body("Produto não encontrado!");
			 }
			 
			 Produto prod = produto.get();
			 repository.delete(prod);
			 
			 return ResponseEntity
					 .status(HttpStatus.OK)
					 .body("Produto apagado com sucesso");
		 }
	}


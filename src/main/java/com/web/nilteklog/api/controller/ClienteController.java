package com.web.nilteklog.api.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.web.nilteklog.domain.model.Cliente;
import com.web.nilteklog.domain.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping()
	public List<Cliente> listar() {
		return repository.findAll();
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<Cliente> findById(@PathVariable Long clientId) {
		return repository.findById(clientId).map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED) // code 201 de sucesso e recurso criado
	public Cliente add(@Valid @RequestBody Cliente cliente) {
		return repository.save(cliente);
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<Cliente> edit(@PathVariable Long clientId, @Valid @RequestBody Cliente cliente) {
		if (!repository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clientId);
		cliente = repository.save(cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Long clientId) {
		if (!repository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		repository.deleteById(clientId);
		return ResponseEntity.noContent().build(); // code 204 quando não possui conteudo no corpo
	}
}

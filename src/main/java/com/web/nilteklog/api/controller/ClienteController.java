package com.web.nilteklog.api.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.web.nilteklog.domain.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository repository;
	private ClienteService clienteService;

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
	@ResponseStatus(HttpStatus.CREATED) // code 201 de sucesso e recurso criado
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clientId, @Valid @RequestBody Cliente cliente) {
		if (!repository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clientId);
		cliente = clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> delete(@PathVariable Long clientId) {
		if (!repository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}
		clienteService.excluir(clientId);
		return ResponseEntity.noContent().build(); // code 204 quando não possui conteudo no corpo
	}
}

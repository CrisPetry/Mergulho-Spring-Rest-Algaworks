package com.web.nilteklog.domain.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.web.nilteklog.domain.exception.DomainException;
import com.web.nilteklog.domain.model.Cliente;
import com.web.nilteklog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository repository;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean existEmail = repository.findByEmail(cliente.getEmail()).stream().anyMatch(c -> !c.equals(cliente));
		if (existEmail) {
			throw new DomainException("E-mail já em uso!");
		}
		return repository.save(cliente);
	}

	@Transactional
	public void excluir(Long clientId) {
		repository.deleteById(clientId);
	}
}

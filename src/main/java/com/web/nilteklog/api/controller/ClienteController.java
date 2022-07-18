package com.web.nilteklog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.nilteklog.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {

		Cliente client1 = new Cliente();
		client1.setId(1L);
		client1.setNome("João");
		client1.setTelefone("54 99921-2354");
		client1.setEmail("joao@uol.br");

		return Arrays.asList(client1);
	}
}

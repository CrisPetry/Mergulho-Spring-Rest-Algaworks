package com.web.nilteklog.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problema {

	private Integer status;
	private LocalDateTime dataHora;
	private String titulo;
	private List<Propriedade> fields;

	@Getter
	@AllArgsConstructor
	public static class Propriedade {
		private String campo;
		private String msg;
	}

}

package com.lins.linslogapi.repository.filter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LancamentoFilter {
	
	private String descricao;
	
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private LocalDate dataVencimetoDe;
	
	@DateTimeFormat(pattern = "yyy-MM-dd")
	private LocalDate dataVencimetoAte;
	
}

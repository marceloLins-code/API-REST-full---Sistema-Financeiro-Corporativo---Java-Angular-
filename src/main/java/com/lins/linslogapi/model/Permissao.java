package com.lins.linslogapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "permissao")
public class Permissao {

	@Id
	private Long codigo;
	private String descricao;

	public Long getCodigo() {
		return codigo;
	}
}
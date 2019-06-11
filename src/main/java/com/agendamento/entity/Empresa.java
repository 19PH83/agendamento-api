package com.agendamento.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 8272369118440438738L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

	private String nomeEmpresa; 
	
	private String descricaoEmpresa;
	
	@OneToMany
	@JoinTable(
			name="empresa_filial", 
			joinColumns={@JoinColumn(name="id_empresa")}, 
			inverseJoinColumns={@JoinColumn(name="id_filial")}
	)
	private List<Filial> filiais;
	
	public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	
	public List<Filial> getFiliais() {
		return filiais;
	}
	
	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

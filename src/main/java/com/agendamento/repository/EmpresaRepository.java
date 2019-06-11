package com.agendamento.repository;

import org.springframework.data.repository.CrudRepository;

import com.agendamento.entity.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long>{}
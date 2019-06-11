package com.agendamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.entity.Colaborador;
import com.agendamento.entity.Empresa;
import com.agendamento.entity.Filial;
import com.agendamento.repository.ColaboradorRepository;
import com.agendamento.repository.EmpresaRepository;
import com.agendamento.repository.FilialRepository;

@RestController
@RequestMapping
public class AgendamentoController {

	@Autowired private EmpresaRepository empresaRepository;
	@Autowired private FilialRepository filialRepository;
	@Autowired private ColaboradorRepository colaboradorRepository;

	@RequestMapping("/empresas")
    public Iterable<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }
	
	@RequestMapping("/empresa/{idEmpresa}")
    public Empresa listarEmpresa(@PathVariable long idEmpresa) {
        return empresaRepository.findOne(idEmpresa);
    }
	
	@RequestMapping(value="/empresa", method={RequestMethod.POST})
	public ResponseEntity<?> createEmpresa(@RequestBody Empresa empresa) {
		try {
			empresaRepository.save(empresa);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/filial/{idEmpresa}", method={RequestMethod.POST})
	public ResponseEntity<?> createFilial(
			@PathVariable long idEmpresa, 
			@RequestBody Filial filial) {
		try {
			Filial filialSalva = filialRepository.save(filial);
			
			Empresa empresa = empresaRepository.findOne(Long.valueOf(idEmpresa));
			empresa.getFiliais().add(filialSalva);
			empresaRepository.save(empresa);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/filiais")
    public Iterable<Filial> listarFiliais() {
        return filialRepository.findAll();
    }
	
	@RequestMapping(value="/colaborador/{idFilial}", method={RequestMethod.POST})
	public ResponseEntity<?> createColaborador(
			@PathVariable long idFilial, 
			@RequestBody Colaborador colaborador) {
		try {
//			Colaborador colaboradorSalvo = colaboradorRepository.save(colaborador);
			
			Filial filial = filialRepository.findOne(Long.valueOf(idFilial));
			filial.getColaboradores().add(colaborador);
			filialRepository.save(filial);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/colaboradores")
    public Iterable<Colaborador> listarColaboradores() {
        return colaboradorRepository.findAll();
    }
}

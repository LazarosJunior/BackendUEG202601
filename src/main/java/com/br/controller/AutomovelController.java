//teste

package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Automovel;
import com.br.model.Marca;
import com.br.repository.AutomovelRepository;
import com.br.repository.MarcaRepository;

@RequestMapping("/cautomovel/")
@RestController
@CrossOrigin(origins="*")
public class AutomovelController {

	////Cria o repositorio JPA de forma automatica e autogerenciado
	
    @Autowired
    private AutomovelRepository arep;

    //Metodo Listar - trazer todas os Automoveis do banco
    
    @GetMapping("/automovel")
    public List<Automovel> listar(){
        return this.arep.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
    }

    //Metodo Consultar - trazer um automovel, caso exista, pelo codigo
    
    @GetMapping("/automovel/{id}")
    public ResponseEntity<Automovel> consultar(@PathVariable Long id) {

        Automovel automovel = this.arep.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Automóvel não encontrado " + id)
        );

        return ResponseEntity.ok(automovel);
    }

    //Metodo Inserir - insere um automovel
    @PostMapping("/automovel")
    public Automovel inserir(@RequestBody Automovel automovel) {
        return this.arep.save(automovel);
    }
}


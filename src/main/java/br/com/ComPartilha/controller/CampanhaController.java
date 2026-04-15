package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Campanha;
import br.com.ComPartilha.service.CampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/campanhas")
@RequiredArgsConstructor
public class CampanhaController {

    private final CampanhaService campanhaService;

    @GetMapping
    public List<Campanha> listarTodas() {
        return campanhaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campanha> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaService.buscarPorId(id));
    }

    @GetMapping("/ong/{ongId}")
    public List<Campanha> buscarPorOng(@PathVariable Long ongId) {
        return campanhaService.buscarPorOng(ongId);
    }

    @PostMapping
    public ResponseEntity<Campanha> salvar(@Valid @RequestBody Campanha campanha) {
        Campanha salva = campanhaService.salvar(campanha);
        return ResponseEntity.ok(campanhaService.buscarPorId(salva.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campanha> atualizar(@PathVariable Long id,@Valid @RequestBody Campanha campanha) {
        return ResponseEntity.ok(campanhaService.atualizar(id, campanha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        campanhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
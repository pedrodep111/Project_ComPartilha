package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Doador;
import br.com.ComPartilha.service.DoadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doadores")
@RequiredArgsConstructor
public class DoadorController {

    private final DoadorService doadorService;

    @GetMapping
    public List<Doador> listarTodas() {
        return doadorService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doador> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(doadorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Doador> salvar(@Valid @RequestBody Doador doador) {
        return ResponseEntity.ok(doadorService.salvar(doador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doador> atualizar(@PathVariable Long id,@Valid @RequestBody Doador doador) {
        return ResponseEntity.ok(doadorService.atualizar(id, doador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        doadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
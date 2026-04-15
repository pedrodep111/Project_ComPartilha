package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Doacao;
import br.com.ComPartilha.service.DoacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService doacaoService;

    @GetMapping
    public List<Doacao> listarTodas() {
        return doacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(doacaoService.buscarPorId(id));
    }

    @GetMapping("/doador/{doadorId}")
    public List<Doacao> buscarPorDoador(@PathVariable Long doadorId) {
        return doacaoService.buscarPorDoador(doadorId);
    }

    @PostMapping
    public ResponseEntity<Doacao> salvar(@Valid @RequestBody Doacao doacao) {
        return ResponseEntity.ok(doacaoService.salvar(doacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doacao> atualizar(@PathVariable Long id,@Valid @RequestBody Doacao doacao) {
        return ResponseEntity.ok(doacaoService.atualizar(id, doacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        doacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
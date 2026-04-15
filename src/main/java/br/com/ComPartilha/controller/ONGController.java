package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.service.ONGService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ongs")
@RequiredArgsConstructor
public class ONGController {

    private final ONGService ongService;

    @GetMapping
    public List<ONG> listarTodas() {
        return ongService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ONG> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ongService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ONG> salvar(@Valid @RequestBody ONG ong) {
        return ResponseEntity.ok(ongService.salvar(ong));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ONG> atualizar(@PathVariable Long id,@Valid @RequestBody ONG ong) {
        return ResponseEntity.ok(ongService.atualizar(id, ong));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ongService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.service.ONGService;
import br.com.ComPartilha.repository.ONGRepository;
import br.com.ComPartilha.exception.ExceptionUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ongs")
public class ONGController {

    private final ONGService ongService;

    public ONGController(ONGRepository ongRepository) {
        this.ongService = new ONGService(ongRepository);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listarTodas() {
        try {
            return ResponseEntity.ok(ongService.listarTodas());
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ongService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody ONG ong) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ongService.salvar(ong));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody ONG ong) {
        try {
            return ResponseEntity.ok(ongService.atualizar(id, ong));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            ongService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }
}
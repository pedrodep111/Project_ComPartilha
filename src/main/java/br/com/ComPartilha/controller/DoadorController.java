package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Doador;
import br.com.ComPartilha.service.DoadorService;
import br.com.ComPartilha.repository.DoadorRepository;
import br.com.ComPartilha.exception.ExceptionUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    private final DoadorService doadorService;

    public DoadorController(DoadorRepository doadorRepository) {
        this.doadorService = new DoadorService(doadorRepository);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listarTodas() {
        try {
            return ResponseEntity.ok(doadorService.listarTodas());
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(doadorService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@Valid @RequestBody Doador doador) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(doadorService.salvar(doador));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Doador doador) {
        try {
            return ResponseEntity.ok(doadorService.atualizar(id, doador));
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
            doadorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }
}
package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Campanha;
import br.com.ComPartilha.service.CampanhaService;
import br.com.ComPartilha.repository.CampanhaRepository;
import br.com.ComPartilha.repository.ONGRepository;
import br.com.ComPartilha.exception.ExceptionUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/campanhas")
public class CampanhaController {

    private final CampanhaService campanhaService;

    public CampanhaController(CampanhaRepository campanhaRepository, ONGRepository ongRepository) {
        this.campanhaService = new CampanhaService(campanhaRepository, ongRepository);
    }

    @GetMapping
    public ResponseEntity<?> listarTodas() {
        try {
            return ResponseEntity.ok(campanhaService.listarTodas());
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(campanhaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @GetMapping("/ong/{ongId}")
    public ResponseEntity<?> buscarPorOng(@PathVariable Long ongId) {
        try {
            return ResponseEntity.ok(campanhaService.buscarPorOng(ongId));
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Campanha campanha) {
        try {
            Campanha salva = campanhaService.salvar(campanha);
            return ResponseEntity.status(HttpStatus.CREATED).body(campanhaService.buscarPorId(salva.getId()));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Campanha campanha) {
        try {
            return ResponseEntity.ok(campanhaService.atualizar(id, campanha));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            campanhaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }
}
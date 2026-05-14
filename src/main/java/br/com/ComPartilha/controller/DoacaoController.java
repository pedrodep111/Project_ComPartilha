package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Doacao;
import br.com.ComPartilha.service.DoacaoService;
import br.com.ComPartilha.repository.DoacaoRepository;
import br.com.ComPartilha.repository.DoadorRepository;
import br.com.ComPartilha.repository.CampanhaRepository;
import br.com.ComPartilha.exception.ExceptionUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoRepository doacaoRepository, DoadorRepository doadorRepository, CampanhaRepository campanhaRepository) {
        this.doacaoService = new DoacaoService(doacaoRepository, doadorRepository, campanhaRepository);
    }

    @GetMapping
    public ResponseEntity<?> listarTodas() {
        try {
            return ResponseEntity.ok(doacaoService.listarTodas());
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(doacaoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @GetMapping("/doador/{doadorId}")
    public ResponseEntity<?> buscarPorDoador(@PathVariable Long doadorId) {
        try {
            return ResponseEntity.ok(doacaoService.buscarPorDoador(doadorId));
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody Doacao doacao) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(doacaoService.salvar(doacao));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Doacao doacao) {
        try {
            return ResponseEntity.ok(doacaoService.atualizar(id, doacao));
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            doacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ExceptionUtil.tratarRuntime(e);
        } catch (Exception e) {
            return ExceptionUtil.tratarGenerico(e);
        }
    }

}
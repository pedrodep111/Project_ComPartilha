package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.service.ONGService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
- Controller responsável pelos endpoints da ONG
- Recebe as requisições HTTP e delega a lógica para ONGService
- Base URL: /ongs
*/

@RestController // Indica que essa classe é um controller REST
@RequestMapping("/ongs") // Define A URL base para todos os endpoints desta classe
@RequiredArgsConstructor // Cria o construtor automaticamente
public class ONGController {

    private final ONGService ongService; // Service com as regras de negócio da ONG

    // Retorna todas as ONGs cadastradas
    @GetMapping
    public List<ONG> listarTodas() {
        return ongService.listarTodas();
    }

    // Retorna uma ONG pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<ONG> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ongService.buscarPorId(id));
    }

    // Cria uma nova ONG
    // @valid aciona as validações definidas na classe ONG
    @PostMapping
    public ResponseEntity<ONG> salvar(@Valid @RequestBody ONG ong) {
        return ResponseEntity.ok(ongService.salvar(ong));
    }

    // Atualiza os dados de uma ONG existente
    // @valid aciona as validações definidas na classes ONG
    @PutMapping("/{id}")
    public ResponseEntity<ONG> atualizar(@PathVariable Long id,@Valid @RequestBody ONG ong) {
        return ResponseEntity.ok(ongService.atualizar(id, ong));
    }

    // Deleta uma ONG pelo ID
    // Retorna 204 No Content em caso de sucesso
    @DeleteMapping("/{id}") // Remove um dado - "Deleta essa ONG"
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ongService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
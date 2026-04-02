package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Doador;
import br.com.ComPartilha.service.DoadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
- Controller responsável pelos endpoints do Doador
- Recebe as requisições HTTP e delega a lógica para DoadorService
- Base URL: /doadores
*/

@RestController // Indica que essa classe é um controller REST (retorna JSON)
@RequestMapping("/doadores") // Define A URL base para todos os endpoints desta classe
@RequiredArgsConstructor // Gera o construtor com os campos final automaticamente
public class DoadorController {

    private final DoadorService doadorService; // Service com as regras de negócio do Doador

    // Retorna todas os Doadores cadastrados
    @GetMapping
    public List<Doador> listarTodas() {
        return doadorService.listarTodas();
    }

    // Retorna um Doador pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Doador> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(doadorService.buscarPorId(id));
    }

    // Salva um novo doador
    // @valid aciona as validações definidas na classe Doador
    @PostMapping
    public ResponseEntity<Doador> salvar(@Valid @RequestBody Doador doador) {
        return ResponseEntity.ok(doadorService.salvar(doador));
    }

    // Atualiza os dados de um Doador existente
    // @valid aciona as validações definidas na classe Doador
    @PutMapping("/{id}")
    public ResponseEntity<Doador> atualizar(@PathVariable Long id,@Valid @RequestBody Doador doador) {
        return ResponseEntity.ok(doadorService.atualizar(id, doador));
    }

    // Deleta um Doador pelo ID
    // retorna 204 No Content no caso de sucesso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        doadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
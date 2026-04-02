package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Doacao;
import br.com.ComPartilha.service.DoacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
- Controller responsável pelos endpoints do Doacao
- Recebe as requisições HTTP e delega a lógica para DoacaoService
- Base URL: /doacoes
*/

@RestController // Indica que essa classe é um controller REST (retorna JSON)
@RequestMapping("/doacoes") // Define A URL base para todos os endpoints desta classe
@RequiredArgsConstructor // Gera o construtor com os campos final automaticamente
public class DoacaoController {

    private final DoacaoService doacaoService; // Service com as regras de negócio do Doador

    // Retorna todas as doações do banco de dados
    @GetMapping
    public List<Doacao> listarTodas() {
        return doacaoService.listarTodas();
    }

    // Busca uma Doação já existente pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Doacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(doacaoService.buscarPorId(id));
    }

    // Cria uma nova doação
    // @valid aciona as validações definidas na classe Doacao
    @PostMapping
    public ResponseEntity<Doacao> salvar(@Valid @RequestBody Doacao doacao) {
        return ResponseEntity.ok(doacaoService.salvar(doacao));
    }

    // Atualiza uma doacao já existente
    // @valid aciona as validações definidas na classe Doocao
    @PutMapping("/{id}")
    public ResponseEntity<Doacao> atualizar(@PathVariable Long id,@Valid @RequestBody Doacao doacao) {
        return ResponseEntity.ok(doacaoService.atualizar(id, doacao));
    }

    // Deleta uma doacao pelo ID
    // return 204 No Content no caso de sucesso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        doacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Busca todas as doacoes de um Doador específico
    @GetMapping("/doador/{doadorId}")
    public List<Doacao> buscarPorDoador(@PathVariable Long doadorId) {
        return doacaoService.buscarPorDoador(doadorId);
    }
}
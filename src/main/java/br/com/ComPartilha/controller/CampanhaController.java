package br.com.ComPartilha.controller;

import br.com.ComPartilha.model.Campanha;
import br.com.ComPartilha.service.CampanhaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List; // Lista

/*
- Controller responsável pelos endpoints da campanha
- Recebe as requisições HTTP e delega a lógica para CampanhaService
- Base URL: /campanhas
*/

@RestController // Indica que essa classe é um controller REST (retorna JSON)
@RequestMapping("/campanhas") // Define A URL base para todos os endpoints desta classe
@RequiredArgsConstructor // Gera o construtor com os campos final automaticamente
public class CampanhaController {

    private final CampanhaService campanhaService; // Service com as regras de negócio da Campanha

    // Retorna todas as campanhas cadastradas
    @GetMapping
    public List<Campanha> listarTodas() {
        return campanhaService.listarTodas();
    }

    // Busca uma camapanha pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Campanha> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(campanhaService.buscarPorId(id));
    }

    // Busca todas as campanhas de uma ONG específica
    @GetMapping("/ong/{ongId}")
    public List<Campanha> buscarPorOng(@PathVariable Long ongId) {
        return campanhaService.buscarPorOng(ongId);
    }

    // Salva uma nova Campanha
    // @valid aciona as validações definidas pela classe Campanha
    @PostMapping
    public ResponseEntity<Campanha> salvar(@Valid @RequestBody Campanha campanha) {
        Campanha salva = campanhaService.salvar(campanha);
        return ResponseEntity.ok(campanhaService.buscarPorId(salva.getId()));
    }

    // Atualiza uma campanha já existente
    // @valid aciona as validações definidas pela classe Campanha
    @PutMapping("/{id}")
    public ResponseEntity<Campanha> atualizar(@PathVariable Long id,@Valid @RequestBody Campanha campanha) {
        return ResponseEntity.ok(campanhaService.atualizar(id, campanha));
    }

    // Deleta uma campanha pelo ID
    // retorna 204 No Content no caso de sucesso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        campanhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
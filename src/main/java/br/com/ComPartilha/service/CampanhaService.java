package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Campanha;
import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.repository.CampanhaRepository;
import br.com.ComPartilha.repository.ONGRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/*
- Service responsável pelas regras de negócio da Campanha
- Faz a intermediação entre o Controller e o Repository
*/

@Service
@RequiredArgsConstructor // Gera o construtor com os campos final automaticamente (injeção de dependência)
public class CampanhaService {

    private final CampanhaRepository campanhaRepository; // Repositório para acessar o banco de dados
    private final ONGRepository ongRepository; // Necessário para buscar a ONG ao salvar/atualizar

    // Retorna todas as campanhas cadastradas no banco de dados
    // @return - Lista de campanhas
    public List<Campanha> listarTodas() {
        return campanhaRepository.findAll();
    }

    // Busca uma campanha pelo ID
    // Lança exceção se não encontrada
    // @param id - ID da campanha
    // @return - campanha encontrada
    public Campanha buscarPorId(Long id) {
        return campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada!")); // .orElseThrow = ou Entao Lança
    }

    // Salva uma nova campanha no banco de dados
    // Busca a ONG completa antes de salvar para garantir integridade
    // @param campanha - objeto campanha reebido do Controller
    // @return - Campanha salva com ID gerado
    public Campanha salvar(Campanha campanha) {
        Long ongId = campanha.getOng().getId();
        ONG ong = ongRepository.findById(ongId)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
        campanha.setOng(ong);
        return campanhaRepository.save(campanha);
    }

    // Deleta uma campanha pelo ID
    // @param id - ID da campanha a ser deletada
    public void deletar(Long id) {
        campanhaRepository.deleteById(id);
    }

    // Atualiza os dados de uma campnha existente
    // Se uma nova ONG for informada, busca os dados completos dela
    // @param id - ID da campanha a ser atualizada
    // @param campanhaAtualizada - objeto com os novos dados
    // @return - campanha atualizada
    public Campanha atualizar(Long id, Campanha campanhaAtualizada) {
        Campanha campanha = campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada!"));
        campanha.setTitulo(campanhaAtualizada.getTitulo());
        campanha.setDescricao(campanhaAtualizada.getDescricao());
        campanha.setMeta(campanhaAtualizada.getMeta());
        if (campanhaAtualizada.getOng() != null) {
            ONG ong = ongRepository.findById(campanhaAtualizada.getOng().getId())
                    .orElseThrow(() -> new RuntimeException("ONG não encontrada!"));
            campanha.setOng(ong);
        }
        return campanhaRepository.save(campanha);
    }

    // Busca todas as campanhas de uma ONG específica
    // @param ongID - ID da ONG
    // @return - Lista de campanhas da ONG
    public List<Campanha> buscarPorOng(Long ongId) {
        return campanhaRepository.findByOngId(ongId);
    }
}
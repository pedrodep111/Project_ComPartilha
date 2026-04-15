package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Campanha;
import br.com.ComPartilha.model.Doacao;
import br.com.ComPartilha.model.Doador;
import br.com.ComPartilha.repository.CampanhaRepository;
import br.com.ComPartilha.repository.DoacaoRepository;
import br.com.ComPartilha.repository.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/*
- Service responsável pelas regras de negócio da Doação
- Faz a intermediação entre o Controller e o Repository
*/

@Service // Avisa ao Spring que essa classe é um SERVICE
@RequiredArgsConstructor  // Gera o construtor com os campos final automaticamente (injeção de dependência)
public class DoacaoService {

    private final DoacaoRepository doacaoRepository; // Repositório de doações
    private final DoadorRepository doadorRepository; // Repositório para buscar o doador ao salvar/atualizar
    private final CampanhaRepository campanhaRepository; // Repositório para buscar a campanha ao salvar/atualizar

    // Salva uma nova doacao no banco de dados
    // Busca o Doador e a campanha completa antes de salvar para garantir integridade
    // @param doacao - objeto campanha recebido do Controller
    // @return - Doacao salva com ID gerado
    public Doacao salvar(Doacao doacao) {
        Doador doador = doadorRepository.findById(doacao.getDoador().getId()) // Pega o ID que veio na requisição e busca o doador completo no banco
                .orElseThrow(() -> new RuntimeException("Doador não encontrado")); // Se o doador não existir, lança mensagem de erro
        Campanha campanha = campanhaRepository.findById(doacao.getCampanha().getId()) // Busca a campanha completa no banco
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada")); // Se a campanha não existir, lanç amensagem de erro
        doacao.setDoador(doador); // Substitui o doador do objeto -doacao- pelos objetos completos que buscamos no banco
        doacao.setCampanha(campanha);  // Substitui a campanha do objeto -doacao- pelos objetos completos que buscamos no banco
        return doacaoRepository.save(doacao); // Salva a doacao no banco e retorna o objeto salvo com o ID gerado
    }

    // Retorna todas as doações cadastradas no banco de dados
    // @return - Lista de doações
    public List<Doacao> listarTodas() {
        return doacaoRepository.findAll(); // findALL -> metodo do JpaRepository
    }

    // Busca uma doação pelo ID
    // Lança exceção se não encontrada
    // @param id - ID da doação
    // @return - doação encontrada
    public Doacao buscarPorId(Long id) {
        return doacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));
    }


    // Busca todas as doações de um doador específico
    // @param doadorID - ID da Doador
    // @return - Lista de Doações do Doador
    public List<Doacao> buscarPorDoador(Long doadorId) {
        return doacaoRepository.findByDoadorId(doadorId);
    }

    // Deleta uma doação pelo ID
    // @param id - ID da doação ser deletada
    public void deletar(Long id) {
        doacaoRepository.deleteById(id);
    }

    // Atualiza os dados de uma doação existente
    // Busca o Doador e a Campanha completos para garantir integridade
    // @param id - ID da doação a ser atualizada
    // @param doacaoAtualizada - objeto com os novos dados
    // @return - Doação atualizada
    public Doacao atualizar(Long id, Doacao doacaoAtualizada) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada!"));
        Doador doador = doadorRepository.findById(doacaoAtualizada.getDoador().getId())
                .orElseThrow(() -> new RuntimeException("Doador não encontrado!"));
        Campanha campanha = campanhaRepository.findById(doacaoAtualizada.getCampanha().getId())
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada!"));
        doacao.setValor(doacaoAtualizada.getValor());
        doacao.setDoador(doador);
        doacao.setCampanha(campanha);
        return doacaoRepository.save(doacao);
    }

}
package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Doador;
import br.com.ComPartilha.repository.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/*
- Service responsável pelas regras de negócio do Doador
- Faz a intermediação entre o Controller e o Repository
*/

@Service
@RequiredArgsConstructor // Gera o construtor com os campos final automaticamnte (injeção de dependência)
public class DoadorService {

    private final DoadorRepository doadorRepository; // Repositório para acessar o banco de dados

    // Retorna todos os doadores cadastrados no banco de dados
    public List<Doador> listarTodas() {
        return doadorRepository.findAll();
    }

    // Busca um doador pelo ID
    // Lança exceção de não encontrado
    // @param id - ID do doador
    // @return - Doador encontrado
    public Doador buscarPorId(Long id) {
        return doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado!"));
    }

    // Salva um novo doador no banco de dados
    // @param doador - objeto Doador recebido do Controler
    // @return - doador salvo com ID gerado
    public Doador salvar(Doador doador) {
        return doadorRepository.save(doador);
    }

    // Deleta um doador pelo ID
    // @param id - ID do doador a ser deletado
    public void deletar(Long id) {
        doadorRepository.deleteById(id);
    }

    // Atualiza os dados de um doador existente
    // @param id - ID do doador a ser atualizado
    // @param doadorAtualizado - objeto com os novos dados
    // @return - Doador atualizado
    public Doador atualizar(Long id, Doador doadorAtualizado) {
        Doador doador = doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado!"));
        doador.setNome(doadorAtualizado.getNome());
        doador.setEmail(doadorAtualizado.getEmail());
        doador.setCpf(doadorAtualizado.getCpf());
        doador.setTelefone(doadorAtualizado.getTelefone());
        return doadorRepository.save(doador);
    }
}
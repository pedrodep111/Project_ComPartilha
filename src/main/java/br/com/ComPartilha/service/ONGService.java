package br.com.ComPartilha.service;

import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.repository.ONGRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/*
- Service responsável pelas regras de negócio da ONG
- Faz a intermediação entre o Controller e o Repository
*/

@Service
@RequiredArgsConstructor // Gera o construtor com os campos final automaticamente (injeção de dependência)
public class ONGService {

    private final ONGRepository ongRepository; // Repositório para acessar o banco de dados

    // Salva uma nova ONG no banco de dados
    // @param ong - objeto ONG reebido do Controller
    // @return - ONG salva com ID gerado
    public ONG salvar(ONG ong) {
        return ongRepository.save(ong);
    }

    // Atualiza os dados de uma ONG existente
    // @param id - ID da ONG a ser atualizada
    // @param ongAtualizada - objeto com os novos dados
    // @return - ONG atualizada
    public ONG atualizar(Long id, ONG ongAtualizada) {
        ONG ong = ongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
        ong.setNome(ongAtualizada.getNome());
        ong.setEmail(ongAtualizada.getEmail());
        ong.setCnpj(ongAtualizada.getCnpj());
        ong.setTelefone(ongAtualizada.getTelefone());
        return ongRepository.save(ong);
    }

    // Retorna todas as ONGs cadastradas no banco de dados
    // @return - Lista de ONGs
    public List<ONG> listarTodas() {
        return ongRepository.findAll();
    }

    // Busca uma ONG pelo ID
    // Lança exceção se não encontrada
    // @param id - ID da ONG
    // @return - ONG encontrada
    public ONG buscarPorId(Long id) {
        return ongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
    }

    // Deleta uma ONG pelo ID
    // @param id - ID da ONG a ser deletada
    public void deletar(Long id) {
        ongRepository.deleteById(id);
    }
}
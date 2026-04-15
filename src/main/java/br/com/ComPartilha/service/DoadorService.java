package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Doador;
import br.com.ComPartilha.repository.DoadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoadorService {

    private final DoadorRepository doadorRepository;

    public List<Doador> listarTodas() {
        return doadorRepository.findAll();
    }

    public Doador buscarPorId(Long id) {
        return doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado!"));
    }

    public Doador salvar(Doador doador) {
        return doadorRepository.save(doador);
    }

    public void deletar(Long id) {
        doadorRepository.deleteById(id);
    }

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
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

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final DoadorRepository doadorRepository;
    private final CampanhaRepository campanhaRepository;

    public Doacao salvar(Doacao doacao) {
        Doador doador = doadorRepository.findById(doacao.getDoador().getId())
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));
        Campanha campanha = campanhaRepository.findById(doacao.getCampanha().getId())
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));
        doacao.setDoador(doador);
        doacao.setCampanha(campanha);
        return doacaoRepository.save(doacao);
    }

    public List<Doacao> listarTodas() {
        return doacaoRepository.findAll();
    }

    public Doacao buscarPorId(Long id) {
        return doacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));
    }

    public List<Doacao> buscarPorDoador(Long doadorId) {
        return doacaoRepository.findByDoadorId(doadorId);
    }

    public void deletar(Long id) {
        doacaoRepository.deleteById(id);
    }

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
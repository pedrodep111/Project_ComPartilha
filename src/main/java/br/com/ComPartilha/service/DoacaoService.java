package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Campanha;
import br.com.ComPartilha.model.Doacao;
import br.com.ComPartilha.model.Doador;
import br.com.ComPartilha.repository.CampanhaRepository;
import br.com.ComPartilha.repository.DoacaoRepository;
import br.com.ComPartilha.repository.DoadorRepository;
import java.util.List;

public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final DoadorRepository doadorRepository;
    private final CampanhaRepository campanhaRepository;

    public DoacaoService(DoacaoRepository doacaoRepository, DoadorRepository doadorRepository, CampanhaRepository campanhaRepository) {
        this.doacaoRepository = doacaoRepository;
        this.doadorRepository = doadorRepository;
        this.campanhaRepository = campanhaRepository;
    }

    public Doacao salvar(Doacao doacao) {
        try {
            Doador doador = doadorRepository.findById(doacao.getDoador().getId())
                    .orElseThrow(() -> new RuntimeException("Doador não encontrado"));
            Campanha campanha = campanhaRepository.findById(doacao.getCampanha().getId())
                    .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));
            doacao.setDoador(doador);
            doacao.setCampanha(campanha);
            return doacaoRepository.save(doacao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar doação: " + e.getMessage());
        }
    }

    public List<Doacao> listarTodas() {
        try {
            return doacaoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar doações: " + e.getMessage());
        }
    }

    public Doacao buscarPorId(Long id) {
        try {
            return doacaoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Doação não encontrada"));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar doação: " + e.getMessage());
        }
    }

    public List<Doacao> buscarPorDoador(Long doadorId) {
        try {
            return doacaoRepository.findByDoadorId(doadorId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar doações por doador: " + e.getMessage());
        }
    }

    public void deletar(Long id) {
        try {
            doacaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar doação: " + e.getMessage());
        }
    }

    public Doacao atualizar(Long id, Doacao doacaoAtualizada) {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar doação: " + e.getMessage());
        }
    }

    // Threads example
    public void salvarAsync(Doacao doacao) {
        Thread thread = new Thread(() -> {
            try {
                salvar(doacao);
                System.out.println("Doação salva de forma assíncrona");
            } catch (Exception e) {
                System.err.println("Erro ao salvar doação assíncrona: " + e.getMessage());
            }
        });
        thread.start();
    }
}
package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Campanha;
import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.repository.CampanhaRepository;
import br.com.ComPartilha.repository.ONGRepository;
import java.util.List;

public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final ONGRepository ongRepository;

    public CampanhaService(CampanhaRepository campanhaRepository, ONGRepository ongRepository) {
        this.campanhaRepository = campanhaRepository;
        this.ongRepository = ongRepository;
    }

    public List<Campanha> listarTodas() {
        try {
            return campanhaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar campanhas: " + e.getMessage());
        }
    }

    public Campanha buscarPorId(Long id) {
        try {
            return campanhaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Campanha não encontrada!"));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar campanha: " + e.getMessage());
        }
    }

    public Campanha salvar(Campanha campanha) {
        try {
            Long ongId = campanha.getOng().getId();
            ONG ong = ongRepository.findById(ongId)
                    .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
            campanha.setOng(ong);
            return campanhaRepository.save(campanha);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar campanha: " + e.getMessage());
        }
    }

    public void deletar(Long id) {
        try {
            campanhaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar campanha: " + e.getMessage());
        }
    }

    public Campanha atualizar(Long id, Campanha campanhaAtualizada) {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar campanha: " + e.getMessage());
        }
    }

    public List<Campanha> buscarPorOng(Long ongId) {
        try {
            return campanhaRepository.findByOngId(ongId);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar campanhas por ONG: " + e.getMessage());
        }
    }

    public void salvarAssincrono(Campanha campanha) {
        Thread thread = new Thread(() -> {
            try {
                salvar(campanha);
                System.out.println("Campanha salva de forma assíncrona");
            } catch (Exception e) {
                System.err.println("Erro ao salvar campanha assíncrona: " + e.getMessage());
            }
        });
        thread.start();
    }
}
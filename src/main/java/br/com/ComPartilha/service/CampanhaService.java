package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Campanha;
import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.repository.CampanhaRepository;
import br.com.ComPartilha.repository.ONGRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final ONGRepository ongRepository;

    public List<Campanha> listarTodas() {
        return campanhaRepository.findAll();
    }

    public Campanha buscarPorId(Long id) {
        return campanhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campanha não encontrada!"));
    }

    public Campanha salvar(Campanha campanha) {
        Long ongId = campanha.getOng().getId();
        ONG ong = ongRepository.findById(ongId)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
        campanha.setOng(ong);
        return campanhaRepository.save(campanha);
    }

    public void deletar(Long id) {
        campanhaRepository.deleteById(id);
    }

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

    public List<Campanha> buscarPorOng(Long ongId) {
        return campanhaRepository.findByOngId(ongId);
    }
}
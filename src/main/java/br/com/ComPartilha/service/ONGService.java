package br.com.ComPartilha.service;

import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.repository.ONGRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ONGService {

    private final ONGRepository ongRepository;

    public ONG salvar(ONG ong) {
        return ongRepository.save(ong);
    }

    public ONG atualizar(Long id, ONG ongAtualizada) {
        ONG ong = ongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
        ong.setNome(ongAtualizada.getNome());
        ong.setEmail(ongAtualizada.getEmail());
        ong.setCnpj(ongAtualizada.getCnpj());
        ong.setTelefone(ongAtualizada.getTelefone());
        return ongRepository.save(ong);
    }

    public List<ONG> listarTodas() {
        return ongRepository.findAll();
    }

    public ONG buscarPorId(Long id) {
        return ongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
    }

    public void deletar(Long id) {
        ongRepository.deleteById(id);
    }
}
package br.com.ComPartilha.service;

import br.com.ComPartilha.model.ONG;
import br.com.ComPartilha.repository.ONGRepository;

public class ONGService extends BaseService<ONG, Long> {

    public ONGService(ONGRepository ongRepository) {
        super(ongRepository);
    }

    @Override
    public ONG atualizar(Long id, ONG ongAtualizada) {
        try {
            ONG ong = buscarPorId(id);
            ong.setNome(ongAtualizada.getNome());
            ong.setEmail(ongAtualizada.getEmail());
            ong.setCnpj(ongAtualizada.getCnpj());
            ong.setTelefone(ongAtualizada.getTelefone());
            return salvar(ong);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar ONG: " + e.getMessage());
        }
    }

    public ONG salvar(ONG ong, String extra) {
        return salvar(ong);
    }
}
package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Doador;
import br.com.ComPartilha.repository.DoadorRepository;
import java.util.List;

public class DoadorService extends BaseService<Doador, Long> {

    public DoadorService(DoadorRepository doadorRepository) {
        super(doadorRepository);
    }

    @Override
    public Doador atualizar(Long id, Doador doadorAtualizado) {
        try {
            Doador doador = buscarPorId(id);
            doador.setNome(doadorAtualizado.getNome());
            doador.setEmail(doadorAtualizado.getEmail());
            doador.setCpf(doadorAtualizado.getCpf());
            doador.setTelefone(doadorAtualizado.getTelefone());
            return salvar(doador);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar doador: " + e.getMessage());
        }
    }

    // Overloading example
    public Doador salvar(Doador doador, String extra) {
        // some logic
        return salvar(doador);
    }
}
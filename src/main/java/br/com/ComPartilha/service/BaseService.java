package br.com.ComPartilha.service;

import br.com.ComPartilha.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class BaseService<T extends Usuario, ID> {
    protected JpaRepository<T, ID> repository;

    public BaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> listarTodas() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage());
        }
    }

    public T buscarPorId(ID id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Entidade não encontrada!"));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar: " + e.getMessage());
        }
    }

    public T salvar(T entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar: " + e.getMessage());
        }
    }

    public T salvar(T entity, boolean validate) {
        if (validate) {
        }
        return salvar(entity);
    }

    public void deletar(ID id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar: " + e.getMessage());
        }
    }

    public T atualizar(ID id, T entityAtualizada) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada!"));
        return repository.save(entity);
    }

    public void salvarAsync(T entity) {
        Thread thread = new Thread(() -> {
            try {
                salvar(entity);
                System.out.println("Entidade salva de forma assíncrona");
            } catch (Exception e) {
                System.err.println("Erro ao salvar assíncrono: " + e.getMessage());
            }
        });
        thread.start();
    }
}

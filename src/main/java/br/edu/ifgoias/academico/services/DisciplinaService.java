package br.edu.ifgoias.academico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifgoias.academico.entities.Disciplina;
import br.edu.ifgoias.academico.repositories.DisciplinaRepository;

@Service
public class DisciplinaService {
    
    @Autowired
    private DisciplinaRepository repository;

    // Listar todas as disciplinas
    public List<Disciplina> findAll() {
        return repository.findAll();
    }

    // Procurar uma disciplina pela chave primária
    public Optional<Disciplina> findById(Integer id) {
        return repository.findById(id);
    }

    // Inserir uma nova disciplina
    public Disciplina save(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    // Alterar uma disciplina existente
    public Disciplina update(Integer id, Disciplina newDisciplina) {
        return repository.findById(id)
            .map(disciplina -> {
                disciplina.setNomedisciplina(newDisciplina.getNomedisciplina());
                disciplina.setCargahoraria(newDisciplina.getCargahoraria());
                return repository.save(disciplina);
            })
            .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }

    // Excluir uma disciplina
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

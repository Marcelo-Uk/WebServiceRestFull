package br.edu.ifgoias.academico.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifgoias.academico.entities.Disciplina;
import br.edu.ifgoias.academico.services.DisciplinaService;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaResource {
    
    @Autowired
    private DisciplinaService service;

    // a) Listar todas as disciplinas
    @GetMapping
    public List<Disciplina> listarTodas() {
        return service.findAll();
    }

    // b) Procurar uma disciplina pela chave primária
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Integer id) {
        Optional<Disciplina> disciplina = service.findById(id);
        return disciplina.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // c) Inserir uma nova disciplina
    @PostMapping
    public Disciplina inserir(@RequestBody Disciplina disciplina) {
        return service.save(disciplina);
    }

    // d) Alterar os dados da disciplina
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> alterar(@PathVariable Integer id, @RequestBody Disciplina novaDisciplina) {
        try {
            Disciplina updatedDisciplina = service.update(id, novaDisciplina);
            return ResponseEntity.ok(updatedDisciplina);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // e) Excluir a disciplina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

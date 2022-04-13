package br.com.zup.handora.delecaoregistroid3.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.handora.delecaoregistroid3.models.Autor;
import br.com.zup.handora.delecaoregistroid3.models.AutorDTO;
import br.com.zup.handora.delecaoregistroid3.repositories.AutorRepository;

@RestController
@RequestMapping(AutorController.BASE_URI)
public class AutorController {

    public final static String BASE_URI = "/autores";

    private final AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid AutorDTO autorDTO,
                                       UriComponentsBuilder ucb) {
        Autor autor = autorRepository.save(autorDTO.toModel());

        URI location = ucb.path(BASE_URI + "/{id}").buildAndExpand(autor.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Autor autor = autorRepository.findById(id)
                                     .orElseThrow(
                                         () -> new ResponseStatusException(
                                             HttpStatus.NOT_FOUND,
                                             "Não existe um autor com o id informado."
                                         )
                                     );

        autorRepository.delete(autor);

        return ResponseEntity.noContent().build();
    }

}

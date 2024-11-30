package com.tech.tripon.web.user;

import com.tech.tripon.domain.entity.Passagem;
import com.tech.tripon.infrastructure.security.annotation.RoleAdministrador;
import com.tech.tripon.service.PassagemService;
import com.tech.tripon.web.dto.PassagemEditarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/passagens")
@RestController
public class PassagemController {

    private final PassagemService passagemService;

    @GetMapping
    public ResponseEntity<List<Passagem>> getAll(){
        return ResponseEntity.ok(passagemService.all());
    }

    @RoleAdministrador
    @PostMapping
    public ResponseEntity<Passagem> save(@RequestBody Passagem novaPassagem){
        return ResponseEntity.ok(passagemService.saveNovaPassagem(novaPassagem));
    }

    @RoleAdministrador
    @PutMapping
    public ResponseEntity<Void> put(@RequestBody PassagemEditarDTO passagemDTO){
        passagemService.editar(passagemDTO);
        return ResponseEntity.noContent().build();
    }

    @RoleAdministrador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer passagemId){
        passagemService.delete(passagemId);
        return ResponseEntity.noContent().build();
    }

}

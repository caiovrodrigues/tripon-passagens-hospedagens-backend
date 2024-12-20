package com.tech.tripon.web.admin;

import com.tech.tripon.domain.entity.Usuario;
import com.tech.tripon.infrastructure.security.UserDetailsImpl;
import com.tech.tripon.infrastructure.security.annotation.RoleAdministrador;
import com.tech.tripon.infrastructure.security.annotation.RoleUsuario;
import com.tech.tripon.service.UsuarioService;
import com.tech.tripon.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    @RoleUsuario
    @GetMapping("/token")
    public ResponseEntity<UsuarioResponseDTO> findByToken(@AuthenticationPrincipal UserDetailsImpl userDetails){
        UsuarioResponseDTO usuarioFromToken = new UsuarioResponseDTO(userDetails.getUsuario());
        return ResponseEntity.ok(usuarioFromToken);
    }

    @RoleAdministrador
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> getAll(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails.getUsuario().getEmail());
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @RoleUsuario
    @GetMapping("/info")
    public ResponseEntity<UsuarioInfoResponseDTO> findUsuarioInfoByToken(@AuthenticationPrincipal UserDetailsImpl userDetails){
        UsuarioInfoResponseDTO usuarioInfoByToken = usuarioService.getUsuarioInfoByToken(userDetails.getUsuario());
        return ResponseEntity.ok(usuarioInfoByToken);
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody LoginRequest credenciais){
        return ResponseEntity.ok(usuarioService.logar(credenciais));
    }

    @PostMapping("/criar")
    public void usuariocreate (@RequestBody UsuarioCreate usuariocriar){
        usuarioService.criar(usuariocriar);

    }

    @RoleAdministrador
    @GetMapping("/is-admin")
    public ResponseEntity<Void> isAdmin(){
        return ResponseEntity.noContent().build();
    }

    @RoleUsuario
    @GetMapping("/is-logado")
    public ResponseEntity<Void> isLogado(){
        return ResponseEntity.noContent().build();
    }
}



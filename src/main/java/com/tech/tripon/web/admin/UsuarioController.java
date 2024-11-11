package com.tech.tripon.web.admin;

import com.tech.tripon.domain.entity.Usuario;
import com.tech.tripon.infrastructure.security.UserDetailsImpl;
import com.tech.tripon.infrastructure.security.annotation.RoleAdministrador;
import com.tech.tripon.infrastructure.security.annotation.RoleUsuario;
import com.tech.tripon.service.UsuarioService;
import com.tech.tripon.service.dto.JwtTokenResponse;
import com.tech.tripon.service.dto.LoginRequest;
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

    @RoleAdministrador
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails);
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody LoginRequest credenciais){
        return ResponseEntity.ok(usuarioService.logar(credenciais));
    }

    @RoleUsuario
    @GetMapping("/admin/{id}")
    public Usuario isAdmin(@PathVariable Integer id){
        return usuarioService.findById(id);
    }

}

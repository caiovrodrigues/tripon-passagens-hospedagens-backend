package com.tech.tripon.service;

import com.tech.tripon.domain.entity.Usuario;
import com.tech.tripon.domain.repository.UsuarioRepository;
import com.tech.tripon.infrastructure.security.JwtService;
import com.tech.tripon.service.dto.JwtTokenResponse;
import com.tech.tripon.service.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public JwtTokenResponse logar(LoginRequest credenciais){
        Usuario usuario = usuarioRepository.findByEmail(credenciais.email()).orElseThrow();

        if (!passwordEncoder.matches(credenciais.password(), usuario.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(usuario);
        return new JwtTokenResponse(token);
    }

    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).orElseThrow();
    }

}

package com.tech.tripon.service;

import com.tech.tripon.domain.entity.Role;
import com.tech.tripon.domain.entity.Usuario;
import com.tech.tripon.domain.entity.UsuarioRoles;
import com.tech.tripon.domain.repository.RoleRepository;
import com.tech.tripon.domain.repository.UsuarioRepository;
import com.tech.tripon.infrastructure.security.JwtService;
import com.tech.tripon.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public JwtTokenResponse logar(LoginRequest credenciais){
        Usuario usuario = usuarioRepository.findByEmail(credenciais.email()).orElseThrow();

        if (!passwordEncoder.matches(credenciais.password(), usuario.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(usuario);
        return new JwtTokenResponse(token, new UsuarioResponseDTO(usuario));
    }

    public Usuario findById(Integer id){
        return usuarioRepository.findById(id).orElseThrow();
    }

    public void criar(UsuarioCreate usuariocriar) {
        if(!usuariocriar.password().equals(usuariocriar.passwordConfirm()))
            throw new RuntimeException("Senha e confirma senha não são iguais");

        Role roleUsuario = roleRepository.findByNome(Role.USER);
        String senhaCriptografada = passwordEncoder.encode(usuariocriar.password());

        Usuario usuario = Usuario.builder()
                .username(usuariocriar.username())
                .email(usuariocriar.email())
                .password(senhaCriptografada)
                .cpf(usuariocriar.cpf())
                .build();
        usuario.setUsuarioRoles(Set.of(new UsuarioRoles(usuario, roleUsuario)));
        usuarioRepository.save(usuario);
    }

    public UsuarioInfoResponseDTO getUsuarioInfoByToken(Usuario usuario) {
        return new UsuarioInfoResponseDTO(usuario);
    }
}

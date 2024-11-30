package com.tech.tripon.infrastructure;

import com.tech.tripon.domain.entity.*;
import com.tech.tripon.domain.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseInitialization {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final UsuarioRolesRepository usuarioRolesRepository;
    private final LocalidadeRepository localidadeRepository;
    private final PassagemRepository passagemRepository;
    private final CompanhiaAereaRepository companhiaAereaRepository;
    private final ComodidadeRepository comodidadeRepository;
    private final HotelRepository hotelRepository;
    private final CompraPassagemRepository compraPassagemRepository;
    private final PasswordEncoder passwordEncoder;


    public DatabaseInitialization(UsuarioRepository usuarioRepository, RoleRepository roleRepository, UsuarioRolesRepository usuarioRolesRepository, LocalidadeRepository localidadeRepository, PassagemRepository passagemRepository, CompanhiaAereaRepository companhiaAereaRepository, ComodidadeRepository comodidadeRepository, HotelRepository hotelRepository, CompraPassagemRepository compraPassagemRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.usuarioRolesRepository = usuarioRolesRepository;
        this.localidadeRepository = localidadeRepository;
        this.passagemRepository = passagemRepository;
        this.companhiaAereaRepository = companhiaAereaRepository;
        this.comodidadeRepository = comodidadeRepository;
        this.hotelRepository = hotelRepository;
        this.compraPassagemRepository = compraPassagemRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){
        var usuario1 = Usuario.builder().nome("Caio").sobrenome("Vinícius").username("caio").email("caio@gmail.com").cpf("02058228278").password(passwordEncoder.encode("123456")).build();
        var usuario2 = Usuario.builder().nome("Ana").sobrenome("Silva").username("ana").email("ana@gmail.com").cpf("64948142719").password(passwordEncoder.encode("123456")).build();
        var usuario3 = Usuario.builder().nome("Lucas").sobrenome("Sabino").username("lucas").email("lucas@gmail.com").cpf("27570825900").password(passwordEncoder.encode("123456")).build();
        var usuario4 = Usuario.builder().nome("Pedro").sobrenome("Henrique").username("pedro").email("pedro@gmail.com").cpf("59991918744").password(passwordEncoder.encode("123456")).build();
        usuarioRepository.saveAll(List.of(usuario1, usuario2, usuario3, usuario4));
        usuarioRepository.save(usuario3);

        var roleAdmin = Role.builder().nome("ADMIN").build();
        var roleUser = Role.builder().nome("USER").build();
        roleRepository.saveAll(List.of(roleAdmin, roleUser));

        var usuario1Role1 = UsuarioRoles.builder().usuario(usuario1).role(roleAdmin).build();
        var usuario1Role2 = UsuarioRoles.builder().usuario(usuario1).role(roleUser).build();

        var usuario2Role2 = UsuarioRoles.builder().usuario(usuario2).role(roleUser).build();
        var usuario3Role2 = UsuarioRoles.builder().usuario(usuario3).role(roleUser).build();
        var usuario4Role2 = UsuarioRoles.builder().usuario(usuario4).role(roleUser).build();

        usuarioRolesRepository.saveAll(List.of(usuario1Role1, usuario1Role2, usuario2Role2, usuario3Role2, usuario4Role2));

        var localidade1 = localidadeRepository.save(Localidade.builder().pais("Brasil").estado("São Paulo").cidade("Guarulhos").build());
        var localidade4 = localidadeRepository.save(Localidade.builder().pais("Brasil").estado("São Paulo").cidade("Congonhas").build());
        var localidade5 = localidadeRepository.save(Localidade.builder().pais("Brasil").estado("São Paulo").cidade("Ribeirão Preto").build());

        var localidade2 = localidadeRepository.save(Localidade.builder().pais("Brasil").estado("Rio de Janeiro").cidade("Arraial do Cabo").build());
        var localidade6 = localidadeRepository.save(Localidade.builder().pais("Brasil").estado("Rio de Janeiro").cidade("Búzios").build());

        var endereco = Endereco.builder().localizacao(localidade1).bairro("Polivalente").cep("12345-678").logradouro("Rua tal").numero("10").build();

        usuario1.setEndereco(endereco);
        usuarioRepository.save(usuario1);

        carregaCompanhiasAereasPassagensAndCompra(usuario1, localidade1, localidade2);
        carregaHoteisAndComodidades(localidade1);
    }

    private void carregaCompanhiasAereasPassagensAndCompra(Usuario usuario, Localidade origem, Localidade destino){
        var companhiaAerea = CompanhiaArea.builder().nome("Companhia A").avaliacao(4.3F).logotipoUrl("img.com").usuario(usuario).build();
        companhiaAereaRepository.save(companhiaAerea);

        IntStream.rangeClosed(1, 10).forEach(num -> {
            var passagem = Passagem.builder().companhiaAerea(companhiaAerea).origem(origem).destino(destino).dataIda(LocalDateTime.now().plusDays(5)).preco(239.99 + num).dataVolta(LocalDateTime.now().plusDays(10)).build();
            passagemRepository.save(passagem);
        });

        var passagem = Passagem.builder().companhiaAerea(companhiaAerea).origem(origem).destino(destino).dataIda(LocalDateTime.now().plusDays(5)).preco(499.99).dataVolta(LocalDateTime.now().plusDays(10)).build();
        passagemRepository.save(passagem);

        var compraPassagem = CompraPassagem.builder().cliente(usuario).passagem(passagem).quantidade(1).build();
        compraPassagemRepository.save(compraPassagem);
    }

    private void carregaHoteisAndComodidades(Localidade localidade){
        var comodidade1 = Comodidade.builder().nome("Piscina").build();
        var comodidade2 = Comodidade.builder().nome("Bar").build();
        var comodidade3 = Comodidade.builder().nome("Serviço de quarto").build();
        var comodidade4 = Comodidade.builder().nome("Restaurante").build();
        var comodidade5 = Comodidade.builder().nome("Sala de Jogos").build();
        var comodidade6 = Comodidade.builder().nome("Wi-fi").build();
        comodidadeRepository.saveAll(List.of(comodidade1, comodidade2, comodidade3, comodidade4, comodidade5, comodidade6));

        IntStream.rangeClosed(1,10).forEach(num -> {
            var hotel = Hotel.builder().nome("Hotel " + num).descricao("descrição do hotel" + num).price(BigDecimal.valueOf(199.99 + (num * 100L))).quartos(num).banheiros(1).estrelas(4).build();
            var hotelComodidade1 = HotelComodidades.builder().hotel(hotel).comodidade(comodidade1).build();
            var hotelComodidade2 = HotelComodidades.builder().hotel(hotel).comodidade(comodidade2).build();
            var hotelComodidade3 = HotelComodidades.builder().hotel(hotel).comodidade(comodidade3).build();
            var hotelComodidade4 = HotelComodidades.builder().hotel(hotel).comodidade(comodidade4).build();
            hotel.setComodidades(List.of(hotelComodidade1, hotelComodidade2, hotelComodidade3, hotelComodidade4));
            hotelRepository.save(hotel);
        });

    }

}

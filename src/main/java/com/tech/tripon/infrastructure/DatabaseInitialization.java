package com.tech.tripon.infrastructure;

import com.tech.tripon.domain.entity.*;
import com.tech.tripon.domain.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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


    public DatabaseInitialization(UsuarioRepository usuarioRepository, RoleRepository roleRepository, UsuarioRolesRepository usuarioRolesRepository, LocalidadeRepository localidadeRepository, PassagemRepository passagemRepository, CompanhiaAereaRepository companhiaAereaRepository, ComodidadeRepository comodidadeRepository, HotelRepository hotelRepository, CompraPassagemRepository compraPassagemRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.usuarioRolesRepository = usuarioRolesRepository;
        this.localidadeRepository = localidadeRepository;
        this.passagemRepository = passagemRepository;
        this.companhiaAereaRepository = companhiaAereaRepository;
        this.comodidadeRepository = comodidadeRepository;
        this.hotelRepository = hotelRepository;
        this.compraPassagemRepository = compraPassagemRepository;
    }

    @PostConstruct
    public void init(){
        var usuario1 = Usuario.builder().username("caio").email("caio@gmail.com").build();
        usuarioRepository.save(usuario1);

        var roleAdmin = Role.builder().nome("ADMIN").build();
        var roleBasic = Role.builder().nome("BASIC").build();
        roleRepository.saveAll(List.of(roleAdmin, roleBasic));

        var roleUsuario1 = UsuarioRoles.builder().usuario(usuario1).role(roleAdmin).build();
        var roleUsuario2 = UsuarioRoles.builder().usuario(usuario1).role(roleBasic).build();

        usuarioRolesRepository.saveAll(List.of(roleUsuario1, roleUsuario2));

        var localidade1 = localidadeRepository.save(Localidade.builder().pais("Brasil").estado("São Paulo").cidade("Guarulhos").build());
        var localidade2 = localidadeRepository.save(Localidade.builder().pais("Brasil").estado("Rio de Janeiro").cidade("Arraial do Cabo").build());

        var endereco = Endereco.builder().localizacao(localidade1).bairro("Polivalente").cep("12345-678").logradouro("Rua tal").numero("10").build();

        usuario1.setEndereco(endereco);
        usuarioRepository.save(usuario1);

        carregaCompanhiasAereasPassagensAndCompra(usuario1, localidade1, localidade2);
        carregaHoteisAndComodidades(localidade1);
    }

    private void carregaCompanhiasAereasPassagensAndCompra(Usuario usuario, Localidade origem, Localidade destino){
        var companhiaAerea = CompanhiaArea.builder().nome("Companhia A").avaliacao(4.3F).logotipoUrl("img.com").usuario(usuario).build();
        companhiaAereaRepository.save(companhiaAerea);

        var passagem = Passagem.builder().companhiaAerea(companhiaAerea).origem(origem).destino(destino).dataIda(LocalDateTime.now().plusDays(5)).preco(239.99).dataVolta(LocalDateTime.now().plusDays(10)).build();
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

        var hotel = Hotel.builder().nome("Hotel Casablanca").descricao("descrição do hotel").quartos(2).banheiros(1).estrelas(4).localizacao(localidade).build();
        var hotelComodidade1 = HotelComodidades.builder().hotel(hotel).comodidade(comodidade1).build();
        var hotelComodidade2 = HotelComodidades.builder().hotel(hotel).comodidade(comodidade2).build();
        var hotelComodidade3 = HotelComodidades.builder().hotel(hotel).comodidade(comodidade3).build();
        var hotelComodidade4 = HotelComodidades.builder().hotel(hotel).comodidade(comodidade4).build();
        hotel.setComodidades(List.of(hotelComodidade1, hotelComodidade2, hotelComodidade3, hotelComodidade4));
        hotelRepository.save(hotel);
    }

}
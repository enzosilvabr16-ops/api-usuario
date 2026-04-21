package br.com.cotiinformatica.api_usuarios.services;

import br.com.cotiinformatica.api_usuarios.components.CryptoComponent;
import br.com.cotiinformatica.api_usuarios.dtos.UsuarioRequestDto;
import br.com.cotiinformatica.api_usuarios.dtos.UsuarioResponseDto;
import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import br.com.cotiinformatica.api_usuarios.enums.Perfil;
import br.com.cotiinformatica.api_usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    public UsuarioResponseDto criarUsuario(UsuarioRequestDto request) {

        //criando um obj da classe entity
        var usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setPerfil(Perfil.OPERADOR);

        //salvar no bd
        usuarioRepository.save(usuario);

        //retornar os dados do bd
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                LocalDateTime.now(),
                usuario.getPerfil().toString()
        );

    }
}

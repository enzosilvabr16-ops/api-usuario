package br.com.cotiinformatica.api_usuarios.services;

import br.com.cotiinformatica.api_usuarios.components.CryptoComponent;
import br.com.cotiinformatica.api_usuarios.components.JwtTokenComponent;

import br.com.cotiinformatica.api_usuarios.dtos.*;

import br.com.cotiinformatica.api_usuarios.dtos.AutenticarRequestDto;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarResponseDto;
import br.com.cotiinformatica.api_usuarios.dtos.UsuarioRequestDto;
import br.com.cotiinformatica.api_usuarios.dtos.UsuarioResponseDto;

import br.com.cotiinformatica.api_usuarios.entities.Usuario;
import br.com.cotiinformatica.api_usuarios.enums.Perfil;
import br.com.cotiinformatica.api_usuarios.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.api_usuarios.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.api_usuarios.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CryptoComponent cryptoComponent;

    @Autowired
    private JwtTokenComponent jwtTokenComponent;

    public UsuarioResponseDto criarUsuario(UsuarioRequestDto request) {

        if(usuarioRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException();
        }

        //criando um obj da classe entity
        var usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(cryptoComponent.getSha256(request.senha()));
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

    public AutenticarResponseDto autenticarUsuario(AutenticarRequestDto request) {

        //capturar o email e senha enviados
        var email = request.email();
        var senha = cryptoComponent.getSha256(request.senha());

        //buscar o usuario no bd através do email e senha
        var usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        //verficar se o usuario não foi encontrado
        if(usuario == null) {
            throw new AcessoNegadoException();
        }

        var token = jwtTokenComponent.getToken(usuario.getId(), usuario.getEmail(), usuario.getPerfil().toString());

        return new AutenticarResponseDto(
          usuario.getId(),
          usuario.getNome(),
          usuario.getEmail(),
          usuario.getPerfil().toString(),
          LocalDateTime.now(),
          token
        );
    }


    public DadosUsuarioResponseDto ObterDadosUsuario(UUID id) {

        //consultar o usuarui no bd atraves do id
        var usuario = usuarioRepository.findById(id).get();

        //return data to user
        return new DadosUsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil().toString()
        );
    }



}

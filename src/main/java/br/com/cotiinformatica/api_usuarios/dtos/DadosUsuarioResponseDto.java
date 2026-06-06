package br.com.cotiinformatica.api_usuarios.dtos;

import br.com.cotiinformatica.api_usuarios.enums.Perfil;

import java.util.UUID;

public record DadosUsuarioResponseDto(
        UUID id,
        String nome,
        String email,
        Perfil perfil
) {
}

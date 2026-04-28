package br.com.cotiinformatica.api_usuarios.dtos;

public record AutenticarRequestDto(
        String email,
        String senha
) {
}

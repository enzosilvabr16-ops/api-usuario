package br.com.cotiinformatica.api_usuarios.controllers;

<<<<<<< HEAD
import br.com.cotiinformatica.api_usuarios.components.JwtTokenComponent;
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarRequestDto;
import br.com.cotiinformatica.api_usuarios.dtos.DadosUsuarioResponseDto;
=======
import br.com.cotiinformatica.api_usuarios.dtos.AutenticarRequestDto;
>>>>>>> 825fc313da19b89b673530b404eaaa5100de2667
import br.com.cotiinformatica.api_usuarios.dtos.UsuarioRequestDto;
import br.com.cotiinformatica.api_usuarios.exceptions.AcessoNegadoException;
import br.com.cotiinformatica.api_usuarios.exceptions.EmailJaCadastradoException;
import br.com.cotiinformatica.api_usuarios.services.UsuarioService;
<<<<<<< HEAD
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 825fc313da19b89b673530b404eaaa5100de2667

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

<<<<<<< HEAD
    @Autowired
    private JwtTokenComponent jwtTokenComponent;

=======
>>>>>>> 825fc313da19b89b673530b404eaaa5100de2667
    @PostMapping("criar")
    public ResponseEntity<?> postCriarUsuario(@RequestBody UsuarioRequestDto request) {

        try {
            var response = usuarioService.criarUsuario(request);
            return ResponseEntity.status(201).body(response);
        }

        catch (EmailJaCadastradoException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("autenticar")
    public ResponseEntity<?> postAutenticarUsuario(@RequestBody AutenticarRequestDto request) {

        try{
            var response = usuarioService.autenticarUsuario(request);
                return ResponseEntity.status(200).body(response);
            }

        catch (AcessoNegadoException e){
            return ResponseEntity.status(401).body(e. getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(500).body(e. getMessage());
        }
    }

<<<<<<< HEAD
    @GetMapping("obter-dados")
    public ResponseEntity<?> getObterDadosUsuario(HttpServletRequest http) {

        //extrair o id do usuario no token
        var id = jwtTokenComponent.getUserId(http);

        //Obter os dados do usuario autenticado
        var response = usuarioService.ObterDadosUsuario(id);

        //retornar os dados do usurio
        return ResponseEntity.status(200).build();
    }

=======
>>>>>>> 825fc313da19b89b673530b404eaaa5100de2667
}

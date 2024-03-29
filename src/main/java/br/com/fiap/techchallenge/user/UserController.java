package br.com.fiap.techchallenge.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Manipula dados de usuários")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Operation(summary = "Retorna um usuário específico",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Caso o usuário tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "404", description = "Caso o usuário não tenha sido encontrado na base")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<UserView> findById(@PathVariable Long id) {
        UserView userView = userService.findById(id);

        return ResponseEntity.ok(userView);
    }


    @Operation(summary = "Retorna uma lista de usuários",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sucesso no retorno da lista")
            }
    )
    @GetMapping
    ResponseEntity<List<UserView>> findAll() {
        List<UserView> usersView = userService.findAll();

        return ResponseEntity.ok(usersView);
    }


    @Operation(summary = "Cria um usuário na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Caso o usuário tenha sido encontrado na base"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação")
            }
    )
    @PostMapping
    ResponseEntity<UserView> create(@Valid @RequestBody UserForm userForm) {
        UserView userView = userService.create(userForm);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userView.id()).toUri();
        return ResponseEntity.created(uri).body(userView);
    }


    @Operation(summary = "Atualiza um usuário na base de dados",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Caso o usuário tenha sido encontrada na base"),
                    @ApiResponse(responseCode = "400", description = "Erros de validação"),
                    @ApiResponse(responseCode = "404", description = "Caso o usuário buscada não seja encontrado")
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<UserView> update(@Valid @RequestBody UserForm userForm, @PathVariable Long id) {
        UserView userView = userService.update(userForm, id);

        return ResponseEntity.ok(userView);
    }

    @Operation(summary = "Deleta um usuário na base de dados",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Ao deletar um usuário"),
                    @ApiResponse(responseCode = "404", description = "Caso o usuário buscada não seja encontrado")
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

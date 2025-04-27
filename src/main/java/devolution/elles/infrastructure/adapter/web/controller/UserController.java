package devolution.elles.infrastructure.adapter.web.controller;

import devolution.elles.domain.model.User;
import devolution.elles.domain.port.in.UserUseCase;
import devolution.elles.infrastructure.adapter.db.entity.UserEntity;
import devolution.elles.infrastructure.adapter.db.repository.UserDetailRepository;
import devolution.elles.infrastructure.adapter.web.dto.AuthenticationResponse;
import devolution.elles.infrastructure.adapter.web.dto.LoginDto;
import devolution.elles.infrastructure.adapter.web.dto.UserRequestDto;
import devolution.elles.infrastructure.adapter.web.dto.UserResponseDto;
import devolution.elles.infrastructure.adapter.web.mapper.UserDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;
    private final UserDetailRepository userSecurityAdapter;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController(UserUseCase userUseCase, UserDetailRepository userSecurityAdapter) {
        this.userUseCase = userUseCase;
        this.userSecurityAdapter = userSecurityAdapter;
    }

    @PostMapping()
    @Operation(summary = "Créer un nouvel utilisateur")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto userRequestDto) {
        User user = UserDtoMapper.toDomain(userRequestDto);
        this.userUseCase.save(user);
        UserEntity saved = this.userSecurityAdapter.save(user, userRequestDto.password(), userRequestDto.role());
        URI location = URI.create("/users/" + saved.getId());

        return ResponseEntity
                .created(location)
                .body(UserDtoMapper.toUserResponseDto(saved));
    }

    @GetMapping()
    @Operation(summary = "Récupérer tous les utilisateurs")
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users = this.userSecurityAdapter.findAll();
        URI location = URI.create("/users/");
        return ResponseEntity
                .created(location)
                .body(users);
    }

    @PostMapping(path = "login")
    @Operation(summary = "Génération de token et connexion")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) {
        UserEntity user = this.userSecurityAdapter.findByEmail(loginDto.username());
        if (user == null) throw new UsernameNotFoundException("User not found");
        if (!Objects.equals(user.getPassword(), loginDto.password()))
            throw new UsernameNotFoundException("Mot de passe incorrect");

        String token = UUID.randomUUID().toString();
        URI location = URI.create("/users/login");
        AuthenticationResponse response = new AuthenticationResponse(token, user);
        return ResponseEntity
                .created(location)
                .body(response);
    }
}

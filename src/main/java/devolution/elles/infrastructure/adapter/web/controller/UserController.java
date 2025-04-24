package devolution.elles.infrastructure.adapter.web.controller;

import devolution.elles.domain.model.User;
import devolution.elles.domain.port.in.UserUseCase;
import devolution.elles.infrastructure.adapter.db.entity.UserEntity;
import devolution.elles.infrastructure.adapter.db.repository.UserDetailRepository;
import devolution.elles.infrastructure.adapter.web.dto.LoginDto;
import devolution.elles.infrastructure.adapter.web.dto.UserRequestDto;
import devolution.elles.infrastructure.adapter.web.dto.UserResponseDto;
import devolution.elles.infrastructure.adapter.web.mapper.UserDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

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

    @PostMapping(path = "register")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto userRequestDto) {
        User user = UserDtoMapper.toDomain(userRequestDto);
        this.userUseCase.save(user);
        UserEntity saved = this.userSecurityAdapter.save(user, userRequestDto.password(), userRequestDto.role());
        URI location = URI.create("/users/" + saved.getId());

        return ResponseEntity
                .created(location)
                .body(UserDtoMapper.toUserResponseDto(saved));
    }

    @PostMapping(path = "login")
    public Map<String, String> login(@RequestBody LoginDto loginDto) {
//        Authentication authenticate = this.authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
//        System.out.println("User Authenticated ===>" + authenticate.isAuthenticated());
//        System.out.println(loginDto.password());
        return null;
    }

}

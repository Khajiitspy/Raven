package Graveyard.services;

import lombok.RequiredArgsConstructor;
import Graveyard.config.JwtService;
import Graveyard.data.dto.account.UserRegisterDTO;
import Graveyard.data.dto.account.UserItemDTO;
import Graveyard.data.dto.account.LoginDto;
import Graveyard.data.dto.account.AuthResponseDto;
import Graveyard.data.mappers.CountryMapper;
import Graveyard.data.mappers.UserMapper;
import Graveyard.entities.account.RoleEntity;
import Graveyard.entities.account.UserEntity;
import Graveyard.repository.IRoleRepository;
import Graveyard.repository.IUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;
    private final FileService fileService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public UserItemDTO registerUser(UserRegisterDTO dto) {
        UserEntity user = userMapper.fromRegisterDTO(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        var file = dto.getImageFile();
        if (file != null) {
            String fileName = fileService.load(file);
            user.setImage(fileName);
        }

        Optional<RoleEntity> userRoleOpt = roleRepository.findByName("User");

        if (userRoleOpt.isPresent()) {
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(userRoleOpt.get());
            user.setRoles(roles);
        }

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public List<UserEntity> GetAllUsers() {
        return userRepository.findAll();
    }

    public AuthResponseDto login(LoginDto request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var isValid = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!isValid) {
            throw new UsernameNotFoundException("User not found");
        }
        var jwtToekn = jwtService.generateAccessToken(user);
        return AuthResponseDto.builder()
                .token(jwtToekn)
                .build();
    }
}

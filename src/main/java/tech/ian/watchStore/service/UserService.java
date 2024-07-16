package tech.ian.watchStore.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.ian.watchStore.dto.UserRequestPasswordDto;
import tech.ian.watchStore.dto.UserResponse;
import tech.ian.watchStore.dto.UserResponsePassword;
import tech.ian.watchStore.entity.UserEntity;
import tech.ian.watchStore.repository.UserRepository;
import tech.ian.watchStore.util.RandomString;

import java.io.UnsupportedEncodingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public UserResponse registerUser(UserEntity user) throws MessagingException, UnsupportedEncodingException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Esse email ja existe");
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);

            UserEntity userSaved = userRepository.save(user);

            UserResponse userResponse = new UserResponse(
                    userSaved.getId(),
                    userSaved.getName(),
                    userSaved.getEmail(),
                    userSaved.getPassword());

            mailService.sendVerificationEmail(user);
            return userResponse;
        }
    }

    public boolean verify(String verificationCode) {
        UserEntity user = userRepository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }
    }

    public UserResponsePassword changePassword(String email, UserRequestPasswordDto userRequestPasswordDto) {
        UserDetails userDetails = userRepository.findByEmail(email);

        if (userDetails == null) {
            throw new RuntimeException("E-mail não encontrado");
        }

        // Converte UserDetails para UserEntity
        UserEntity user = (UserEntity) userDetails;

        // Codifica a nova senha
        String encodedPassword = passwordEncoder.encode(userRequestPasswordDto.password());
        user.setPassword(encodedPassword);

        // Salva a nova senha no banco de dados
        UserEntity userSaved = userRepository.save(user);

        // Retorna a resposta com a nova senha codificada
        return new UserResponsePassword(userSaved.getPassword());
    }


}

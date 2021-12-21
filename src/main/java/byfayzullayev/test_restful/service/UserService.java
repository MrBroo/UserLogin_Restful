package byfayzullayev.test_restful.service;

import byfayzullayev.test_restful.entity.UserEntity;
import byfayzullayev.test_restful.model.receive.SingInReceiveModel;
import byfayzullayev.test_restful.model.receive.SingUpReceiveModel;
import byfayzullayev.test_restful.model.response.ApiResponse;
import byfayzullayev.test_restful.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements BaseService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public ApiResponse addUser(
            SingUpReceiveModel singUpReceiveModel
    ) {
        Optional<UserEntity> optionalUserEntity =
                userRepository.findByUsername(singUpReceiveModel.getUsername());
        if (optionalUserEntity.isPresent())
            return USER_EXIST;

        UserEntity userEntity = new UserEntity();
        userEntity.setName(singUpReceiveModel.getFullName());
        userEntity.setUsername(singUpReceiveModel.getUsername());
        userEntity.setPassword(singUpReceiveModel.getPassword());
        userRepository.save(userEntity);
        return SUCCESS;
    }


    public ApiResponse login(SingInReceiveModel userSingInReceiveModel) {
        Optional<UserEntity> optionalUserEntity =
                userRepository.findByUsername(userSingInReceiveModel.getUsername());
        if (optionalUserEntity.isEmpty())
            return USER_NOT_FOUND;


        String token = this.generateToken(optionalUserEntity.get());
        SUCCESS.setData(token);
        return SUCCESS;
    }
    private String generateToken(UserEntity userEntity) {
        try {
            String token = Jwts.builder().signWith(SignatureAlgorithm.HS512, jwtSecretKey)

                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86_000L))
                    .setSubject(objectMapper.writeValueAsString(userEntity))
                    .compact();
            return token;
        } catch (Exception e) {
            return null;
        }

    }

}


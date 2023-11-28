package com.hakan.service;

import com.hakan.dto.request.UserLoginRequestDto;
import com.hakan.dto.request.UserRegisterRequestDto;
import com.hakan.dto.request.UserSaveRequestDto;
import com.hakan.dto.response.UserLoginResponseDto;
import com.hakan.dto.response.UserRegisterResponseDto;
import com.hakan.entity.User;
import com.hakan.exception.BlogException;
import com.hakan.exception.ErrorType;
import com.hakan.mapper.IUserMapper;
import com.hakan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserRegisterResponseDto register(UserRegisterRequestDto dto) {
        User user = null;
        if(!userRepository.existsByEmail(dto.getEmail())){
            if(dto.getPassword().equals(dto.getRePassword())){

                user = IUserMapper.INSTANCE.dtoToUser(dto);
                userRepository.save(user)
                ;
            }else {
                throw new BlogException(ErrorType.INVALID_PASSWORD);
            }
        }else {
            throw new BlogException(ErrorType.EMAIL_ALREADY_EXSIST);
        }
        return IUserMapper.INSTANCE.userToDto(user);
    }

    public UserLoginResponseDto login(UserLoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if(optionalUser.isPresent()){
            return IUserMapper.INSTANCE.userToLoginDto(optionalUser.get());
        }else {
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void update(UserSaveRequestDto dto){
        Optional<User> optionalUser = findById(dto.getId());
        if(optionalUser.isPresent()){
            User user = User.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .surname(dto.getSurname())
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .build();

            userRepository.save(user);
        }else{
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public void deleteById(Long id){
        Optional<User> optionalUser = findById(id);
        if(optionalUser.isPresent()){
            userRepository.deleteById(id);
        } else{
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<User> findByNameIgnoreCase(String name) {
        Boolean varMi = userRepository.existsByName(name);
        if(varMi){
            return userRepository.findByNameIgnoreCase(name);
        }else{
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }

    }

    public List<User> findAllByNameStartsWith(String word) {
        String newWord=word+"%";
        return userRepository.findAllByNameStartsWith(newWord);
    }

}

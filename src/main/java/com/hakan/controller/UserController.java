package com.hakan.controller;

import com.hakan.dto.request.UserLoginRequestDto;
import com.hakan.dto.request.UserRegisterRequestDto;
import com.hakan.dto.request.UserSaveRequestDto;
import com.hakan.dto.response.UserLoginResponseDto;
import com.hakan.dto.response.UserRegisterResponseDto;
import com.hakan.entity.User;
import com.hakan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import static com.hakan.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+USER)
public class UserController {

    private final UserService userService;

    @PostMapping(REGISTER)
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody @Validated UserRegisterRequestDto dto){
        return new ResponseEntity(userService.register(dto), HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto dto) {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.login(dto));
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(UserSaveRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok("Kullanıcı güncelleme işlemi tamamlanmıştır...");
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("Kullanıcı silme işlemi tamamlanmıştır.");
    }

    @GetMapping(FINDBYNAME)
    public ResponseEntity<List<User>> findByNameIgnoreCase(String name){
        return ResponseEntity.ok(userService.findByNameIgnoreCase(name));
    }

    @GetMapping(FINDALLBYSTARTSWITH)
    public ResponseEntity<List<User>>findAllByNameStartsWith(@PathVariable String word){
        return ResponseEntity.ok(userService.findAllByNameStartsWith(word));
    }

}

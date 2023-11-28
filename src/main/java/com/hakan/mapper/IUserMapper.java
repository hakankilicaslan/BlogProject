package com.hakan.mapper;


import com.hakan.dto.request.UserRegisterRequestDto;
import com.hakan.dto.request.UserSaveRequestDto;
import com.hakan.dto.response.UserLoginResponseDto;
import com.hakan.dto.response.UserRegisterResponseDto;
import com.hakan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User dtoToUser(UserRegisterRequestDto dto);

    UserRegisterResponseDto userToDto(User user);

    UserLoginResponseDto userToLoginDto(User user);

    User saveRequestDtoToUser(UserSaveRequestDto dto);

    UserSaveRequestDto userToSaveRequestDto(User user);



}

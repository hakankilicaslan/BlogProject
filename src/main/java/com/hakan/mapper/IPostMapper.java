package com.hakan.mapper;




import com.hakan.dto.request.PostRequestDto;
import com.hakan.dto.request.PostSaveRequestDto;
import com.hakan.dto.request.UserSaveRequestDto;
import com.hakan.entity.Post;
import com.hakan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPostMapper {

    IPostMapper INSTANCE = Mappers.getMapper(IPostMapper.class);

    @Mapping(source = "userId", target = "user.id")
    Post postDTOToPost(PostRequestDto postDTO);

    @Mapping(source = "user.id", target = "userId")
    PostRequestDto postToPostDTO(Post post);

    @Mapping(source = "user.id", target = "userId")
    PostSaveRequestDto postToSaveRequestDto(Post post);

    @Mapping(source = "userId", target = "user.id")
    Post saveRequestDtoToPost(PostSaveRequestDto dto);

}

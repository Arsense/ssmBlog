package api.mapping;


import com.we.weblog.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {


    @Select({"select * from t_user where username=#{name}"})
    User searchUserByName(@Param("name") String username);
}

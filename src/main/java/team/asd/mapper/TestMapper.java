package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    void insertValue(String value);
}

package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Occupy;
import com.seucourse.hotelmanage.provider.OccupySQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OccupyMapper {
    @Insert("INSERT INTO occupy(roomId, name, certId) VALUES (#{roomId}, #{name}, #{certId})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertOccupy(Occupy occupy);

    @Delete("DELETE FROM occupy WHERE id = #{id}")
    void deleteOccupy(Integer id);

    @SelectProvider(type = OccupySQLProvider.class, method = "createSelectSQL")
    List<Occupy> selectOccupy(Occupy occupy);

    @UpdateProvider(type = OccupySQLProvider.class, method = "createUpdateSQL")
    void updateOccupy(Occupy occupy);
}

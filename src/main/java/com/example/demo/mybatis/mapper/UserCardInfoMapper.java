package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.model.UserCardInfo;
import com.example.demo.mybatis.model.UserCardInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCardInfoMapper {
    @SelectProvider(type=UserCardInfoSqlProvider.class, method="countByExample")
    long countByExample(UserCardInfoExample example);

    @DeleteProvider(type=UserCardInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserCardInfoExample example);

    @Delete({
        "delete from user_card_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_card_info (user_id, card_id, ",
        "times, left_money, ",
        "create_time, update_time)",
        "values (#{userId,jdbcType=INTEGER}, #{cardId,jdbcType=INTEGER}, ",
        "#{times,jdbcType=INTEGER}, #{leftMoney,jdbcType=DOUBLE}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(UserCardInfo record);

    @InsertProvider(type=UserCardInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(UserCardInfo record);

    @SelectProvider(type=UserCardInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="card_id", property="cardId", jdbcType=JdbcType.INTEGER),
        @Result(column="times", property="times", jdbcType=JdbcType.INTEGER),
        @Result(column="left_money", property="leftMoney", jdbcType=JdbcType.DOUBLE),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<UserCardInfo> selectByExample(UserCardInfoExample example);

    @Select({
        "select",
        "id, user_id, card_id, times, left_money, create_time, update_time",
        "from user_card_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="card_id", property="cardId", jdbcType=JdbcType.INTEGER),
        @Result(column="times", property="times", jdbcType=JdbcType.INTEGER),
        @Result(column="left_money", property="leftMoney", jdbcType=JdbcType.DOUBLE),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    UserCardInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserCardInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserCardInfo record, @Param("example") UserCardInfoExample example);

    @UpdateProvider(type=UserCardInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserCardInfo record, @Param("example") UserCardInfoExample example);

    @UpdateProvider(type=UserCardInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserCardInfo record);

    @Update({
        "update user_card_info",
        "set user_id = #{userId,jdbcType=INTEGER},",
          "card_id = #{cardId,jdbcType=INTEGER},",
          "times = #{times,jdbcType=INTEGER},",
          "left_money = #{leftMoney,jdbcType=DOUBLE},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserCardInfo record);
}
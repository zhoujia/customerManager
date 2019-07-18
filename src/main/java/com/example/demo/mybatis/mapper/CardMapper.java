package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.model.Card;
import com.example.demo.mybatis.model.CardExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardMapper {
    @SelectProvider(type=CardSqlProvider.class, method="countByExample")
    long countByExample(CardExample example);

    @DeleteProvider(type=CardSqlProvider.class, method="deleteByExample")
    int deleteByExample(CardExample example);

    @Delete({
        "delete from card",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into card (card_name, card_level, ",
        "description, card_times, ",
        "discount, start_date, ",
        "end_date, create_time, ",
        "update_time)",
        "values (#{cardName,jdbcType=VARCHAR}, #{cardLevel,jdbcType=TINYINT}, ",
        "#{description,jdbcType=VARCHAR}, #{cardTimes,jdbcType=INTEGER}, ",
        "#{discount,jdbcType=DOUBLE}, #{startDate,jdbcType=TIMESTAMP}, ",
        "#{endDate,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Card record);

    @InsertProvider(type=CardSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Card record);

    @SelectProvider(type=CardSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="card_name", property="cardName", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_level", property="cardLevel", jdbcType=JdbcType.TINYINT),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_times", property="cardTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="discount", property="discount", jdbcType=JdbcType.DOUBLE),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Card> selectByExample(CardExample example);

    @Select({
        "select",
        "id, card_name, card_level, description, card_times, discount, start_date, end_date, ",
        "create_time, update_time",
        "from card",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="card_name", property="cardName", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_level", property="cardLevel", jdbcType=JdbcType.TINYINT),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_times", property="cardTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="discount", property="discount", jdbcType=JdbcType.DOUBLE),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Card selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CardSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Card record, @Param("example") CardExample example);

    @UpdateProvider(type=CardSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Card record, @Param("example") CardExample example);

    @UpdateProvider(type=CardSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Card record);

    @Update({
        "update card",
        "set card_name = #{cardName,jdbcType=VARCHAR},",
          "card_level = #{cardLevel,jdbcType=TINYINT},",
          "description = #{description,jdbcType=VARCHAR},",
          "card_times = #{cardTimes,jdbcType=INTEGER},",
          "discount = #{discount,jdbcType=DOUBLE},",
          "start_date = #{startDate,jdbcType=TIMESTAMP},",
          "end_date = #{endDate,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Card record);
}
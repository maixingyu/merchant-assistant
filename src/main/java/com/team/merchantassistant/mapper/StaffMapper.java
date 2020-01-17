package com.team.merchantassistant.mapper;

import com.team.merchantassistant.bean.Staff;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StaffMapper {

    /**
     * 根据openid查找员工列表
     *
     * @param openid 微信小程序用户唯一标识
     * @return List<Staff>
     */
    @Select("select id,avatar,name,sex,tel,id_card,address,remarks from staff where openid=#{openid}")
    List<Staff> findStaffByOpenid(@Param("openid") String openid);

    /**
     * 添加staff信息
     *
     * @param staff 员工信息
     */
    @Insert("insert into staff (avatar,name,sex,tel,id_card,address,remarks,openid) " +
            "values(#{s.avatar},#{s.name},#{s.sex},#{s.tel},#{s.idCard},#{s.address},#{s.remarks},#{s.openid})")
    void addStaff(@Param("s") Staff staff);

    /**
     * 根据id修改头像名
     *
     * @param avatar 头像名
     * @param id     员工信息的唯一标识
     */
    @Update("update staff set avatar=#{avatar} where id=#{id}")
    void updateAvatar(@Param("avatar") String avatar, Integer id);

    /**
     * 根据id修改员工名字
     *
     * @param name 员工名字
     * @param id   员工信息的唯一标识
     */
    @Update("update staff set name=#{name} where id=#{id}")
    void updateName(@Param("name") String name, Integer id);

    /**
     * 根据id修改员工电话
     *
     * @param tel 员工电话
     * @param id  员工信息的唯一标识
     */
    @Update("update staff set tel=#{tel} where id=#{id}")
    void updateTel(@Param("tel") String tel, Integer id);

    /**
     * 根据id修改员工身份证
     *
     * @param idCard 员工身份证
     * @param id     员工信息的唯一标识
     */
    @Update("update staff set id_card=#{id_card} where id=#{id}")
    void updateIdCard(@Param("id_card") String idCard, Integer id);

    /**
     * 根据id修改员工地址
     *
     * @param address 员工地址
     * @param id      员工信息的唯一标识
     */
    @Update("update staff set address=#{address} where id=#{id}")
    void updateAddress(@Param("address") String address, Integer id);

    /**
     * 根据id修改员工备注信息
     *
     * @param remarks 员工备注信息
     * @param id      员工信息的唯一标识
     */
    @Update("update staff set remarks=#{remarks} where id=#{id}")
    void updateRemarks(@Param("remarks") String remarks, Integer id);

    /**
     * 根据id修改员工性别
     *
     * @param sex 员工性别
     * @param id  员工信息的唯一标识
     */
    @Update("update staff set sex=#{sex} where id=#{id}")
    void updateSex(@Param("sex") String sex, Integer id);

    /**
     * 根据id删除员工
     *
     * @param id 员工信息的唯一标识
     */
    @Delete("delete from staff where id=#{id}")
    void deleteStaffById(@Param("id") Integer id);
}

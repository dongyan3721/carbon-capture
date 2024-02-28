package com.carboncapture.carbon.mapper;

import com.carboncapture.carbon.entity.CarbonUerBelong;
import com.carboncapture.carbon.entity.CarbonUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper

public interface CarbonUserMapper {

    /**
     * 根据用户名和密码查询用户
     * @return
     */
    @Select("select * from user where user_id=#{userId} and nickname=#{nickname} ")
    CarbonUser getByIdAndName(CarbonUser c);


    /**
     * 录入用户信息
     * @param user
     */

    @Insert("insert into t_carbon_user(user_id, user_type, email,password,nickname,avatar) values(#{userId},#{userType},#{email},#{password},#{nickname},#{avatar})")
    void insert(CarbonUser user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from t_carbon_user where user_id=#{id}")
    CarbonUser selectUserById(String id);

    /**
     * 修改用户的邮箱和昵称
     * @param user
     * @return
     */
    @Update("update t_carbon_user set nickname=#{nickname},email=#{email} where user_id=#{userId}")
    void changeUser(CarbonUser user);

    /**
     * 通过邮箱地址查询用户
     * @param e
     * @return
     */
    @Select("select * from t_carbon_user where email=#{e}")
    CarbonUser selectByEmail(String e);

    /**
     * 根据id查询到用户并且修改密码
     * @param id
     * @param password
     */
    @Update("update t_carbon_user set password=#{password} where user_id=#{id}")
    void changeCode(String id, String password);

    /**
     * 根据用户id查询用户所属地
     * @param id
     * @return
     */
    @Select("select * from t_carbon_user_belong where user_id=#{userId}")
    CarbonUerBelong getBelongById(String id);

    @Insert("insert into t_carbon_user_belong(user_id, user_nickname, province_id,province_name,industry,industry_process,blur_carbon_output) values(#{userId},#{userNickname},#{provinceId},#{provinceName},#{industry},#{industryProcess},#{blurCarbonOutput})")
    void addUserBelong(CarbonUerBelong c);

    @Update("update t_carbon_user_belong set user_nickname=#{userNickname},province_id=#{provinceId},province_name=#{provinceName},industry=#{industry},industry_process=#{industryProcess},blur_carbon_output=#{blurCarbonOutput}")
    void changeUserBelong(CarbonUerBelong c);
}

package com.carboncapture.carbon.service.impl;

import com.carboncapture.carbon.entity.CarbonUerBelong;
import com.carboncapture.carbon.entity.CarbonUser;
import com.carboncapture.carbon.mapper.CarbonUserMapper;
import com.carboncapture.carbon.service.CarbonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarbonUserImpl implements CarbonUserService {
    @Autowired
    private CarbonUserMapper carbonUserMapper;
    @Override
    public CarbonUser login(CarbonUser c) {
        return carbonUserMapper.getByIdAndName(c);
    }

    @Override
    public void regist(CarbonUser user) {
         carbonUserMapper.insert(user);
    }

    @Override
    public CarbonUser getUserDetail(String id) {
        return carbonUserMapper.selectUserById(id);
    }

    @Override
    public int modifyUser(CarbonUser user) {
        //首先查询邮箱是否重复
        CarbonUser u=carbonUserMapper.selectByEmail(user.getEmail());
        if(u!=null){
            //邮箱重复
            return 1000;
        }
        //邮箱不重复,修改信息
        carbonUserMapper.changeUser(user);
        return 200;
    }

    @Override
    public void changeCode(String id, String password) {
        carbonUserMapper.changeCode(id,password);
    }

    @Override
    public CarbonUerBelong getBelongById(String id) {
        return carbonUserMapper.getBelongById(id);
    }

    @Override
    public void addUserBelong(CarbonUerBelong c) {
         carbonUserMapper.addUserBelong(c);
    }

    @Override
    public void changeUserBelong(CarbonUerBelong c) {
        carbonUserMapper.changeUserBelong(c);
    }

    @Override
    public CarbonUser selectUserByEmail(CarbonUser user) {
        return carbonUserMapper.selectUserByEmail(user);
    }
}

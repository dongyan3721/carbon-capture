package com.carboncapture.carbon.service;


import com.carboncapture.carbon.entity.CarbonUerBelong;
import com.carboncapture.carbon.entity.CarbonUser;

public interface CarbonUserService {
    CarbonUser login(CarbonUser c);

    void regist(CarbonUser user);

    CarbonUser getUserDetail(String id);

    int modifyUser(CarbonUser user);

    void changeCode(String id,String password);

    CarbonUerBelong getBelongById(String id);

    void addUserBelong(CarbonUerBelong c);

    void changeUserBelong(CarbonUerBelong c);

    public CarbonUser selectUserByEmail(CarbonUser user);
}

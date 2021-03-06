package cn.edu.ecnu.projectmanager.service.impl;

import cn.edu.ecnu.projectmanager.entity.Admin;
import cn.edu.ecnu.projectmanager.mapper.AdminMapper;
import cn.edu.ecnu.projectmanager.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) throws Exception {
        if(username.isEmpty()){
            throw new Exception("用户名不得为空");
        }
        if(password.isEmpty()){
            throw new Exception("密码不得为空");
        }
        Admin user = adminMapper.getAdminByUsername(username);
        if(user == null){
            log.error("admin: {} doesn't exist.", username);
            throw new Exception("用户不存在");
        }else if(!user.getPassword().equals(password)){
            log.error("admin: {} password wrong.", username);
            throw new Exception("密码错误");
        }
        return user;
    }

    @Override
    public int update(Admin admin) {
        if(admin == null){
            log.error("update admin error: empty parameter");
            return -1;
        }
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public Admin findByUsername(String username) {
        return adminMapper.getAdminByUsername(username);
    }

    @Override
    public Admin findById(Integer id) {
        return adminMapper.getAdminById(id);
    }

    @Override
    public List<Admin> findByName(String name) {
        return adminMapper.getAdminByName(name);
    }

    @Override
    public List<Admin> listAll() {
        return adminMapper.getAllAdmin();
    }

    @Override
    public boolean isExisted(String username) {
        Admin admin = findByUsername(username);
        return admin != null;
    }
}

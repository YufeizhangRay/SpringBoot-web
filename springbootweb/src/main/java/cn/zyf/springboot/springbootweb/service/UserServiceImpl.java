package cn.zyf.springboot.springbootweb.service;

import cn.zyf.springboot.springbootweb.dao.UserDao;
import cn.zyf.springboot.springbootweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) {

        userDao.save(user);
    }

    @Override
    public void delete(int id) {

        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {

        userDao.saveAndFlush(user);
    }

    @Override
    public User get(int id) {

        return userDao.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {

        return userDao.findAll();
    }

    @Override
    public User login(User user) {
        //判断登录的基本逻辑，登录成功弄返回一个登陆成功的User对象，登录失败返回一个null
        //1.根据用户名查询有没有对应的记录
        User u = userDao.getByUsername(user.getUsername());
        if (u == null) {
            return null;
        }
        //2.判断密码是否正确
        if (!u.getPassword().equals(user.getPassword())) {
            return null;
        }
        return u;
    }

    @Override
    public List<User> getByUsernameLike(String searchByUsername) {
        return userDao.getByUsernameLike(searchByUsername);
    }
}

package cn.zyf.springboot.springbootweb.service;

import cn.zyf.springboot.springbootweb.model.User;

import java.util.List;

public interface UserService {

    //增
    public void add(User user);

    //删
    public void delete(int id);

    //改
    public void update(User user);

    //查
    public User get(int id);

    public List<User> getAllUsers();

    public User login(User user);

    public List<User> getByUsernameLike(String searchByUsername);

}

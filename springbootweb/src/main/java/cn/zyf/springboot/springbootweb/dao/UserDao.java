package cn.zyf.springboot.springbootweb.dao;

import cn.zyf.springboot.springbootweb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    /**
     * 默认接口中没有通过用户名查询用户记录的方法，自己做
     */
    public User getByUsername(String username);

    /**
     * 在dao层里建立模糊查询的接口
     */
    public List<User> getByUsernameLike(String searchByUsername);
}

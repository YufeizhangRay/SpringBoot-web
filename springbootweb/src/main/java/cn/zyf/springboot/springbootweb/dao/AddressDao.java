package cn.zyf.springboot.springbootweb.dao;

import cn.zyf.springboot.springbootweb.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address,Integer> {
}

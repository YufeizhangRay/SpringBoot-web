package cn.zyf.springboot.springbootweb.service;

import cn.zyf.springboot.springbootweb.model.Address;

import java.util.List;

public interface AddressService {

    //增
    public void add(Address address);

    //删
    public void delete(int id);

    //改
    public void update(Address address);

    //查
    public Address get(int id);

    public List<Address> getAllAddresses();

}

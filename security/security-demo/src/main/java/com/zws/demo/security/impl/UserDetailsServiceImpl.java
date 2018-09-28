package com.zws.demo.security.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@Data
public class UserDetailsServiceImpl implements UserDetailsService {


    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = passwordEncoder.encode("123456");
        return new User(username,password,true,true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

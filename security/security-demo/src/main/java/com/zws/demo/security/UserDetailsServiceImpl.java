package com.zws.demo.security;

import com.zws.demo.entity.dto.UserDto;
import com.zws.demo.entity.vo.UserVo;
import com.zws.demo.mapper.UserMapper;
import com.zws.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
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

@Component
public class UserDetailsServiceImpl implements UserDetailsService, UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.zws.demo.entity.po.User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    @Override
    public UserVo insert(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserVo userVo = new UserVo();
        userVo.setId(userMapper.insert(userDto).toString());
        userVo.setAge(userDto.getAge());
        userVo.setUsername(userDto.getUsername());
        userVo.setBirthDay(userDto.getBirthDay());
        return userVo;
    }
}

package com.zws.demo.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zws.demo.entity.dto.UserDto;
import com.zws.demo.entity.vo.UserVo;
import com.zws.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2018/9/28
 */
@RestController
@RequestMapping("/user")
@Api(value="/user", tags="用户模块")
public class UserController {

    @Autowired
    private UserService userDetailServiceImpl;

    @GetMapping("/{id:\\d+}")
    @ApiOperation(value = "查询用户详情")
    @JsonView(UserVo.PasswordUserView.class)
    public UserVo getUserInfo(@PathVariable String id){
       UserVo userVo =new UserVo();
       userVo.setId("1");
       userVo.setUsername("zws");
       userVo.setAge("20");
       userVo.setPassword("123456");
       userVo.setBirthDay(new Date());
       return userVo;
    }

    @GetMapping
    @ApiOperation(value = "用户列表查询")
    @JsonView(UserVo.SimpleUserView.class)
    public List<UserVo> getUserList(){
        List<UserVo> userVoList = new ArrayList<>();
        UserVo userVo =new UserVo();
        userVo.setId("1");
        userVo.setUsername("zws");
        userVo.setAge("20");
        userVo.setPassword("123456");
        userVo.setBirthDay(new Date());
        userVoList.add(userVo);
        return userVoList;
    }

    @PostMapping
    @ApiOperation(value = "用户添加")
    public UserVo createUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult){
         return userDetailServiceImpl.insert(userDto);
    }
}

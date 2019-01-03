package com.index.configurer;

import com.index.security.mapper.UserMapper;
import com.index.security.model.User;
import com.index.security.pojo.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Auther: Index
 * @Date: 2018/12/28 09:32
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception { //配置策略
        http.authorizeRequests().
                // 静态资源默认放行
                        antMatchers("/static/**").permitAll().
                antMatchers("/css/**").permitAll().
                antMatchers("/js/**").permitAll().
                antMatchers("/img/**").permitAll().
                antMatchers("/plugins/**").permitAll().
                // 系统首页对游客开放
                        antMatchers("/night/**").permitAll().
                // 视频子系统资源默认放行
                        antMatchers("/video/**").permitAll().
                antMatchers("/f/video/**").permitAll().
                // 图片子系统资源默认放行
                        antMatchers("/picture/**").permitAll().
                antMatchers("/f/picture/**").permitAll().
                anyRequest().authenticated().
                and().formLogin().permitAll();
    }

    @Component
    public class MyUserDetailsService implements UserDetailsService {
        private Logger logger = LoggerFactory.getLogger(getClass());

        @Autowired
        private UserMapper userMapper;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userMapper.findByUserName(username);
            if (user == null)
                throw new UsernameNotFoundException("用户不存在！");
            return new SecurityUser(user);
        }
    }
}

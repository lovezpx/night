package com.index;

import com.index.management.mapper.MenuMapper;
import com.index.management.model.Menu;
import com.index.video.mapper.VideoGroupMapper;
import com.index.video.pojo.VideoWallBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoTest {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private VideoGroupMapper videoGroupMapper;

    @Test
    @Rollback(false)
    public void test() {
        List<VideoWallBean> selectAll = videoGroupMapper.selectAll();

        List<VideoWallBean> selectByType = videoGroupMapper.selectByType("AsiaMosaic");

        List<VideoWallBean> selectByKey = videoGroupMapper.selectByKey("三上");
    }
}
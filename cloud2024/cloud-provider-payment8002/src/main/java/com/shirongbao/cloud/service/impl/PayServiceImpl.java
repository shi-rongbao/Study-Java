package com.shirongbao.cloud.service.impl;

import com.shirongbao.cloud.entities.Pay;
import com.shirongbao.cloud.mapper.PayMapper;
import com.shirongbao.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/3/10 15:11
 * @description:
 */
@Service
public class PayServiceImpl implements PayService {

    // @Autowired  // java17后不再推荐用这个
    @Resource
    private PayMapper payMapper;
    @Override
    public int add(Pay pay) {
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id) {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay) {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll() {
        return payMapper.selectAll();
    }
}

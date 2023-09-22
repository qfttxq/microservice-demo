package com.example.goodservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.comonbeans.entity.good.Good;
import com.example.goodservice.mapper.GoodMapper;
import com.example.goodservice.service.GoodService;
import org.springframework.stereotype.Service;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {
}

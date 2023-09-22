package com.example.payservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.comonbeans.entity.pay.Pay;
import com.example.payservice.mapper.PayMapper;
import com.example.payservice.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService {
}

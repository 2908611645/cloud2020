package com.springcloud.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao extends BaseMapper<PaymentDao> {
  public int create(Payment payment);
  public  Payment getPaymentById(@Param("id") Long id);
}

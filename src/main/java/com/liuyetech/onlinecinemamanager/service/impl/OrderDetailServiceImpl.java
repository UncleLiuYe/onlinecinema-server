package com.liuyetech.onlinecinemamanager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.OrderDetail;
import com.liuyetech.onlinecinemamanager.mapper.OrderDetailMapper;
import com.liuyetech.onlinecinemamanager.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
* @author LiuYe
* @description 针对表【tb_order_detail】的数据库操作Service实现
* @createDate 2023-02-20 19:07:56
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService {

}





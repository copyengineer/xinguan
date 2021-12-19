package com.xjb.newcrowncore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjb.newcrowncommon.model.TbLoginLog;
import com.xjb.newcrowncore.mapper.TbLoginLogMapper;
import com.xjb.newcrowncore.service.TbLoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author xjb
 * @since 2021-12-11
 */
@Service
public class TbLoginLogServiceImpl extends ServiceImpl<TbLoginLogMapper, TbLoginLog> implements TbLoginLogService {

}

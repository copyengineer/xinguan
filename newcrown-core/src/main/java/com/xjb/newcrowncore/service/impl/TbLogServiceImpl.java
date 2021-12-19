package com.xjb.newcrowncore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjb.newcrowncommon.model.TbLog;
import com.xjb.newcrowncore.mapper.TbLogMapper;
import com.xjb.newcrowncore.service.TbLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author xjb
 * @since 2021-12-11
 */
@Service
public class TbLogServiceImpl extends ServiceImpl<TbLogMapper, TbLog> implements TbLogService {

}

package yutong.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yutong.entity.YtOrder;
import yutong.mapper.YtOrderMapper;
import yutong.service.YtOrderService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 于童
 * @since 2020-02-07
 */
@Service
public class YtOrderServiceImpl extends ServiceImpl<YtOrderMapper, YtOrder> implements YtOrderService {

}

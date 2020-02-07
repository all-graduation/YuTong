package yutong.serviceImpl;

import yutong.entity.YtGame;
import yutong.mapper.YtGameMapper;
import yutong.service.YtGameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 于童
 * @since 2020-02-07
 */
@Service
public class YtGameServiceImpl extends ServiceImpl<YtGameMapper, YtGame> implements YtGameService {

}

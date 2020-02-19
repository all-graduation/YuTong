package yutong.serviceImpl;

import yutong.entity.YtPost;
import yutong.mapper.YtPostMapper;
import yutong.service.YtPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 于童
 * @since 2020-02-18
 */
@Service
public class YtPostServiceImpl extends ServiceImpl<YtPostMapper, YtPost> implements YtPostService {

}

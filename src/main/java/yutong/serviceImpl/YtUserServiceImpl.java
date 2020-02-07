package yutong.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yutong.entity.YtUser;
import yutong.mapper.YtUserMapper;
import yutong.service.YtUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 于童
 * @since 2020-02-07
 */
@Service
public class YtUserServiceImpl extends ServiceImpl<YtUserMapper, YtUser> implements YtUserService {

}

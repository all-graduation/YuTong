package yutong;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 表示取消数据源的自动配置
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@MapperScan(basePackages = {"yutong.mapper"})
public class ApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);


    }
}

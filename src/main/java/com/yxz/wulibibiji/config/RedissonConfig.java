package com.yxz.wulibibiji.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson.yml"));
        MasterSlaveServersConfig masterSlaveServersConfig = config.useMasterSlaveServers();
        masterSlaveServersConfig.setMasterConnectionMinimumIdleSize(10);
        masterSlaveServersConfig.setMasterConnectionPoolSize(64);
        masterSlaveServersConfig.setSlaveConnectionMinimumIdleSize(10);
        masterSlaveServersConfig.setSlaveConnectionPoolSize(64);
        // 添加主节点
        // masterSlaveServersConfig.setMasterAddress("redis://1.117.158.138:6300");
        // 添加从节点
        // masterSlaveServersConfig.addSlaveAddress("redis://124.71.183.56:6300");
        // 设置密码
        // masterSlaveServersConfig.setPassword("123");
        // 主从都可以读取
        // masterSlaveServersConfig.setReadMode(ReadMode.MASTER_SLAVE);
        // 订阅服务
        // masterSlaveServersConfig.setSubscriptionMode(SubscriptionMode.SLAVE);
        // 负载均衡  有轮询（默认） 权重和随机
        // masterSlaveServersConfig.setLoadBalancer(new RoundRobinLoadBalancer());
        config.setCodec(new StringCodec());
        return Redisson.create(config);
    }
}

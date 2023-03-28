package com.yxz.wulibibiji.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
//        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson.yml"));
        Config config = new Config();
        ClusterServersConfig clusterServersConfig = config.useClusterServers().addNodeAddress("redis://1.117.158.138:7001"/*, "redis://1.117.158.138:7002", "redis://1.117.158.138:7003"*/).setReadMode(ReadMode.MASTER_SLAVE).setPingConnectionInterval(1000);

//        ClusterServersConfig clusterServersConfig = config.useClusterServers().setScanInterval(2000).addNodeAddress("redis://1.117.158.138:7001"
//                , "redis://1.117.158.138:7002"
//                , "redis://1.117.158.138:7003"
//                , "redis://1.117.158.138:7004"
//                , "redis://1.117.158.138:7005"
//                , "redis://1.117.158.138:7006").setReadMode(ReadMode.MASTER_SLAVE);
        config.setCodec(new StringCodec());

        return Redisson.create(config);
    }
}

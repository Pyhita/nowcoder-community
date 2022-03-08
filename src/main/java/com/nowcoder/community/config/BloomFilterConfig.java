package com.nowcoder.community.config;

import com.nowcoder.community.util.BloomFilterUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: pyhita
 * @Date: 2022/3/6
 * @Descrption: com.nowcoder.community.config
 * @Version: 1.0
 */
@Configuration
public class BloomFilterConfig {


    @Bean
    public BloomFilterUtil<Integer> bloomFilterUtil() {
        int n = 1000000000;
        double p = 0.000001;

        BloomFilterUtil bloomFilterUtil = new BloomFilterUtil(n, p);
        return bloomFilterUtil;
    }
}

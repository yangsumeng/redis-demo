package com.yangsm.demo.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yangsm.demo.redis.constant.Constance;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

/**
 * todo redis配置
 *
 * @author yangsumeng
 * @created 20180913
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * 缓存对象集合中，缓存是以key-value形式保存的。当不指定缓存的key时，SpringBoot会使用keyGenerator() 方法生成key，
     * 所以重写keyGenerator() 方法。
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();

            sb.append(target.getClass().getName());
            sb.append(":" + method.getName());
            for (Object obj : params) {
                sb.append(":" + obj.toString());
            }
            return sb.toString();
        };

    }

//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
//        rcm.setDefaultExpiration(3600);
//        return rcm;
//    }

    @Bean
    public RedisTemplate<String, Object> redisCacheTemplate(RedisConnectionFactory factory) {
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //设置序列化工具
        setSerializer(template);
        template.setConnectionFactory(factory);
        System.out.println("==============================RedisCacheConfig RestTemplate");
        return template;
    }

    private void setSerializer(RedisTemplate template){
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        //设置转换规则
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectMapper objectMapper = builder.build();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        // 忽略 transient 修饰的属性
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        objectMapper.setDateFormat(new SimpleDateFormat(Constance.DEFAULT_DATE_FORMATE));
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        RedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer); //否者key会出现乱码
        //template.setValueSerializer(jackson2JsonRedisSerializer);//设置解析对象
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        //template.setValueSerializer(new JdkSerializationRedisSerializer());
    }
}

package com.consult.address.address_consult.config;

import com.consult.address.address_consult.dto.CepResponse;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class CacheConfigTest {

    @Test
    void shouldCreateConfiguredRedisTemplate() {
        // arrange
        CacheConfig config = new CacheConfig();
        RedisConnectionFactory factory = mock(RedisConnectionFactory.class);

        // act
        RedisTemplate<String, CepResponse> template = config.cepRedisTemplate(factory);

        // assert
        assertNotNull(template.getKeySerializer());
        assertNotNull(template.getValueSerializer());
    }
}

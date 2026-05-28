package com.consult.address.address_consult.cache;

import com.consult.address.address_consult.dto.CepResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
public class RedisCepCacheRepository implements CepCacheRepository {

    private static final String KEY_PREFIX = "cep-address:";

    private final RedisTemplate<String, CepResponse> cepRedisTemplate;
    private final Duration ttl;

    public RedisCepCacheRepository(
            RedisTemplate<String, CepResponse> cepRedisTemplate,
            @Value("${cache.cep.ttl-minutes:10}") long ttlMinutes) {
        this.cepRedisTemplate = cepRedisTemplate;
        this.ttl = Duration.ofMinutes(ttlMinutes);
    }

    @Override
    public Optional<CepResponse> findByCep(String cep) {
        CepResponse cached = cepRedisTemplate.opsForValue().get(buildKey(cep));
        return Optional.ofNullable(cached);
    }

    @Override
    public void save(String cep, CepResponse response) {
        cepRedisTemplate.opsForValue().set(buildKey(cep), response, ttl);
    }

    private String buildKey(String cep) {
        return KEY_PREFIX + cep;
    }
}

package com.consult.address.address_consult.cache;

import com.consult.address.address_consult.dto.CepResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RedisCepCacheRepositoryTest {

    @Mock
    private RedisTemplate<String, CepResponse> redisTemplate;

    @Mock
    private ValueOperations<String, CepResponse> valueOperations;

    private RedisCepCacheRepository repository;

    @BeforeEach
    void setUp() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        repository = new RedisCepCacheRepository(redisTemplate, 10);
    }

    @Test
    void shouldReturnCachedValueWhenPresent() {
        // arrange
        CepResponse response = new CepResponse("01001000", "Rua A", "Apto", "Centro", "Sao Paulo", "SP");
        when(valueOperations.get("cep-address:01001000")).thenReturn(response);

        // act
        Optional<CepResponse> result = repository.findByCep("01001000");

        // assert
        assertTrue(result.isPresent());
        assertEquals("01001000", result.get().cep());
    }

    @Test
    void shouldSaveWithPrefixedKey() {
        // arrange
        CepResponse response = new CepResponse("01001000", "Rua A", "Apto", "Centro", "Sao Paulo", "SP");

        // act
        repository.save("01001000", response);

        // assert
        verify(valueOperations).set(org.mockito.ArgumentMatchers.eq("cep-address:01001000"), org.mockito.ArgumentMatchers.eq(response), org.mockito.ArgumentMatchers.any());
    }
}

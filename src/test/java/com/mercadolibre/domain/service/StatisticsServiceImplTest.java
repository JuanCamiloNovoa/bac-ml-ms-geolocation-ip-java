package com.mercadolibre.domain.service;

import com.mercadolibre.domain.dto.response.Statistics;
import com.mercadolibre.persistence.entity.StatisticsEntity;
import com.mercadolibre.persistence.repository.StatisticsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceImplTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @InjectMocks
    private StatisticsServiceImpl statisticsServiceImpl;


    @Test
    void testUpdateStatistics_existingCountry() {
        StatisticsEntity existingEntity = StatisticsEntity.builder().country("TestCountry").distance(100).invocation(1).build();
        when(statisticsRepository.findByCountry(anyString())).thenReturn(Mono.just(existingEntity));
        when(statisticsRepository.save(any(StatisticsEntity.class))).thenReturn(Mono.just(existingEntity));

        Mono<Void> result = statisticsServiceImpl.updateStatistics("TestCountry", 50);

        StepVerifier.create(result)
                .verifyComplete();

        verify(statisticsRepository).findByCountry("TestCountry");
        verify(statisticsRepository).save(existingEntity);
    }

    @Test
    void testUpdateStatistics_newCountry() {
        when(statisticsRepository.findByCountry(anyString())).thenReturn(Mono.empty());
        when(statisticsRepository.save(any(StatisticsEntity.class))).thenReturn(Mono.just(new StatisticsEntity()));

        Mono<Void> result = statisticsServiceImpl.updateStatistics("NewCountry", 150);

        StepVerifier.create(result)
                .verifyComplete();

        verify(statisticsRepository).findByCountry("NewCountry");
        verify(statisticsRepository).save(any(StatisticsEntity.class));
    }

    @Test
    void testGetStatistics() {
        StatisticsEntity maxEntity = StatisticsEntity.builder().country("MaxCountry").distance(200).build();
        StatisticsEntity minEntity = StatisticsEntity.builder().country("MinCountry").distance(50).build();
        when(statisticsRepository.getTotalInvocations()).thenReturn(Mono.just(3L));
        when(statisticsRepository.getTotalDistance()).thenReturn(Mono.just(300L));
        when(statisticsRepository.getMaxDistanceEntity()).thenReturn(Mono.just(maxEntity));
        when(statisticsRepository.getMinDistanceEntity()).thenReturn(Mono.just(minEntity));

        Mono<Statistics> result = statisticsServiceImpl.getStatistics();

        StepVerifier.create(result)
                .assertNext(statistics -> {
                    Assertions.assertNotNull(statistics);
                    Assertions.assertEquals("MaxCountry", statistics.paisDistanciaMaxima());
                    Assertions.assertEquals("MinCountry", statistics.paisDistanciaMinima());
                    Assertions.assertEquals(200, statistics.distanciaMaxima());
                    Assertions.assertEquals(50, statistics.distanciaMinima());
                    Assertions.assertEquals("100", statistics.distanciaPromedio());
                })
                .verifyComplete();

        verify(statisticsRepository).getTotalInvocations();
        verify(statisticsRepository).getTotalDistance();
        verify(statisticsRepository).getMaxDistanceEntity();
        verify(statisticsRepository).getMinDistanceEntity();
    }
}

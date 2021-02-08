package ru.funbox.urlhistoryservice.doamin;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import ru.funbox.urlhistoryservice.dao.HistoryRepository;

import java.util.List;

public class HistoryServiceImplTest {
    private HistoryRepository historyRepository;
    private HistoryService historyService;

    @BeforeEach
    void init() {
        this.historyRepository = Mockito.mock(HistoryRepository.class);
        this.historyService = new HistoryServiceImpl(this.historyRepository);
    }


    @Test
    void saveVisitedLinks_ShouldThrowException_WhenGetNullObject() {
        Class<DomainException> expected = DomainException.class;
        Executable actual = ()->this.historyService.saveVisitedLinks(null);
        assertThrows(expected, actual);
    }

    @Test
    void saveVisitedLinks_ShouldNotThrowException_WhenGetEmptyList() {
        Mockito.doThrow(new IllegalArgumentException()).when(this.historyRepository).saveVisitedDomainsInTime(Mockito.anyList(), Mockito.anyLong());

        Executable actual = ()->this.historyService.saveVisitedLinks(List.of());
        assertDoesNotThrow(actual);
    }

    @Test
    void getVisitedDomainsBetweenTime_ShouldThrowException_WhenFromIsHighestThenToInTimeRange() {
        Class<DomainException> expected = DomainException.class;
        Executable actual = ()->this.historyService.getVisitedDomainsBetweenTime(System.currentTimeMillis(), 0);
        assertThrows(expected, actual);
    }

    @Test
    void getVisitedDomainsBetweenTime_ShouldReturnDistinctDomainList_WhenExecuteMethod() {
        Mockito.when(this.historyRepository.getVisitedDomainsBetweenTime(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(List.of("vk.com","ya.ru", "vk.com"));

        List<String> expected = List.of("ya.ru", "vk.com");
        List<String> actual = this.historyService.getVisitedDomainsBetweenTime(0,1);
        assertThat(expected, containsInAnyOrder(actual.toArray()));
    }
}

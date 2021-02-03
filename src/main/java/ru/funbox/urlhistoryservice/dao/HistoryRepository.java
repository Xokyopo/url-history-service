package ru.funbox.urlhistoryservice.dao;

import java.util.List;
import java.util.Map;

public interface HistoryRepository {
    void saveVisitedDomainsInTime(List<String> domains, long time);
    Map<Long, List<String>> getVisitedDomainsBetweenTime(long from, long to);
}

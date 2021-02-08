package ru.funbox.urlhistoryservice.dao;

import java.util.List;

public interface HistoryRepository {
    void saveVisitedDomainsInTime(List<String> domains, long time);
    List<String> getVisitedDomainsBetweenTime(long from, long to);
}

package ru.funbox.urlhistoryservice.doamin;

import ru.funbox.urlhistoryservice.dao.HistoryRepository;
import ru.funbox.urlhistoryservice.utils.UrlUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class HistoryServiceImpl implements HistoryService {
    private final UrlUtils urlUtils;
    private final HistoryRepository historyRepository;

    @Inject
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
        this.urlUtils = new UrlUtils();
    }

    @Override
    public void saveVisitedLinks(List<String> links) {
        this.validateLinks(links);

        List<String> domains = links.stream().map(this.urlUtils::extractDomain).collect(Collectors.toList());

        if (!domains.isEmpty()) this.historyRepository.saveVisitedDomainsInTime(domains, System.currentTimeMillis());
    }

    @Override
    public List<String> getVisitedDomainsBetweenTime(long from, long to) {
        this.validateTimeRange(from, to);

        return this.historyRepository.getVisitedDomainsBetweenTime(from, to).stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private void validateTimeRange(long from, long to) {
        if (from > to) throw new DomainException(String.format("Invalid time range from = %s to = %s", from , to));
    }

    private void validateLinks(List<String> links) {
        if (links == null || !links.stream().allMatch(this.urlUtils::isUrlLink)) throw new DomainException("Оne of your strings is not a valid url");
    }
}

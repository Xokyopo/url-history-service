package ru.funbox.urlhistoryservice.dao;

import io.quarkus.redis.client.RedisClient;
import io.vertx.redis.client.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class RedisHistoryRepository implements HistoryRepository {
    private final RedisClient redisClient;
    private final String groupName;

    @Inject
    public RedisHistoryRepository(RedisClient redisClient,
                                  @ConfigProperty(name = "redis.repositories.urlhistory", defaultValue = "urlHistory") String groupName) {
        this.redisClient = redisClient;
        this.groupName = groupName;
    }

    @Override
    public void saveVisitedDomainsInTime(List<String> domains, long time) {
        List<String> command = new ArrayList<>();

        command.add(this.groupName);

        domains.forEach(domain -> {
            command.add(Long.toString(time));
            command.add(domain);
        });

        try {
            this.redisClient.zadd(command);
        } catch (Exception e) {
            throw new DaoException("Redis service not found");
        }
    }

    @Override
    public List<String> getVisitedDomainsBetweenTime(long from, long to) {
        List<String> command = new ArrayList<>();

        command.add(this.groupName);
        command.add(Long.toString(from));
        command.add(Long.toString(to));

        try {
            return this.redisClient.zrangebyscore(command).stream()
                    .map(Response::toString)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DaoException("Redis service not found");
        }
    }
}

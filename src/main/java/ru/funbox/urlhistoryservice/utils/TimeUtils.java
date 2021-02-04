package ru.funbox.urlhistoryservice.utils;

public class TimeUtils {
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public boolean isValidTimeRange(long from, long to) {
        return from <= to;
    }
}

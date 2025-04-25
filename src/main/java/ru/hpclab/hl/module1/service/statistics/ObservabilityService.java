package ru.hpclab.hl.module1.service.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import ru.hpclab.hl.module1.model.Timing;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class ObservabilityService {

    private static final Logger log = LoggerFactory.getLogger(ObservabilityService.class);

    private final List<Integer> sortedIntervals;
    private final int delay;
    private final Set<Timing> timings = new ConcurrentSkipListSet<>(Comparator.comparing(Timing::getStart));

    public ObservabilityService(List<Integer> intervals, int delay) {
        this.sortedIntervals = intervals.stream().sorted().toList();
        this.delay = delay;
    }

    public void start(String name) {
        timings.add(new Timing(name, Instant.now(), Optional.empty()));
    }

    public void stop(String name) {
        Instant now = Instant.now();
        timings.stream()
                .filter(t -> t.getName().equals(name) && t.getStop().isEmpty())
                .forEach(t -> t.setStop(Optional.of(now)));
    }

    private void removeOldTimings(Instant now, int maxInterval) {
        timings.removeIf(t -> t.getStop().isPresent() &&
                t.getStart().isBefore(now.minusSeconds(maxInterval)));
    }

    private Set<String> getUniqueNames(List<Timing> snapshot) {
        return snapshot.stream()
                .map(Timing::getName)
                .collect(Collectors.toSet());
    }

    @Async("applicationTaskExecutor")
    @Scheduled(fixedDelayString = "${service.statistic.observability.delay}")
    public void logStatistics() {
        Instant now = Instant.now();
        List<Timing> snapshot = new ArrayList<>(timings);
        int maxInterval = sortedIntervals.get(sortedIntervals.size() - 1);

        removeOldTimings(now, maxInterval);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        String timestamp = formatter.format(now);

        log.info("\n[Observability] Log at {}, delay = {}ms", timestamp, delay);

        for (String name : getUniqueNames(snapshot)) {
            for (int interval : sortedIntervals) {
                List<Timing> filtered = snapshot.stream()
                        .filter(t -> t.getStop().isPresent()
                                && !t.getStart().isBefore(now.minusSeconds(interval))
                                && !t.getStart().isAfter(now)
                                && t.getName().equals(name))
                        .toList();

                if (!filtered.isEmpty()) {
                    double avgMillis = filtered.stream()
                            .mapToLong(t -> Duration.between(t.getStart(), t.getStop().get()).toMillis())
                            .average().orElse(0.0);

                    System.out.printf(
                            "[ %s ] %ds: %s = %.2f ms ( %d )%n",
                            timestamp,
                            interval,
                            name,
                            avgMillis,
                            filtered.size()
                    );
                }
            }
        }
    }
}

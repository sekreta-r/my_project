package ru.hpclab.hl.module1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Optional;

@Data
@AllArgsConstructor
public class Timing {
    private String name;
    private Instant start;
    private Optional<Instant> stop;
}

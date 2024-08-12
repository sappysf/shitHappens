package org.example.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record BirthDay(LocalDate localDate) {
    public long getAge() {
        return ChronoUnit.YEARS.between(localDate,LocalDate.now());
    }
}

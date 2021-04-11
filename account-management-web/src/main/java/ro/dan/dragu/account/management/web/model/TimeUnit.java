package ro.dan.dragu.account.management.web.model;

import java.time.temporal.ChronoUnit;

public enum TimeUnit {

    DAYS(ChronoUnit.DAYS), HOURS(ChronoUnit.HOURS);

    private final ChronoUnit chronoUnit;


    TimeUnit(ChronoUnit chronoUnit) {
        this.chronoUnit = chronoUnit;
    }

    public ChronoUnit getChronoUnit() {
        return chronoUnit;
    }


}

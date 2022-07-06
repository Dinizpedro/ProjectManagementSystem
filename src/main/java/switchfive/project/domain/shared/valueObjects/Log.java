package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Log implements ValueObject<Log>, Serializable {
    /**
     * Code of the log in a Task. Starts with 1 and it's auto-incremented.
     */
    private final int code;
    /**
     * The amount of time in hours spent on a given log.
     */
    private final Hour timeSpent;
    /**
     * A brief description of the work done.
     */
    private final TaskDescription logDescription;
    /**
     * The date when the log was added.
     */
    private final String dateOfLog;

    private Log(final int code, final Hour timeSpent,
                final TaskDescription logDescription) {
        this.code = code;
        this.timeSpent = timeSpent;
        this.logDescription = logDescription;
        this.dateOfLog = getLocalTime();
    }

    public static Log createLog(int code, Hour timeSpent, TaskDescription logDescription) {
        return new Log(code, timeSpent, logDescription);
    }

    private String getLocalTime() {
        // Pattern used e.g. " 2018-11-12 21:19"
        final String DATE_PATTERN = "yyyy-MM-dd HH:mm";
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return localDateTime.format(formatter);
    }

    /**
     * Value objects compare by the values of their attributes, they don't have
     * an identity.
     *
     * @param other The other value object.
     * @return <code>true</code> if the given value object's and this value
     * object's attributes are the same.
     */
    @Override
    public boolean sameValueAs(Log other) {
        return other != null && code == other.code && timeSpent.equals(other.timeSpent)
                && logDescription.equals(other.logDescription) && dateOfLog.equals(other.dateOfLog);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return sameValueAs(log);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, timeSpent, logDescription, dateOfLog);
    }
}

package switchfive.project.domain.shared.valueObjects;

import switchfive.project.domain.shared.dddTypes.ValueObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class Time implements ValueObject<Time> {
    /**
     * Start date inserted by actor.
     */
    private final Date startDate;
    /**
     * End date inserted by actor.
     */
    private final Date endDate;

    /**
     * We use private constructor so that any target class could not
     * instantiate this class directly by calling constructor.
     *
     * @param startDateInput as String
     * @param endDateInput   as String
     */
    private Time(final String startDateInput, final String endDateInput)
            throws ParseException {
        if (isValidStartDate(startDateInput)
                && isValidEndDate(startDateInput, endDateInput)) {
            this.startDate = new SimpleDateFormat("dd/MM/yyyy").
                    parse(startDateInput);
            this.endDate = new SimpleDateFormat("dd/MM/yyyy").
                    parse(endDateInput);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Time(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @param startDateInput as String
     * @param endDateInput   as String
     * @return new instance of Time class.
     */
    public static Time create(final String startDateInput,
                              final String endDateInput) throws ParseException {
        return new Time(startDateInput, endDateInput);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param startDateInput as String
     * @return true if is a valid startDate; otherwise, returns false.
     */
    public static boolean isValidStartDate(final String startDateInput)
            throws ParseException {
        final Date startDate = new SimpleDateFormat("dd/MM/yyyy").
                parse(startDateInput);
        final ZoneId defaultZoneId = ZoneId.systemDefault();
        final LocalDate getCurrentDate = LocalDate.now();
        final Date actualCurrentDate = Date.from(
                getCurrentDate.atStartOfDay(defaultZoneId).toInstant());
        return !startDate.before(actualCurrentDate);
    }

    /**
     * @param startDateInput as string
     * @param endDateInput   as string
     * @return true if is a valid endDate; otherwise, returns false.
     */
    public static boolean isValidEndDate(final String startDateInput,
                                         final String endDateInput)
            throws ParseException {
        final Date startDate = new SimpleDateFormat("dd/MM/yyyy").
                parse(startDateInput);
        final Date endDate = new SimpleDateFormat("dd/MM/yyyy").
                parse(endDateInput);
        return endDate.after(startDate);
    }

    public boolean areInputDatesInsideTimeDates(Time time) {
        return !this.startDate.after(time.startDate) &&
                !this.endDate.before(time.endDate);
    }

    public boolean areTimeDatesInsideInputDates(Time time) {
        return !this.startDate.before(time.startDate) &&
                !this.endDate.after(time.endDate);
    }

    /**
     * @param other The other value object.
     * @return true if other have same valued as this; otherwise, returns false.
     */
    @Override
    public boolean sameValueAs(final Time other) {
        return other != null && Objects.equals(startDate, other.startDate)
                && Objects.equals(endDate, other.endDate);
    }

    /**
     * Override equals.
     *
     * @param other the other object.
     * @return true if the other instances are equals.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Time)) {
            return false;
        }
        Time that = (Time) other;
        return sameValueAs(that);
    }

    /**
     * @return hash(int).
     */
    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}

package switchfive.project.domain.aggregates.sprint;

import switchfive.project.dtos.TimeDTO;
import switchfive.project.domain.shared.dddTypes.AggregateRoot;
import switchfive.project.domain.shared.valueObjects.ProjectCode;
import switchfive.project.domain.shared.valueObjects.SprintDescription;
import switchfive.project.domain.shared.valueObjects.SprintID;
import switchfive.project.domain.shared.valueObjects.SprintNumber;
import switchfive.project.domain.shared.valueObjects.SprintStatus;
import switchfive.project.domain.shared.valueObjects.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Sprint implements AggregateRoot<Sprint> {
    private SprintID sprintID;
    private ProjectCode projectCode;
    private SprintNumber sprintNumber;
    private Time dates;
    private SprintDescription description;
    private SprintStatus status;

    public Sprint(SprintID sprintID, ProjectCode projectCode, SprintNumber sprintNumber,
                  Time dates, SprintDescription description, SprintStatus status) {
        this.sprintID = sprintID;
        this.projectCode = projectCode;
        this.sprintNumber = sprintNumber;
        this.dates = dates;
        this.description = description;
        this.status = status;
    }

    public SprintID getSprintID() {
        return sprintID;
    }

    public String getProjectCode() {
        return projectCode.getCode();
    }

    public int getSprintNumber() {
        return sprintNumber.getSprintNumber();
    }

    public TimeDTO getDates() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = this.dates.getStartDate();
        Date endDate = this.dates.getEndDate();
        String startDateString = dateFormat.format(startDate);
        String endDateString = dateFormat.format(endDate);

        return new TimeDTO(startDateString, endDateString);
    }

    public String getDescription() {
        return description.getDescription();
    }

    public String getStatus() {
        return status.toString();
    }

    /**
     * Check if this sprint status is FINISHED.
     *
     * @return true if it is FINISHED, false otherwise.
     */
    public boolean isSprintStatusFinished() {
        return this.status.equals(SprintStatus.FINISHED);
    }

    @Override
    public boolean sameIdentityAs(final Sprint other) {
        return other != null && this.sprintID.sameValueAs(other.sprintID);
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Sprint)) {
            return false;
        }
        Sprint sprint = (Sprint) other;
        return sameIdentityAs(sprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintID);
    }

    public boolean areDatesWithinSprintDates(Time time)
            throws ParseException {
        return this.dates.areInputDatesInsideTimeDates(time);
    }

    public boolean areSprintDatesInsideInputTime(Time time)
            throws ParseException {
        return this.dates.areTimeDatesInsideInputDates(time);
    }
}

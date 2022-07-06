package switchfive.project.dtos;

public class StartSprintDto {
    public String projectCodeDto;
    public int sprintNumberDto;
    public String startDateDto;
    public int durationInWeeks;

    public StartSprintDto(String projectCodeDto, int sprintNumberDto,
                          String startDateDto, int durationInWeeks) {
        this.projectCodeDto = projectCodeDto;
        this.sprintNumberDto = sprintNumberDto;
        this.startDateDto = startDateDto;
        this.durationInWeeks = durationInWeeks;
    }
}

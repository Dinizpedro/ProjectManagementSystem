package switchfive.project.assemblers.assemblersREST.implAssemblersREST;

import org.springframework.stereotype.Component;
import switchfive.project.dataModel.dataREST.ProjectRest;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;
import switchfive.project.assemblers.assemblersREST.iAssemblersREST.IRestProjectAssembler;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This java class implements the methods needed to map a ProjectRest, received from Rest Repository, to ProjectWS, that will interact with app service.
 */
@Component public class ImplRestProjectAssembler implements IRestProjectAssembler {

    public ProjectWS toWebServiceDomain(final ProjectRest projectRest) throws ParseException {

        String euStartDate = formatCalendarEU(projectRest.getStartDate());
        String euEndDate = formatCalendarEU(projectRest.getEndDate());

        String status = formatStatus(projectRest);

        return ProjectWS.create(status, projectRest.getTypo(), projectRest.getCustomer(), projectRest.getCode(), projectRest.getProjectName(), projectRest.getDescription(),
                projectRest.getBusinessSector(), euStartDate, euEndDate, projectRest.getNumberOfSprints(), projectRest.getBudget(), projectRest.getSprintDuration());
    }

    public ProjectWS toWebServiceDomain(final ProjectRestSimplified restSimplified) {

        return ProjectWS.create(restSimplified.getStatus(), null, null, restSimplified.getCode(), restSimplified.getProjectName(), restSimplified.getDescription(), null, restSimplified.getStartDate(),
                null, 0, 0, 0);
    }

    public String formatStatus(ProjectRest projectRest) {
        String status = projectRest.getStatus();
        status = status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase();
        return status;
    }

    public String formatCalendarEU(String date) throws ParseException {
        SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat euDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date isoStartDate = isoDateFormat.parse(date);
        return euDateFormat.format(isoStartDate);
    }
}

package switchfive.project.assemblers.assemblersREST.iAssemblersREST;

import switchfive.project.dataModel.dataREST.ProjectRest;
import switchfive.project.dataModel.dataREST.ProjectRestSimplified;
import switchfive.project.interfaceAdapters.domainWS.ProjectWS;

import java.text.ParseException;

/**
 * Interface with the methods needed to map a ProjectRest, received from Rest Repository, to ProjectWS, that will interact with app service.
 */
public interface IRestProjectAssembler {

    ProjectWS toWebServiceDomain(ProjectRest projectRest) throws ParseException;

    ProjectWS toWebServiceDomain(final ProjectRestSimplified restSimplified) throws ParseException;

    String formatCalendarEU(String date) throws ParseException;

    String formatStatus(ProjectRest projectRest);

}

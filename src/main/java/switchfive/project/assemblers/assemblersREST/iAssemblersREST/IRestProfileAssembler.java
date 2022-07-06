package switchfive.project.assemblers.assemblersREST.iAssemblersREST;

import switchfive.project.dataModel.dataREST.ProfileRest;
import switchfive.project.interfaceAdapters.domainWS.ProfileWS;

import java.util.List;

public interface IRestProfileAssembler {

    ProfileWS toDomain(ProfileRest profileRest);

    List<ProfileWS> toDomain(List<ProfileRest> profileRestList);
}

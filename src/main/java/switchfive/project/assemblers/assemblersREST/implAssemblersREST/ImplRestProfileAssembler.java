package switchfive.project.assemblers.assemblersREST.implAssemblersREST;

import org.springframework.stereotype.Service;
import switchfive.project.assemblers.assemblersREST.iAssemblersREST.IRestProfileAssembler;
import switchfive.project.dataModel.dataREST.ProfileRest;
import switchfive.project.interfaceAdapters.domainWS.ProfileWS;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImplRestProfileAssembler implements IRestProfileAssembler {

    public ProfileWS toDomain(ProfileRest profileRest) {
        String profileDescriptionRest = profileRest.getUserProfileName();

        ProfileWS profileWS = new ProfileWS(profileDescriptionRest);

        return profileWS;
    }

    @Override
    public List<ProfileWS> toDomain(List<ProfileRest> profileRestList) {
        List<ProfileWS> profilesInWebService = new ArrayList<>();

        for (ProfileRest profileRest : profileRestList) {
            ProfileWS domainProfileWS = this.toDomain(profileRest);
            profilesInWebService.add(domainProfileWS);
        }

        return profilesInWebService;
    }
}

package switchfive.project.assemblers.assemblersJPA.implAssemblersJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IRequestAssemblerJPA;
import switchfive.project.dataModel.dataJPA.RequestJPA;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.factories.iFactories.RequestFactory;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.time.LocalDate;

@Service
public class ImplRequestAssemblerJPA implements IRequestAssemblerJPA {

    @Autowired
    private RequestFactory requestFactory;

    public Request toDomain(RequestJPA requestJPA) {
        String requestIDJPA = requestJPA.getIdentity();
        String userIDJPA = requestJPA.getUserID();
        String profileIDJPA = requestJPA.getProfileDescription();
        String creationDateJPA = requestJPA.getCreationDate();
        boolean isApprovedJPA = requestJPA.isApproved();

        RequestID requestID = RequestID.createRequestID(requestIDJPA);
        Email userID = Email.create(userIDJPA);
        ProfileDescription profileID = ProfileDescription.createProfileDescription(profileIDJPA);
        LocalDate creationDate = java.time.LocalDate.parse(creationDateJPA);

        return requestFactory.createNewRequest(requestID,
                userID,
                profileID,
                creationDate,
                isApprovedJPA);
    }

    public RequestJPA toData(Request request) {

        String requestIDJPA = request.getIdentity();
        String userIDJPA = request.getUserID();
        String profileIDJPA = request.getProfileDescription();
        String creationDateJPA = request.getCreationDate();
        boolean isApprovedJPA = request.isRequestApproved();

        RequestJPA requestJPA = new RequestJPA();
        requestJPA.setIdentity(requestIDJPA);
        requestJPA.setUserID(userIDJPA);
        requestJPA.setProfileDescription(profileIDJPA);
        requestJPA.setCreationDate(creationDateJPA);
        requestJPA.setApproved(isApprovedJPA);

        return requestJPA;
    }

}

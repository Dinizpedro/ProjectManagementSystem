package switchfive.project.domain.factories.implFactories;

import org.springframework.stereotype.Component;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.factories.iFactories.RequestFactory;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.time.LocalDate;

@Component
public class RequestFactoryImplementation implements RequestFactory {

    @Override
    public Request createNewRequest(RequestID requestIdentity, Email requestedUserID,
                                    ProfileDescription selectedProfileID) {
        return new Request(requestIdentity, requestedUserID, selectedProfileID);
    }

    @Override
    public Request createNewRequest(RequestID requestIdentity,
                                    Email requestedUserID,
                                    ProfileDescription selectedProfileID,
                                    LocalDate creationDate,
                                    boolean isApproved) {
        return new Request(requestIdentity,
                requestedUserID,
                selectedProfileID,
                creationDate,
                isApproved);
    }
}

package switchfive.project.domain.factories.iFactories;

import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.time.LocalDate;

public interface RequestFactory {
    Request createNewRequest(final RequestID requestIdentity,
                             final Email requestedUserID,
                             final ProfileDescription selectedProfileID);

    Request createNewRequest(final RequestID requestIdentity,
                             final Email requestedUserID,
                             final ProfileDescription selectedProfileID,
                             final LocalDate creationDate,
                             final boolean isApproved);
}

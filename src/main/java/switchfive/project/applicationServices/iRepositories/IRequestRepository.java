package switchfive.project.applicationServices.iRepositories;

import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.util.Optional;

public interface IRequestRepository {
    // boolean approveRequestIfRequestInStore(UserID selectedUser, ProfileID requestedProfile);

    // boolean deleteRequestOfOldProfileIfInStore(UserID selectedUser, ProfileID actualProfile);

    Request save(Request newRequest);

    Optional<Request> getRequestByUserIDAndProfileID(Email userID, ProfileDescription profileID);

    Optional<Request> getRequestByID(RequestID requestIDToFind);
}

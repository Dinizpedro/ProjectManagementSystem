package switchfive.project.interfaceAdapters.implRepositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.assemblers.assemblersJPA.iAssemblersJPA.IRequestAssemblerJPA;
import switchfive.project.dataModel.dataJPA.RequestJPA;
import switchfive.project.infrastructure.persistence.repositories.iRepositoriesJPA.IRequestRepositoryJPA;
import switchfive.project.applicationServices.iRepositories.IRequestRepository;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.util.Optional;

/**
 * Class responsible for the storage of all Request in a List
 */
@Service
public class ImplRequestRepository implements IRequestRepository {

    private final IRequestRepositoryJPA iRequestRepositoryJPA;

    private final IRequestAssemblerJPA iRequestAssemblerJPA;

    @Autowired
    public ImplRequestRepository(IRequestRepositoryJPA iRequestRepositoryJPA,
                                 IRequestAssemblerJPA iRequestAssemblerJPA) {
        this.iRequestRepositoryJPA = iRequestRepositoryJPA;
        this.iRequestAssemblerJPA = iRequestAssemblerJPA;
    }


    /**
     * Private method that adds the new profile Request to the store.
     *
     * @param newRequest Request object
     * @return true if the Request is successfully added to the store
     */
    @Override
    public Request save(Request newRequest) {
        RequestJPA requestToSave = iRequestAssemblerJPA.toData(newRequest);
        RequestJPA requestInDB = iRequestRepositoryJPA.save(requestToSave);
        return iRequestAssemblerJPA.toDomain(requestInDB);
    }

    /**
     * Searches the store and retrieves the Request with the selected User and
     * requested Profile. Null if no Request is found.
     * <p>
     * author: mpc
     *
     * @param selectedUser     User in Request
     * @param requestedProfile Profile in Request
     * @return Request object if found
     */
    public Optional<Request> getRequestByUserIDAndProfileID(Email selectedUser,
                                                            ProfileDescription requestedProfile) {
        Request selectedRequest = null;

        String userIDToFind = selectedUser.getUserEmail();
        String profileDescriptionToFind = requestedProfile.getDescription();

        Optional<RequestJPA> optRequestJPA = iRequestRepositoryJPA
                .findByUserIDAndProfileDescription(userIDToFind, profileDescriptionToFind);

        if (optRequestJPA.isPresent()) {
            RequestJPA requestJPA = optRequestJPA.get();
            selectedRequest = iRequestAssemblerJPA.toDomain(requestJPA);
        }


        return Optional.ofNullable(selectedRequest);
    }

    @Override
    public Optional<Request> getRequestByID(RequestID requestID) {
        Request selectedRequest = null;

        String requestIDToFind = requestID.toString();

        Optional<RequestJPA> optRequestJPA = iRequestRepositoryJPA
                .findById(requestIDToFind);

        if (optRequestJPA.isPresent()) {
            RequestJPA requestJPA = optRequestJPA.get();
            selectedRequest = iRequestAssemblerJPA.toDomain(requestJPA);
        }

        return Optional.ofNullable(selectedRequest);
    }


//    /**
//     * Approves a Request in the Request Store, after an update was done
//     * by the admin.
//     * author: mpc
//     *
//     * @param selectedUser     User in Request
//     * @param requestedProfile Profile in Request
//     */
//    @Override
//    public boolean approveRequestIfRequestInStore(UserID selectedUser, ProfileID requestedProfile) {
//        boolean isRequestApproved = false;
//        Optional<Request> requestInStore =
//                getRequestByUserIDAndProfileID(selectedUser, requestedProfile);
//
//        if (requestInStore.isPresent()) {
//            Request request = requestInStore.get();
//            isRequestApproved = request.approveRequest();
//        }
//
//        return isRequestApproved;
//    }
//
//
//    /**
//     * If a Request of a selected Profile that was updated by the admin exists in Store,
//     * deletes it from the Request Store.
//     * <p>
//     * author: mpc
//     *
//     * @param selectedUser  User in Request
//     * @param actualProfile Profile in Request
//     */
//    @Override
//    public boolean deleteRequestOfOldProfileIfInStore(UserID selectedUser, ProfileID actualProfile) {
//        boolean wasRequestDeleted = false;
//        Optional<Request> requestInStore = getRequestByUserIDAndProfileID(selectedUser, actualProfile);
//
//        if (requestInStore.isPresent()) {
//            wasRequestDeleted = this.requestList.remove(requestInStore.get());
//        }
//
//        return wasRequestDeleted;
//    }
}

package switchfive.project.applicationServices.appServices.implAppServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchfive.project.dtos.RequestDTO;
import switchfive.project.applicationServices.appServices.iappServices.IAppRequestService;
import switchfive.project.applicationServices.assemblers.implAssemblers.RequestAssembler;
import switchfive.project.applicationServices.iRepositories.IProfileRepository;
import switchfive.project.applicationServices.iRepositories.IRequestRepository;
import switchfive.project.applicationServices.iRepositories.IUserRepository;
import switchfive.project.domain.aggregates.profile.Profile;
import switchfive.project.domain.aggregates.request.Request;
import switchfive.project.domain.aggregates.user.User;
import switchfive.project.domain.factories.iFactories.RequestFactory;
import switchfive.project.domain.shared.valueObjects.ProfileDescription;
import switchfive.project.domain.shared.valueObjects.Email;
import switchfive.project.domain.shared.valueObjects.RequestID;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class ImplAppRequestService implements IAppRequestService {

    @Autowired
    private IRequestRepository iRequestRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IProfileRepository iProfileRepository;

    @Autowired
    private RequestFactory requestFactory;

    public ImplAppRequestService(IRequestRepository iRequestRepository,
                                 IUserRepository iUserRepository,
                                 IProfileRepository iProfileRepository,
                                 RequestFactory requestFactory) {
        this.iRequestRepository = iRequestRepository;
        this.iUserRepository = iUserRepository;
        this.iProfileRepository = iProfileRepository;
        this.requestFactory = requestFactory;
    }

    @Override
    public Optional<RequestDTO> createNewProfileRequest(String userID, String profileDescription) throws NoSuchAlgorithmException {

        ProfileDescription profileDescriptionObj = ProfileDescription
                .createProfileDescription(profileDescription);
        Optional<Profile> profileInRepo = iProfileRepository
                .getProfileByDescription(profileDescriptionObj);

        Email userIDObj = Email.create(userID);
        Optional<User> userInRepo = iUserRepository.findUserByEmail(userID);

        Optional<Request> requestInRepo = iRequestRepository
                .getRequestByUserIDAndProfileID(userIDObj, profileDescriptionObj);

        RequestDTO requestDTO = null;

        if (profileInRepo.isPresent() && userInRepo.isPresent() &&
                requestInRepo.isEmpty()) {

            User user = userInRepo.get();

            boolean doesUserAlreadyHaveProfile = user.hasProfile(profileDescriptionObj);

            if (!doesUserAlreadyHaveProfile) {
                RequestID requestID = RequestID.createRequestID();
                Request newRequest = requestFactory.createNewRequest(requestID,
                        userIDObj, profileDescriptionObj);

                Request requestDB = iRequestRepository
                        .save(newRequest);

                requestDTO = RequestAssembler.toDto(requestDB);
            }
        }

        return Optional.ofNullable(requestDTO);
    }

    @Override
    public Optional<RequestDTO> getRequestDTO(String requestID) {
        RequestID requestIDToFind = RequestID.createRequestID(requestID);

        Optional<Request> requestInRepo = iRequestRepository
                .getRequestByID(requestIDToFind);
        RequestDTO requestDTO = null;

        if (requestInRepo.isPresent()) {
            requestDTO = RequestAssembler.toDto(requestInRepo.get());
        }

        return Optional.ofNullable(requestDTO);

    }
}

package switchfive.project.domain.factories.implFactories;

import org.springframework.stereotype.Component;
import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.factories.iFactories.IResourceFactory;
import switchfive.project.domain.shared.valueObjects.*;

@Component
public final class ResourceFactoryImplementation implements IResourceFactory {

    /**
     * @param identityInput          ID_Resource identityInput
     * @param userIdInput            ID_User userIdInput
     * @param projectCodeInput   ProjectCode projectCodeInput
     * @param datesInput              Time datesInput
     * @param costPerHourInput   ResourceCostPerHour costPerHourInput
     * @param allocationInput       ResourcePercentageOfAllocation allocationInput
     * @param roleInput                 Role roleInput
     * @return new instance of Resource class.
     */
    @Override
    public Resource createResource(final ResourceID identityInput,
                                   final Email userIdInput,
                                   final ProjectCode projectCodeInput,
                                   final Time datesInput,
                                   final ResourceCostPerHour costPerHourInput,
                                   final ResourcePercentageOfAllocation
                                           allocationInput,
                                   final Role roleInput) {

        return new Resource(identityInput, userIdInput, projectCodeInput,
                datesInput, costPerHourInput, allocationInput, roleInput);
    }

}

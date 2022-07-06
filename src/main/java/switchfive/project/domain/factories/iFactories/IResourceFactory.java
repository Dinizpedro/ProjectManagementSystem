package switchfive.project.domain.factories.iFactories;

import switchfive.project.domain.aggregates.resource.Resource;
import switchfive.project.domain.shared.valueObjects.*;

import java.text.ParseException;

/**
 * ResourceFactory interface (Factory Pattern).
 */
public interface IResourceFactory {
    /**
     * @param identityInput    ID_Resource identityInput
     * @param userIdInput      ID_User userIdInput
     * @param projectCodeInput ProjectCode projectCodeInput
     * @param datesInput       Time datesInput
     * @param costPerHourInput ResourceCostPerHour costPerHourInput
     * @param allocationInput  ResourcePercentageOfAllocation allocationInput
     * @param roleInput        Role roleInput
     * @return instance of Resource.
     */
    Resource createResource(ResourceID identityInput,
                            Email userIdInput,
                            ProjectCode projectCodeInput,
                            Time datesInput,
                            ResourceCostPerHour costPerHourInput,
                            ResourcePercentageOfAllocation allocationInput,
                            Role roleInput) throws ParseException;

}

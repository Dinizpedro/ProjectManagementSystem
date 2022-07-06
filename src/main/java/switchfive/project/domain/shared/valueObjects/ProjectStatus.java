package switchfive.project.domain.shared.valueObjects;

public enum ProjectStatus {

    /**
     * Default status for new projects.
     */
    Planned,

    /**
     * This status represents a meeting typically dedicated for the majority
     * of a business day to prepare a team to start a new project.
     * Inceptions may also be used to realign on an
     * existing project that has been going on for several months.
     */
    Inception,

    /**
     * status which represents the delivery engagement phase
     * where we create our initial project plan
     * by reviewing the business requirements and
     * elaborating on what the requirements are to
     * fulfill those requirements.
     */
    Elaboration,

    /**
     * Status which represents an ongoing project.
     */
    Construction,

    /**
     * Represents a break in the project (which will continue).
     */
    Transition,

    /**
     * Represents a written promise made by a company in the contract.
     */
    Warranty,

    /**
     * Represents a completed project.
     */
    Closed,

}


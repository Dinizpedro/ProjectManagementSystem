package switchfive.project.domain.shared.valueObjects;

public enum Role {
    /**
     * Each project have one scrum master.
     */
    SCRUM_MASTER,
    /**
     * Each project have one product owner.
     */
    PRODUCT_OWNER,
    /**
     * Each project have one project manager.
     */
    PROJECT_MANAGER,
    /**
     * Each project have zero or many team members.
     */
    TEAM_MEMBER
}



package switchfive.project.interfaceAdapters.controllers.iControllers;


import org.springframework.http.ResponseEntity;
import switchfive.project.dtos.ResourceCreationDTO;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface IResourceController {
    ResponseEntity definedTeamMemberOfAProject(ResourceCreationDTO dto)
            throws ParseException;

    ResponseEntity definedProductOwnerOfAProject(ResourceCreationDTO dto)
            throws ParseException, NoSuchAlgorithmException;

    ResponseEntity definedScrumMasterOfAProject(ResourceCreationDTO dto)
            throws ParseException, NoSuchAlgorithmException;
}

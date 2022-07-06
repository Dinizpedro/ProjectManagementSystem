package switchfive.project.interfaceAdapters.controllers.iControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import switchfive.project.dtos.ProjectDTO;
import switchfive.project.dtos.UpdateProjectDTO;

import javax.net.ssl.SSLException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public interface IProjectController {

    ResponseEntity<Object> getProject(String projectCode) throws ParseException, NoSuchAlgorithmException;

    ResponseEntity<Object> getAllProjects() throws ParseException, NoSuchAlgorithmException, SSLException;

    ResponseEntity<Object> createProject(ProjectDTO dto) throws ParseException;

    ResponseEntity<Object> updateProject(String projectCode, UpdateProjectDTO dto)throws ParseException, NoSuchAlgorithmException;

    ResponseEntity<Object> getActivitiesStatus(@PathVariable String projectCode) throws ParseException;

}

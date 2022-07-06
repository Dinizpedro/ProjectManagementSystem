package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.ResourceJPA;
import switchfive.project.domain.aggregates.resource.Resource;

import java.text.ParseException;

public interface IResourceAssemblerJPA {

    Resource toDomain(ResourceJPA resourceJPA) throws ParseException;

    ResourceJPA toData(Resource resource);
}

package switchfive.project.assemblers.assemblersJPA.iAssemblersJPA;

import switchfive.project.dataModel.dataJPA.RequestJPA;
import switchfive.project.domain.aggregates.request.Request;

public interface IRequestAssemblerJPA {

    Request toDomain(RequestJPA requestJPA);

    RequestJPA toData(Request request);

}

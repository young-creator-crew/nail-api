package sptech.school.nail_api.mapper;

import sptech.school.nail_api.dto.procedures.ProceduresRequestDTO;
import sptech.school.nail_api.dto.procedures.ProceduresResponseDTO;
import sptech.school.nail_api.model.Procedure;

public class ProceduresMapper {

    public static ProceduresResponseDTO procedureToProcedureResponse(Procedure procedure){
        return new ProceduresResponseDTO(
            procedure.getId(),
            procedure.getName(),
            procedure.getPrice(),
            procedure.getEstimatedDurationMinutes()
            );
        }

    public static Procedure registerRequestToProcedure(ProceduresRequestDTO dto){
        Procedure procedure = new Procedure();
        procedure.setName(dto.getName());
        procedure.setPrice(dto.getPrice());
        procedure.setEstimatedDurationMinutes(dto.getEstimatedDurationMinutes());

        return procedure;
    }

    public static Procedure updateRequestToProcedure(ProceduresRequestDTO dto){
        Procedure procedure = new Procedure();
        procedure.setName(dto.getName());
        procedure.setPrice(dto.getPrice());
        procedure.setEstimatedDurationMinutes(dto.getEstimatedDurationMinutes());

        return procedure;
    }

    }




package ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.service;

import java.util.List;
import java.util.Optional;

import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.domain.MissionReimbursementRequest;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.exception.InvalidMissionReimbursementRequestIdException;

public interface MissionReimbursementRequestService {
    
    List<MissionReimbursementRequest> getAllMissionReimbursementRequests();
    Optional<MissionReimbursementRequest> getMissionReimbursementRequestById(Long id);
    MissionReimbursementRequest saveMissionReimbursementRequest(MissionReimbursementRequest missionReimbursementRequest);
    MissionReimbursementRequest createMissionReimbursementRequestWith(Long missionId, Long professorId);
    MissionReimbursementRequest approveMissionReimbursementRequestById(Long id) throws InvalidMissionReimbursementRequestIdException;
    MissionReimbursementRequest rejectMissionReimbursementRequestById(Long id) throws InvalidMissionReimbursementRequestIdException;
    
}

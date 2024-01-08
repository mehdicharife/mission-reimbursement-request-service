package ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.domain.MissionReimbursementRequest;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.repository.MissionReimbursementRequestRepository;


@Service
public class MissionReimbursementRequestServiceImpl  implements MissionReimbursementRequestService {

    private MissionReimbursementRequestRepository missionReimbursementRequestRepository;

    public MissionReimbursementRequestServiceImpl(MissionReimbursementRequestRepository missionReimbursementRequestRepository) {
        this.missionReimbursementRequestRepository = missionReimbursementRequestRepository;
    }
    
    public List<MissionReimbursementRequest> getAllMissionReimbursementRequests() {
        return this.missionReimbursementRequestRepository.findAll();
    }

    public Optional<MissionReimbursementRequest> getMissionReimbursementRequestById(Long id) {
        return this.missionReimbursementRequestRepository.findById(id);
    }

    public MissionReimbursementRequest saveMissionReimbursementRequest(MissionReimbursementRequest missionReimbursementRequest) {
        return this.missionReimbursementRequestRepository.save(missionReimbursementRequest);
    }
    
}

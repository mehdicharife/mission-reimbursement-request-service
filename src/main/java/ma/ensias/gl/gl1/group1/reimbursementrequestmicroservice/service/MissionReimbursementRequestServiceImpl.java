package ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.domain.MissionReimbursementRequest;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.domain.MissionReimbursementRequest.State;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.event.MissionReimbursementRequestApprovedEvent;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.exception.InvalidMissionReimbursementRequestIdException;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.repository.MissionReimbursementRequestRepository;


@Service
public class MissionReimbursementRequestServiceImpl  implements MissionReimbursementRequestService {

    private MissionReimbursementRequestRepository missionReimbursementRequestRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${missionReimbursementRequestApprovedExchangeName}")
    private String missionReimbursementRequestApprovedExchangeName;


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

    public MissionReimbursementRequest createMissionReimbursementRequestWith(Long missionId, Long professorId) {
        MissionReimbursementRequest missionReimbursementRequest = new MissionReimbursementRequest();
        missionReimbursementRequest.setMissionId(missionId);
        missionReimbursementRequest.setProfessorId(professorId);

        return this.missionReimbursementRequestRepository.save(missionReimbursementRequest);
    }

    public MissionReimbursementRequest approveMissionReimbursementRequestById(Long id) throws InvalidMissionReimbursementRequestIdException {
        Optional<MissionReimbursementRequest> optionalMissionReimbursementRequest = this.missionReimbursementRequestRepository.findById(id);
        if(optionalMissionReimbursementRequest.isPresent()) {
            MissionReimbursementRequest missionReimbursementRequest = optionalMissionReimbursementRequest.get();
            missionReimbursementRequest.setState(State.APPROVED);

            MissionReimbursementRequestApprovedEvent event = new MissionReimbursementRequestApprovedEvent(
                missionReimbursementRequest.getId(),
                missionReimbursementRequest.getMissionId(),
                missionReimbursementRequest.getProfessorId()
            );

            this.rabbitTemplate.convertAndSend(missionReimbursementRequestApprovedExchangeName,"", event);
            
            return missionReimbursementRequest;
        }

        throw new InvalidMissionReimbursementRequestIdException("id: " + id + " doesn't correspond to any reimbursement request");
    }

    public MissionReimbursementRequest rejectMissionReimbursementRequestById(Long id) throws InvalidMissionReimbursementRequestIdException {
        Optional<MissionReimbursementRequest> optionalMissionReimbursementRequest = this.missionReimbursementRequestRepository.findById(id);
        if(optionalMissionReimbursementRequest.isPresent()) {
            MissionReimbursementRequest missionReimbursementRequest = optionalMissionReimbursementRequest.get();
            missionReimbursementRequest.setState(State.REJECTED);
            return missionReimbursementRequest;
        }

        throw new InvalidMissionReimbursementRequestIdException("id: " + id + " doesn't correspond to any reimbursement request");
    }
    
}

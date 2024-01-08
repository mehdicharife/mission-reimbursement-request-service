package ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.controller;

import org.springframework.web.bind.annotation.RestController;

import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.domain.MissionReimbursementRequest;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.dto.MissionReimbursementRequestDto;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.exception.InvalidMissionReimbursementRequestIdException;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.service.MissionReimbursementRequestService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/mission-reimbursement-requests")
public class MissionReimbursementRequestController {
    
    private MissionReimbursementRequestService missionReimbursementRequestService;

    public MissionReimbursementRequestController(MissionReimbursementRequestService missionReimbursementRequestService) {
        this.missionReimbursementRequestService = missionReimbursementRequestService;
    }


    @GetMapping
    public List<MissionReimbursementRequest> getAllMissionReimbursementRequests() {
        return this.missionReimbursementRequestService.getAllMissionReimbursementRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMissionReimbursementRequestById(@PathVariable Long id) {
        Optional<MissionReimbursementRequest> optionalMissionReimbursementRequest = this.missionReimbursementRequestService.getMissionReimbursementRequestById(id);

        if(optionalMissionReimbursementRequest.isPresent()) {
            return new ResponseEntity<>(missionReimbursementRequestService, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<Object> createMissionReimbursementRequest(MissionReimbursementRequestDto missionReimbursementRequestDto) {
        return new ResponseEntity<>(
            this.missionReimbursementRequestService.createMissionReimbursementRequestWith(
                missionReimbursementRequestDto.getMissionId(),
                missionReimbursementRequestDto.getProfessorId()
            ),
            HttpStatus.CREATED
        );
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<Object> approveMissionReimbursementRequest(@PathVariable Long id) {
        try {
            MissionReimbursementRequest missionReimbursementRequest = this.missionReimbursementRequestService.approveMissionReimbursementRequestById(id);
            return new ResponseEntity<>(missionReimbursementRequest, HttpStatus.OK);
        } catch(InvalidMissionReimbursementRequestIdException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<Object> rejectMissionReimbursementRequest(@PathVariable Long id) {
        try {
            MissionReimbursementRequest missionReimbursementRequest = this.missionReimbursementRequestService.rejectMissionReimbursementRequestById(id);
            return new ResponseEntity<>(missionReimbursementRequest, HttpStatus.OK);
        } catch(InvalidMissionReimbursementRequestIdException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }  
    }


}

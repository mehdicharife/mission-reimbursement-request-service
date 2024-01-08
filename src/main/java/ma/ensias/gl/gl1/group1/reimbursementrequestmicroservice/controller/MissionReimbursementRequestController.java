package ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.controller;

import org.springframework.web.bind.annotation.RestController;

import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.domain.MissionReimbursementRequest;
import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.service.MissionReimbursementRequestService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<Object> createMissionReimbursementRequest(MissionReimbursementRequest missionReimbursementRequest) {
        return new ResponseEntity<>(
            this.missionReimbursementRequestService.saveMissionReimbursementRequest(missionReimbursementRequest),
            HttpStatus.CREATED
        );
    }
}

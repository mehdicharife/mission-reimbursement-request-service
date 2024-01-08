package ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.domain.MissionReimbursementRequest;


@Repository
public interface MissionReimbursementRequestRepository extends JpaRepository<MissionReimbursementRequest, Long>{
    
}

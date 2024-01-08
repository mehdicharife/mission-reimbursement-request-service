package ma.ensias.gl.gl1.group1.reimbursementrequestmicroservice.dto;


public class MissionReimbursementRequestDto {
    
    private Long missionId;

    private Long professorId;

    public MissionReimbursementRequestDto() {
    }


    public MissionReimbursementRequestDto(Long missionId, Long professorId) {
        this.missionId = missionId;
        this.professorId = professorId;
    }


    public Long getMissionId() {
        return this.missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getProfessorId() {
        return this.professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }


}

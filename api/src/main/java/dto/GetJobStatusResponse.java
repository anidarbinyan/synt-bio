package dto;

import dto.enums.JobStatus;

public class GetJobStatusResponse {
    private JobStatus jobStatus;

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }
}

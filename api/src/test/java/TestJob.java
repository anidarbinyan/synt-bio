import dto.enums.JobStatus;
import providers.Endpoints;
import org.testng.Assert;
import org.testng.annotations.Test;

import dto.ScheduleJobRequest;
import dto.ScheduleJobResponse;
import dto.GetJobStatusResponse;

import utils.Utils;
import java.util.concurrent.Callable;

import static utils.RequestsUtils.getJobStatus;
import static utils.RequestsUtils.getJobStatusRaw;
import static utils.RequestsUtils.getScheduleJobRaw;
import static utils.RequestsUtils.scheduleJob;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestJob extends BaseTest {

    private String validId = "validId";

    @Test(description = "TC-001")
    void testJobSuccessfulCreation() {
        ScheduleJobRequest request = new ScheduleJobRequest(validId);
        ScheduleJobResponse response = scheduleJob(request, Endpoints.SCHEDULE_JOB.url);

        Assert.assertNotNull(response.getJobId(), "JobId should not be null");
        Assert.assertFalse(response.getJobId().isEmpty(), "JobId should not be empty");

        getScheduleJobRaw(request, Endpoints.SCHEDULE_JOB.url)
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/scheduleJobSchema.json"));
    }

    @Test(description = "TC-002")
    public void testValidJob() {
        ScheduleJobRequest request = new ScheduleJobRequest(validId);
        ScheduleJobResponse response = scheduleJob(request, Endpoints.SCHEDULE_JOB.url);

        Assert.assertNotNull(response.getJobId(), "JobId should not be null");
        String jobId = response.getJobId();

        Utils.waitFor(100, new Callable<Boolean>() {
            @Override
            public Boolean call() {
                GetJobStatusResponse statusResponse = getJobStatus(jobId, 200);
                return statusResponse.getJobStatus() == JobStatus.SUCCESS;
            }
        }, 5, "Job did not complete with SUCCESS in 2 minutes");

        GetJobStatusResponse finalStatus = getJobStatus(jobId, 200);
        Assert.assertEquals(finalStatus.getJobStatus(), JobStatus.SUCCESS);

        getJobStatusRaw(jobId, 200)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/jobStatusSchema.json"));
    }

    @Test(description = "TC-003")
    public void testJobWithInvalidEntityId() {
        ScheduleJobRequest request = new ScheduleJobRequest("non-existing-entity");
        ScheduleJobResponse response = scheduleJob(request, Endpoints.GET_JOB.url);

        Utils.waitFor(120, new Callable<Boolean>() {
            @Override
            public Boolean call() {
                GetJobStatusResponse statusResponse = getJobStatus(response.getJobId(), 404);
                return statusResponse.getJobStatus() == JobStatus.ERROR;
            }
        }, 5, "Job did not complete with ERROR as expected");

        GetJobStatusResponse finalStatus = getJobStatus(response.getJobId(), 404);
        Assert.assertEquals(finalStatus.getJobStatus(), JobStatus.ERROR);
    }

    @Test(description = "TC-004")
    public void testGetInvalidJob() {
        getJobStatus(Endpoints.GET_JOB.url + "invalid-job", 404);
    }
}

package providers;

public enum Endpoints {

    SCHEDULE_JOB("scheduleJob"),
    GET_JOB("job/");

    public final String url;

    Endpoints(String endpoint) {
        this.url = endpoint;
    }
}
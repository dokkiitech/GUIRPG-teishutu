// Job クラス
public abstract class Job {
    private String jobName;

    public Job(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public abstract void applyEffect(Character character);
    public abstract void applyEffect(Enemy enemy);
}
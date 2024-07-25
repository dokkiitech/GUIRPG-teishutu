public class Enemy {
    private String name;
    private Job job;
    private int hp;
    private int motivation;

    public Enemy(String name, Job job) {
        this.name = name;
        this.job = job;
        this.hp = 100; // デフォルトのHP
        this.motivation = 100; // デフォルトのやる気
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }
}
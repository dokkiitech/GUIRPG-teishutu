// Action クラス
public abstract class Action {
    private String description;

    public Action(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(Character character);
    public abstract void execute(Enemy enemy);
}
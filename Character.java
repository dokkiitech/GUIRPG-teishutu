public class Character {
    private String name;
    private Action action;  // Action クラスを参照するフィールド
    private int hp;
    private int motivation;

    // 既存のコンストラクタ
    public Character(String name, Action action) {
        this.name = name;
        this.action = action;
        this.hp = 100; // デフォルトのHP
        this.motivation = 100; // デフォルトのやる気
    }

    // 追加のコンストラクタ (例)
    public Character(String name, Action action, int hp, int motivation) {
        this.name = name;
        this.action = action;
        this.hp = hp;
        this.motivation = motivation;
    }

    // getter と setter メソッド
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
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
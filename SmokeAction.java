// SmokeAction クラス
public class SmokeAction extends Action {
    public SmokeAction() {
        super("タバコを吸う");
    }

     
    public void execute(Character character) {
        character.setHp(character.getHp() - 5);
    }

     
    public void execute(Enemy enemy) {
        enemy.setHp(enemy.getHp() - 5);
    }
}

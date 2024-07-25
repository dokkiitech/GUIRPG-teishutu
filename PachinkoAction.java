// PachinkoAction クラス
public class PachinkoAction extends Action {
    public PachinkoAction() {
        super("パチンコをする");
    }

     
    public void execute(Character character) {
        character.setHp(character.getHp() - 10);
    }

     
    public void execute(Enemy enemy) {
        enemy.setHp(enemy.getHp() - 10);
    }
}
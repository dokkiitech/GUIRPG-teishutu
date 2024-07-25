// SchoolAction クラス
public class SchoolAction extends Action {
    public SchoolAction() {
        super("学校に行く");
    }

     
    public void execute(Character character) {
        character.setMotivation(character.getMotivation() + 10);
    }

     
    public void execute(Enemy enemy) {
        enemy.setHp(enemy.getHp() - 15);
    }
}
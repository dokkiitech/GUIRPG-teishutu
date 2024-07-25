public class Pachinkas extends Job {
    public Pachinkas() {
        super("パチンコ");
    }

     
    public void applyEffect(Character character) {
        // キャラクターに対しての効果を実装
        character.setHp(character.getHp() - 10); // 例: HPを10減少させる
    }

     
    public void applyEffect(Enemy enemy) {
        // 敵には効果を与えない
    }
}
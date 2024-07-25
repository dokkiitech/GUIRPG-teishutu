public class FutoKouko extends Job {
    public FutoKouko() {
        super("ふとこうこう");
    }

     
    public void applyEffect(Character character) {
        // キャラクターに対しての効果を実装
        character.setHp(character.getHp() - 20); // 例: HPを20減少させる
    }

     
    public void applyEffect(Enemy enemy) {
        // 敵には効果を与えない
    }
}
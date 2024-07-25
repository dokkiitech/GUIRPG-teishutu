public class Yankii extends Job {
    public Yankii() {
        super("ヤンキー");
    }

     
    public void applyEffect(Character character) {
        // キャラクターに対しての効果を実装
        character.setHp(character.getHp() - 15); // 例: HPを15減少させる
    }

     
    public void applyEffect(Enemy enemy) {
        // 敵には効果を与えない
    }
}
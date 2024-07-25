public class YankiiAction extends Action {
    public YankiiAction() {
        super("ヤンキーアクション");
    }

     
    public void execute(Character character) {
        // プレイヤーキャラクターに対するアクション
        character.setHp(character.getHp() - 10);
    }

     
    public void execute(Enemy enemy) {
        // 敵キャラクターに対するアクション（実装が必要な場合）
    }
}
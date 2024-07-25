public class FutoKoukoAction extends Action {
    public FutoKoukoAction() {
        super("不登校アクション");
    }

     
    public void execute(Character character) {
        // プレイヤーキャラクターに対するアクション
        character.setHp(character.getHp() - 20);
    }

     
    public void execute(Enemy enemy) {
        // 敵キャラクターに対するアクション（実装が必要な場合）
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RPGGameGUI extends JFrame {
    private JLabel characterImageLabel;
    private JLabel enemyImageLabel;
    private JTextArea logArea;
    private JButton pachinkoButton;
    private JButton smokeButton;
    private JButton schoolButton;
    private JButton repeatButton;
    private JButton exitButton;
    private Character player;
    private Enemy enemy;
    private boolean gameEnded = false;

    public RPGGameGUI() {
        setTitle("RPG Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // キャラクター画像ラベル
        characterImageLabel = new JLabel();
        add(characterImageLabel, BorderLayout.WEST);

        // 敵キャラクター画像ラベル
        enemyImageLabel = new JLabel();
        add(enemyImageLabel, BorderLayout.EAST);

        // ログエリア
        logArea = new JTextArea();
        logArea.setEditable(false);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        // ボタンパネル
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        // 行動ボタン
        pachinkoButton = new JButton("パチンコをする");
        pachinkoButton.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
                if (!gameEnded) {
                    performAction(new PachinkoAction());
                }
            }
        });
        buttonPanel.add(pachinkoButton);

        smokeButton = new JButton("タバコを吸う");
        smokeButton.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
                if (!gameEnded) {
                    performAction(new SmokeAction());
                }
            }
        });
        buttonPanel.add(smokeButton);

        schoolButton = new JButton("学校に行く");
        schoolButton.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
                if (!gameEnded) {
                    performAction(new SchoolAction());
                }
            }
        });
        buttonPanel.add(schoolButton);

        repeatButton = new JButton("再挑戦");
        repeatButton.setEnabled(false);
        repeatButton.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        buttonPanel.add(repeatButton);

        exitButton = new JButton("終了");
        exitButton.setEnabled(false);
        exitButton.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);
    }

    public void startGame() {
        // キャラクターと敵を設定
        Character[] characters = {
            new Character("わたなべかしゅう", new PachinkoAction()),
            new Character("にしむらこうせい", new YankiiAction()),
            new Character("かとうぜんかい", new FutoKoukoAction()),
            new Character("さかもとしょうた", new FutoKoukoAction())
        };

        Enemy[] enemies = {
            new Enemy("かたやまこたろう", new Job("メンヘラ先生") {
                 
                public void applyEffect(Character character) {
                    character.setHp(character.getHp() - 10);
                }

                 
                public void applyEffect(Enemy enemy) {
                    enemy.setHp(enemy.getHp() - 10);
                }
            }),
            new Enemy("おやまだかいと", new Job("優しい先生") {
                 
                public void applyEffect(Character character) {
                    character.setMotivation(character.getMotivation() - 20);
                }

                 
                public void applyEffect(Enemy enemy) {
                    enemy.setMotivation(enemy.getMotivation() - 20);
                }
            }),
            new Enemy("あおきとしろう", new Job("気分で性格の変わる先生") {
                 
                public void applyEffect(Character character) {
                    character.setHp(character.getHp() - 20);
                }

                 
                public void applyEffect(Enemy enemy) {
                    enemy.setHp(enemy.getHp() - 20);
                }
            }),
            new Enemy("もりようすけ", new Job("マッドサイエンティスト") {
                 
                public void applyEffect(Character character) {
                    character.setHp(character.getHp() - 30);
                }

                 
                public void applyEffect(Enemy enemy) {
                    enemy.setHp(enemy.getHp() - 30);
                }
            })
        };

        // キャラクターを選択
        String[] characterOptions = {
            "わたなべかしゅう", "にしむらこうせい", "かとうぜんかい", "さかもとしょうた"
        };
        String playerName = (String) JOptionPane.showInputDialog(
            this,
            "キャラクターを選んでください:",
            "キャラクター選択",
            JOptionPane.QUESTION_MESSAGE,
            null,
            characterOptions,
            characterOptions[0]
        );

        // プレイヤーキャラクターを設定
        for (Character c : characters) {
            if (c.getName().equals(playerName)) {
                player = c;
                break;
            }
        }

        // 敵キャラクターを設定
        Random random = new Random();
        enemy = enemies[random.nextInt(enemies.length)];

        // 初期状態の表示
        updateCharacterImage(player);
        updateEnemyImage(enemy);
        logArea.setText("ゲームを開始します！\n" +
                        player.getName() + " と " + enemy.getName() + " の対戦が始まります。\n");

        gameEnded = false;
        repeatButton.setEnabled(false);
        exitButton.setEnabled(false);
    }

    private void performAction(Action action) {
        // プレイヤーの行動を実行
        action.execute(player);
        logArea.append(action.getDescription() + " を実行しました。\n");

        // プレイヤーの行動が敵に与える影響を調整
        action.execute(enemy);

        // 敵の行動を実行
        enemyAttack();

        // 状態表示
        updateCharacterImage(player);
        updateEnemyImage(enemy);
        logArea.append(player.getName() + " のHP: " + player.getHp() + ", やる気: " + player.getMotivation() + "\n");
        logArea.append(enemy.getName() + " のHP: " + enemy.getHp() + ", やる気: " + enemy.getMotivation() + "\n");

        // 勝敗判定
        if (player.getHp() <= 0 || enemy.getHp() <= 0) {
            endGame(player, enemy);
        }
    }

    private void enemyAttack() {
        Random random = new Random();
        int actionIndex = random.nextInt(3); // 0: パチンコ, 1: タバコ, 2: 学校
        Action enemyAction;

        switch (actionIndex) {
            case 0:
                enemyAction = new PachinkoAction();
                break;
            case 1:
                enemyAction = new SmokeAction();
                break;
            case 2:
                enemyAction = new SchoolAction();
                break;
            default:
                enemyAction = new PachinkoAction(); // デフォルト
                break;
        }

        // 敵の行動を実行
        enemyAction.execute(player);
        logArea.append(enemy.getName() + " の行動によって、" + player.getName() + " のHPが減少しました。\n");
    }

    private void endGame(Character player, Enemy enemy) {
        gameEnded = true;
        repeatButton.setEnabled(true);
        exitButton.setEnabled(true);

        if (player.getHp() <= 0) {
            logArea.append(player.getName() + " は敗北しました。\n");
        } else if (enemy.getHp() <= 0) {
            logArea.append(player.getName() + " は勝利しました！\n");
        }
    }

    private void resetGame() {
        startGame();
    }

    private void updateCharacterImage(Character character) {
        // ここでキャラクターの画像を更新
        ImageIcon icon = new ImageIcon("images/" + character.getName() + ".png");
        characterImageLabel.setIcon(icon);
    }

    private void updateEnemyImage(Enemy enemy) {
        // ここで敵の画像を更新
        ImageIcon icon = new ImageIcon("images/" + enemy.getName() + ".png");
        enemyImageLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
             
            public void run() {
                RPGGameGUI game = new RPGGameGUI();
                game.setVisible(true);
                game.startGame();
            }
        });
    }
}
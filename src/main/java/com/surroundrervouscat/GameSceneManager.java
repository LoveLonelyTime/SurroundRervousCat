package com.surroundrervouscat;

public class GameSceneManager {
	public static final GameScene INSTRUCTION_GAME_SCENE = new InstructionGameScene();
	public static final GameScene WELCOME_GAME_SCENE = new WelcomeGameScene();
	public static final GameScene PLAY_GAME_SCENE = new PlayGameScene();
	private static GameScene gameScene;

	public static GameScene getGameScene() {
		return gameScene;
	}

	public static void chooseInstructionGameScene() {
		gameScene = INSTRUCTION_GAME_SCENE;
	}

	public static void chooseWelcomeGameScene() {
		gameScene = WELCOME_GAME_SCENE;
	}

	public static void choosePlayGameScene() {
		gameScene = PLAY_GAME_SCENE;
	}
}

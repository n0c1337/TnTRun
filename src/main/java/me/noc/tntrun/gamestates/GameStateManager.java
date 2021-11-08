package me.noc.tntrun.gamestates;

public class GameStateManager {
    private static GameStateManager instance;
    private GameState gameState;

    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        if (this.gameState != null) {
            this.gameState.stop();
        }
        this.gameState = gameState;
        this.gameState.start();
    }
}

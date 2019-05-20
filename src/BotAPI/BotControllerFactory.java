package BotAPI;

public interface BotControllerFactory {
    BotController createMasterBotController();
    BotController createMiniBotController();
}

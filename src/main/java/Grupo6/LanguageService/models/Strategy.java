package Grupo6.LanguageService.models;

public interface Strategy {
    void executeCode(Language lang);
    boolean createDir();
}

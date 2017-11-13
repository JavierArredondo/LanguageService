package Grupo6.LanguageService.models;

import java.util.List;

public class Language {
    // Atributes
    private String code;
    private String lang;
    private int statusCode;
    private List<String> output;
    private List<String> error;
    private Strategy strategy;
    // Constructor
    public Language(Strategy s){this.strategy = s;}
    public Language(){}
    // Getters and setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    // Method
    public void execute()
    {
        if(this.lang.equals("Python"))
            this.setStrategy(new StrategyPython());
        else if(this.lang.equals("C"))
            this.setStrategy(new StrategyC());
        else if(this.lang.equals("Java"))
            this.setStrategy((new StrategyJava()));

        this.strategy.executeCode(this);
    }
}

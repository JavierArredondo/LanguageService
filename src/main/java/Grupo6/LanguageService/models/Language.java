package Grupo6.LanguageService.models;

import java.util.List;

public class Language {
    // Atributes
    private String code;
    private String lang;
    private String p1;
    private String p2;
    private String p3;
    private String function;
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

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
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

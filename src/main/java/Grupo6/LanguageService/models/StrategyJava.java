package Grupo6.LanguageService.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StrategyJava implements Strategy {
    // Constructor

    // Method
    @Override
    public void executeCode(Language language)
    {
        System.out.println("_______________________\nLang: Java");
        this.createDir();
        String javaFile = this.getNameClass(language.getCode());
        boolean isCompiled = compileCode(language, javaFile);
        if(isCompiled)
        {
            try
            {
                ProcessBuilder builder = new ProcessBuilder("java", javaFile);
                Process compiler = builder.start();

                BufferedReader output = new BufferedReader(new InputStreamReader(compiler.getInputStream()));
                BufferedReader error = new BufferedReader(new InputStreamReader(compiler.getErrorStream()));

                System.out.println("\n> Program Output:");
                String out;
                List<String> outputs = new ArrayList<String>();
                while ((out = output.readLine()) != null)
                {
                    outputs.add(out);
                    System.out.println(out);
                }

                System.out.println("\n> Program Errors (if any):");
                String err;
                List<String> errors = new ArrayList<String>();
                while ((err = error.readLine()) != null)
                {
                    errors.add(err);
                    System.out.println(err);
                }
                language.setError(errors);
                language.setOutput(outputs);
            }
            catch(Exception e){}
        }
        else
        {
            System.out.println("Don't compile:");
            for(int i = 0; i < language.getError().size(); i++)
                System.out.println(language.getError().get(i));
        }
        System.out.println("_______________________");
    }

    private boolean compileCode(Language language, String javaFile)
    {
        boolean status = true;
        try
        {
            BufferedWriter file = new BufferedWriter(new FileWriter( javaFile +".java"));
            file.write(language.getCode());
            file.close();

            ProcessBuilder builder = new ProcessBuilder("javac", javaFile +".java");
            Process compiler = builder.start();

            BufferedReader error = new BufferedReader(new InputStreamReader(compiler.getErrorStream()));

            String err;
            language.setError(new ArrayList<String>());
            while ((err = error.readLine()) != null)
            {
                language.getError().add(err);
                status = false;
            }
        }
        catch (Exception e){}
        return status;
    }

    private String getNameClass(String code)
    {
        String[] codeAux = code.split(" ");
        int i;
        for (i = 0; i < codeAux.length; i++)
            if(codeAux[i].equals("class"))
                break;
        if(i == codeAux.length)
            return "Error";
        String ans = codeAux[i+1];
        if(!ans.contains("{"))
            return ans;
        i = ans.indexOf("{");
        return ans.subSequence(0, i).toString();
    }

    @Override
    public boolean createDir()
    {
        File theDir = new File("java");
        if (!theDir.exists())
        {
            System.out.println("Creating directory of Java: " + theDir.getName());
            try
            {
                theDir.mkdir();
            }
            catch (SecurityException se)
            {
                System.out.println("Can't create directory of Java: " + se);
                return false;
            }
        }
        return true;
    }
}

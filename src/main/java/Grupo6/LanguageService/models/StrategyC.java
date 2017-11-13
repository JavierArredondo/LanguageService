package Grupo6.LanguageService.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StrategyC implements Strategy {
    // Constructor
    public StrategyC() {}
    // Method
    @Override
    public void executeCode(Language language)
    {
        System.out.println("_______________________\nLang: C");
        this.createDir();
        boolean isCompiled = compileCode(language);
        if(isCompiled)
        {
            try
            {
                System.out.println("> Compiled!");
                ProcessBuilder builder = new ProcessBuilder("./a.out");
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
            System.out.println("> Didn't compile:");
            for(int i = 0; i < language.getError().size(); i++)
                System.out.println(language.getError().get(i));
        }
        System.out.println("_______________________");
    }

    private boolean compileCode(Language language)
    {
        boolean status = true;
        try
        {
            BufferedWriter file = new BufferedWriter(new FileWriter("c/myCode.c"));
            file.write(language.getCode());
            file.close();

            ProcessBuilder builder = new ProcessBuilder("gcc", "c/myCode.c");
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

    @Override
    public boolean createDir()
    {
        File theDir = new File("c");
        if (!theDir.exists())
        {
            System.out.println("Creating directory of C: " + theDir.getName());
            try
            {
                theDir.mkdir();
            }
            catch (SecurityException se)
            {
                System.out.println("Can't create directory of C: " + se);
                return false;
            }
        }
        return true;
    }
}
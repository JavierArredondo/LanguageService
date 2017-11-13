package Grupo6.LanguageService.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StrategyPython implements Strategy {
    // Constructor
    public StrategyPython() {}
    // Method
    @Override
    public void executeCode(Language language) {
        System.out.println("_______________________\nLang: Python");
        this.createDir();
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter("python/myCode.py"));
            file.write(language.getCode());
            file.close();

            ProcessBuilder builder = new ProcessBuilder("python", "python/myCode.py");
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
        catch (Exception e) {
            System.out.println("> Can't execute code: " + e);
        }
        System.out.println("_______________________\n");
    }

    @Override
    public boolean createDir()
    {
        File theDir = new File("python");
        if (!theDir.exists())
        {
            System.out.println("> Creating directory of Python: " + theDir.getName());
            try
            {
                theDir.mkdir();
            }
            catch (SecurityException se)
            {
                System.out.println("> Can't create directory of Python: " + se);
                return false;
            }
        }
        return true;
    }
}
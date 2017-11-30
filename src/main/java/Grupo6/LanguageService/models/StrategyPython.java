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
            System.out.println(outputs);
            System.out.println("\n> Program Errors (if any):");
            String err;
            List<String> errors = new ArrayList<String>();
            while ((err = error.readLine()) != null)
            {
                errors.add(err);
                System.out.println(err);
            }

            List<String> testCases = new ArrayList<String>();
            if(errors.size() == 0)
            {
                file = new BufferedWriter(new FileWriter("python/myCode.py"));
                file.write(language.getCode());
                file.write("\na = " + language.getFunction() + "(" + language.getP1() + ")\n");
                file.write("b = " + language.getFunction() + "(" + language.getP2() + ")\n");
                file.write("c = " + language.getFunction() + "(" + language.getP3() + ")\n");
                file.write("print (a)\n");
                file.write("print (b)\n");
                file.write("print (c)\n");
                file.close();
                builder = new ProcessBuilder("python", "python/myCode.py");
                compiler = builder.start();
                output = new BufferedReader(new InputStreamReader(compiler.getInputStream()));
                while((out = output.readLine()) != null)
                    testCases.add(out);
                testCase(language, testCases);
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
    public boolean createDir() {
        File theDir = new File("python");
        if (!theDir.exists()) {
            System.out.println("> Creating directory of Python: " + theDir.getName());
            try {
                theDir.mkdir();
            } catch (SecurityException se) {
                System.out.println("> Can't create directory of Python: " + se);
                return false;
            }
        }
        return true;
    }

    private void testCase(Language language, List<String> outputs)
    {
        int last = outputs.size();
        language.setP1(outputs.get(last - 3));
        language.setP2(outputs.get(last - 2));
        language.setP3(outputs.get(last - 1));
        outputs.remove(last - 1);
        outputs.remove(last - 2);
        outputs.remove(last - 3);
    }
}
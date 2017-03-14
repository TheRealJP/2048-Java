package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Regels {
    private String rules = "";

    public Regels() {

        try {
            Path regelsPath = Paths.get("src" + File.separator + "bestanden" + File.separator + "rules.txt");
            List<String> leesFile = Files.readAllLines(regelsPath);

            for (String s : leesFile) {
                if (s.equals("")) {
                    rules += System.lineSeparator();
                } else {
                    rules += s + System.lineSeparator();
                }
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public String getRegelTekst() {
        return rules;
    }
}
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by thook on 10/7/15.
 */
public class HamletParser {

    private String hamletData;

    public HamletParser(){
        this.hamletData = loadFile();
    }

    private String loadFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("hamlet.txt").getFile());
        StringBuilder result = new StringBuilder("");

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return result.toString();
    }

    public String getHamletData(){
        return hamletData;
    }

    public String replaceNames(){
    try {
        Path path = Paths.get(loadFile());
        Stream<String> lines = Files.lines(path);
        lines.map(line -> line.replaceAll("Hamlet", "Leon"));
        lines.map(line -> line.replaceAll("Horatio", "Tariq"));
        List<String> replaced = lines.collect(Collectors.toList());
        Files.write(path, replaced);

    return lines.toString();
    } catch (IOException e){
        e.printStackTrace();
        }
    return null;
    }




}

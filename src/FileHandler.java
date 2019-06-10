import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class FileHandler {
    private String path;

    FileHandler(String path){
        this.path = path;
    }

    public void write(ArrayList<float[][]> weights) throws Exception{

        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/william/IdeaProjects/NaturalNetwork/src/results.txt"));

        for (int i = 0; i<weights.size(); i++){
            float[][] temp = weights.get(i);
            for (int j = 0; j< temp.length; j++){
                for (int z = 0; z<temp[j].length; z++){
                    float t = temp[i][j];
                    writer.write(" " + String.valueOf(t)+ " ");
                }
                writer.write("\n");
            }
            writer.write("\n---------------------");
        }
    }

    public String randomFile() throws Exception{
        File maindir = new File(path);
        Random rn = new Random();
        if(maindir.exists() && maindir.isDirectory()){

            File[] arr = maindir.listFiles();
            int answer = rn.nextInt(arr.length);
            String numberfile = arr[answer].toString();


            File subfolder = new File(numberfile);
            File[] arrayOfNumbers = subfolder.listFiles();
            int pngValue = rn.nextInt(arrayOfNumbers.length); // can produce a nullpointer.

            String steamvalue = arrayOfNumbers[pngValue].toString();

            return steamvalue +" ≈ "+ numberfile;

        }
        return "noFileFound!";
    }
}

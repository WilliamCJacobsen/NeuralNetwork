import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.Arrays;
//should get 10 and 10 images when running.

public class ImageReader {
    private BufferedImage pic;

    ImageReader(BufferedImage pic){
        this.pic = pic;
    }

    public Node[] imageAsNodes(){
        float[] nv = normalizeImages();
        Node[] nodes = new Node[nv.length];

        for (int i = 0; i<nv.length; i++){
            nodes[i] = new Node(nv[i]);
        }
        return nodes;
    }

    private float[] normalizeImages(){
        int[] image = convertFromRGBToGray();
        int min = 0;
        int max = 255;
        for(int value : image){
            if (value > max){
                max = value;
            }
            if(value < min){
                min = value;
            }
        }

        float[] normalizedValues = new float[image.length];
        float maxMin = (max-min);

        for(int i= 0; i<image.length; i++){
            normalizedValues[i] = (image[i]-min)/maxMin;
        }

        return normalizedValues;
    }

    private int[] convertFromRGBToGray(){
        int xAxis = pic.getWidth();
        int yAxis = pic.getHeight();

        int[] image = new int[xAxis*yAxis];
        int counter = 0;

        for(int i = 0; i<yAxis; i++){
            for (int j = 0; j<xAxis; j++){
                int rgb = pic.getRGB(j, i);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb & 0xFF);
                int gray = (r + g + b) / 3;
                image[counter] = gray;
            }
        }
        return image;
    }
}

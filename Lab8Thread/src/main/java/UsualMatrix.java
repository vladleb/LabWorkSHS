import java.util.Random;
public class UsualMatrix {
    private final int[][] matr;
    private final int height;
    private final int width;

    public UsualMatrix (int height, int width){
        Random r = new Random (0);
        this.height = height;
        this.width = width;
        matr = new int[height][width];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                matr[i][j] = r.nextInt (32);
            }
        }
    }

    public UsualMatrix (int[][] matr){
        this.matr = matr.clone ();
        this.height = matr.length;
        this.width = matr[0].length;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder (matr.length * width);
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                sb.append(matr[i][j]).append(" ");
            }
            sb.append ('\n');
        }
        return new String (sb);
    }

    public int getLength (){
        return matr.length;
    }
    public int getWidth (){
        return width;
    }
    public final int[][] getMatrix (){
        return matr;
    }

}
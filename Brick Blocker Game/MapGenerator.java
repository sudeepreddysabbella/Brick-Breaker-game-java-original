import java.awt.*;
public class MapGenerator {
    public int map[][];
    public int brickheight;
    public int brickwidth;
    public MapGenerator(int x,int y){
    map=new int[x][y];
    for (int []map1 : map)
        {
        for(int j=0;j<=map[0].length;j++)
        {
            map1[j]=1;
        }
        }
    brickheight=540/x;
    brickwidth=540/y;
    }
    public void draw(Graphics2D g){
        for(int i=0;i<=map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]>0){
                    g.setColour(Color.red);
                    g.fillRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight); 
                    g.setStroke(new BasicStroke(3));
                    g.setColour(Color.black);
                    g.drawRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight);

                }
            }
        }
    }
    public void setBrickvalue(int value,int x,int y){
        map [x][y]=value;
    }
}
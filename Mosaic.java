//imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

//setting up the tile and orgnizing it.
class AlphabetTile extends JPanel {
    private int red, green, blue;
    private String letter;

    AlphabetTile() {
        super();//parent class constructor.
        ObtainRandomNums();
    }

    final public void ObtainRandomNums() {
        red = ANumberBetween(0,255);
        green = ANumberBetween(0,255);
        blue = ANumberBetween(0,255);

        letter = "a";
        if (ANumberBetween(0,12) == 1) {
            letter = "b";
        } else if (ANumberBetween(0,12) == 2) {
            letter = "c";
        } else if (ANumberBetween(0,12)== 3) {
            letter = "d";
        } else if (ANumberBetween(0,12)== 4) {
            letter = "e";
        } else if (ANumberBetween(0,12)== 5) {
            letter = "f";
        } else {
            letter = "g";
        }
    }

    private static int ANumberBetween(int min, int max) {
        Random r = new Random();
        return min + r.nextInt(max-min+1);
    }   

     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        //setting up square and circle tiles.
        if (ANumberBetween(0,12)== 2) {
            g.setColor(new Color(red,green,blue));
            g.fillRect(10,10,width-20,height-20);
            g.setColor(new Color(ContrastingColor(red),ContrastingColor(green),ContrastingColor(blue)));
            final int fsize=25;
            g.setFont(new Font("Helvetica", Font.PLAIN, fsize));
            int xstring = (width/2)-10;
            int ystring = (height/2)+10;
            g.drawString(letter,xstring,ystring);
        } else {
            g.setColor(new Color(red,green,blue));
            g.drawOval(10,10,width/2, height/2);
            g.fillOval(10,10,width/2,height/2);
            g.setColor(new Color(ContrastingColor(red),ContrastingColor(green),ContrastingColor(blue)));
            final int fsize=25;
            g.setFont(new Font("Helvetica", Font.PLAIN, fsize));
            int xstring = (width/3)-10;
            int ystring = (height/3)+10;
            g.drawString(letter,xstring,ystring);


        }

    }

    private static int ContrastingColor(int color) {
        return ((color+128)%256);//contrasting color method.
    }
     
        
}

class MosaicFrame extends JFrame implements ActionListener {
    private ArrayList<AlphabetTile> tilepart;

    public MosaicFrame() {
        setBounds(200,200,800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container content = getContentPane();
        content.setLayout(new BorderLayout());

        JPanel button = new JPanel();
        content.add(button, BorderLayout.SOUTH);

        JButton randomize = new JButton("Randomize");
        button.add(randomize);
        randomize.addActionListener(this);

        JPanel AlphabetPanel = new JPanel();
        content.add(AlphabetPanel, BorderLayout.CENTER);
        AlphabetPanel.setLayout(new GridLayout(12,12));

        tilepart = new ArrayList<AlphabetTile>();
        for(int i=1; i<145; i++) {
            AlphabetTile tile = new AlphabetTile();
            tilepart.add(tile);
            AlphabetPanel.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for(AlphabetTile tile : tilepart) {
            tile.ObtainRandomNums();
        }
        repaint();
    }
}
//main class
public class Mosaic {
    public static void main(String[] args) {

        MosaicFrame frame = new MosaicFrame();
        frame.setVisible(true);
    }
}
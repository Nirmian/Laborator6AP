package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    public DrawingPanel(MainFrame frame) {
        this.frame = frame; createOffscreenImage(); init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY()); repaint();
            }
        });
    }
    private void drawShape(int x, int y) {

        Random rand = new Random();

        int redValue = rand.nextInt(255);
        int greenValue = rand.nextInt(255);
        int blueValue = rand.nextInt(255);

        int radius = (int)(Math.random() * 100);
        int sides = (int)frame.configPanel.sidesField.getValue(); //get the value from UI (in ConfigPanel)

        Color color;
        if(frame.configPanel.colorCombo.getSelectedItem() == "Random") {
            color = new Color(redValue, greenValue, blueValue);   //create a transparent random Color.
        }
        else {
            color = new Color(0, 0, 0); // set the polygon's color to black
        }
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }
    @Override
    public void update(Graphics g) { }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}

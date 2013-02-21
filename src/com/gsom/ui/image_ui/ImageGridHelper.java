/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gsom.ui.image_ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Thush
 */
public class ImageGridHelper {

    public static JPanel getImageGridPanel(JPanel gridHolderPanel, Map<String, ArrayList<String>> map,MouseListener mListner) {

        String recreatedKey = "";
        File file = null;
        Image image = null;
        String firstFileName;
        // Set a grid layout in gridholderPanel
        gridHolderPanel.setLayout(new GridLayout(UIValues.getXGridCount(), UIValues.getYGridCount()));


        JLabel[][] cells = new JLabel[UIValues.getXGridCount()][UIValues.getYGridCount()];

        for (int ix = 0, xx = UIValues.getX_MIN(); ix < UIValues.getXGridCount(); ix++, xx++) {
            for (int iy = 0, yy = UIValues.getYMIN(); iy < UIValues.getYGridCount(); iy++, yy++) {
                //System.out.print("(" + ix + "," + iy + ") ");
                recreatedKey = xx + "," + yy;
                if (map.containsKey(recreatedKey)) {

                    firstFileName = map.get(recreatedKey).get(0);

                    file = new File(UIValues.getIMAGE_FOLDER_LOCATION() + "\\" + firstFileName + ".jpg");

                    try {
                        image = ImageIO.read(file).getScaledInstance(80, 80, BufferedImage.SCALE_SMOOTH);
                        cells[ix][iy] = getThubnailImage(image);
                        cells[ix][iy].setName(xx+","+yy);
                        cells[ix][iy].addMouseListener(mListner);

                    } catch (IOException ex) {
                        Logger.getLogger(ImageNetworkViewer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    cells[ix][iy] = new JLabel("");
                }
                cells[ix][iy].setBorder(BorderFactory.createLineBorder(Color.black));

                gridHolderPanel.add(cells[ix][iy]);
            }
        }
        return gridHolderPanel;
    }

    private void createTheGrid() {
    }

    public static JPanel getImageGridPanel(JPanel gridHolderPanel, ArrayList<String> fNames, int cols) {
        int rows =(int) Math.ceil(fNames.size()/cols);
        File file = null;
        Image image = null;
        // Set a grid layout in gridholderPanel
        gridHolderPanel.setLayout(new GridLayout(rows, cols));
        JLabel[] cells = new JLabel[fNames.size()];

        for (int i = 0; i < fNames.size(); i++) {

            file = new File(UIValues.getIMAGE_FOLDER_LOCATION() + "\\" + fNames.get(i) + ".jpg");

            try {
                image = ImageIO.read(file).getScaledInstance(80, 80, BufferedImage.SCALE_SMOOTH);
                cells[i] = getThubnailImage(image);

            } catch (IOException ex) {
                Logger.getLogger(ImageNetworkViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            gridHolderPanel.add(cells[i]);
        }
       
        //cells[ix][iy].setBorder(BorderFactory.createLineBorder(Color.black));
        return gridHolderPanel;
    }

    private static JLabel getThubnailImage(Image img) {
        JLabel label = new JLabel();
        label.setLayout(new GridLayout(2, 1));
        label.setIcon(new ImageIcon(img));
        return label;
    }
}
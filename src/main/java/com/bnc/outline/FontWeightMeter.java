package com.bnc.outline;

import com.intellij.util.ui.UIUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bnc
 * Date: 3/29/13
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class FontWeightMeter
{
    private HashMap<Character, Float> fontWeights;

    public FontWeightMeter()
    {
        fontWeights = new HashMap<Character, Float>(256);
    }

    public float getCharWeight(char ch)
    {
        if (!fontWeights.containsKey(ch)) {
            BufferedImage img = UIUtil.createImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Graphics g = img.getGraphics();
            int[] data = new int[1024];
            int alphaSum = 0;

            g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            g.setColor(new Color(0xffffff));
            g.drawString(Character.toString(ch), 0, 16);
            img.getData().getPixels(0, 0, 16, 16, data);

            for (int i = 0; i < data.length; i+=4) {
                alphaSum += data[i] & 0xff;
            }

            fontWeights.put(ch, Math.min(alphaSum / 9000.0f, 1.0f));
        }

        return fontWeights.get(ch);
    }
}

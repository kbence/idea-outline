package com.bnc.outline;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: bnc
 * Date: 3/28/13
 * Time: 10:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutlineWindow extends JPanel
{
    private OutlinePlugin plugin;
    private Project project;
    private FontWeightMeter fontWeightMeter;

    public OutlineWindow(OutlinePlugin plugin, Project project)
    {
        this.plugin  = plugin;
        this.project = project;

        this.fontWeightMeter = new FontWeightMeter();
    }

    @Override
    public void paint(Graphics g)
    {
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();


        if (editor != null) {
            displayDocument(g, editor.getDocument(), editor.getColorsScheme());
        }
    }

    private void displayDocument(Graphics g, Document document, EditorColorsScheme scheme)
    {
        Date startDate = new Date(), endDate;
        Color backColor = scheme.getDefaultBackground();
        Color foreColor = scheme.getDefaultForeground();

        g.setColor(backColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (int lineNumber = 0; lineNumber < document.getLineCount(); lineNumber++) {
            String line = document.getText(new TextRange(
                document.getLineStartOffset(lineNumber),
                document.getLineEndOffset(lineNumber)
            ));

            for (int colNumber = 0; colNumber < line.length(); colNumber++) {
                float weight = fontWeightMeter.getCharWeight(line.charAt(colNumber));

                if (weight > 0.0f) {
                    g.setColor(blendColors(backColor, foreColor, weight));
                    g.fillRect(colNumber, lineNumber, 1, 1);
                }
            }
        }

        endDate = new Date();

        System.out.println("Outline: redraw happened in " + (   (endDate.getTime() - startDate.getTime()) / 1000.0) + " seconds");
    }

    private Color blendColors(Color color1, Color color2, float bl)
    {
        bl = Math.max(0.0f, Math.min(1.0f, bl));

        float inv = 1.0f - bl;

        float[] rgb1 = new float[4];
        float[] rgb2 = new float[4];

        color1.getComponents(rgb1);
        color2.getComponents(rgb2);

        return new Color(
            rgb1[0] * inv + rgb2[0] * bl,
            rgb1[1] * inv + rgb2[1] * bl,
            rgb1[2] * inv + rgb2[2] * bl
        );
    }
}

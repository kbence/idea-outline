package com.bnc.outline;

import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: bnc
 * Date: 3/27/13
 * Time: 6:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutlineToolWindowContent extends JPanel
{
    static OutlineToolWindowContent instance;

    static
    {
        instance = new OutlineToolWindowContent();
    }

    Project project;

    public OutlineToolWindowContent()
    {
        setName("Outline");
    }

    public static OutlineToolWindowContent getInstance()
    {
        return instance;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    @Override
    public void paint(Graphics graphics)
    {
        Graphics2D g = (Graphics2D)graphics;

        if (g != null) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
}

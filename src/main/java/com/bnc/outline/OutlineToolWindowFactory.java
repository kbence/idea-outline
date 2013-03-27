package com.bnc.outline;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import javax.swing.text.AbstractDocument;

/**
 * Created with IntelliJ IDEA.
 * User: bnc
 * Date: 3/27/13
 * Time: 6:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutlineToolWindowFactory implements ToolWindowFactory
{
    public void createToolWindowContent(Project project, ToolWindow toolWindow)
    {
        OutlineToolWindowContent outline = OutlineToolWindowContent.getInstance();
        outline.setProject(project);

        Content content = ContentFactory.SERVICE.getInstance().createContent(outline, "Outline", false);
        toolWindow.getContentManager().addContent(content);
    }
}

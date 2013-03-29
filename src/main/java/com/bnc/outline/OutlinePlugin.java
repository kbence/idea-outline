package com.bnc.outline;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerAdapter;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: bnc
 * Date: 3/28/13
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutlinePlugin implements ProjectComponent
{
    final static String TOOLWINDOW_ID = "Outline";

    @Override
    public synchronized void initComponent()
    {
        initProjectListener();
        initOpenedProjects();
    }

    private void initProjectListener()
    {
        ProjectManager.getInstance().addProjectManagerListener(new ProjectManagerAdapter()
        {
            @Override
            public void projectOpened(Project project)
            {
                addWindowToProject(project);
            }

            @Override
            public void projectClosed(Project project)
            {
                removePluginFromProject(project);
            }
        });
    }

    private void initOpenedProjects()
    {
        for (Project project : ProjectManager.getInstance().getOpenProjects()) {
            addWindowToProject(project);
        }
    }

    private synchronized void addWindowToProject(Project project)
    {
        ContentFactory contentFactory = ServiceManager.getService(ContentFactory.class);

        ToolWindowManager twm = ToolWindowManager.getInstance(project);
        ToolWindow tw = twm.registerToolWindow(TOOLWINDOW_ID, false, ToolWindowAnchor.RIGHT);

        Content content = contentFactory.createContent(new OutlineWindow(this, project), "Code Outline", true);
        tw.getContentManager().addContent(content);
        tw.getContentManager().setSelectedContent(content);
    }

    @Override
    public synchronized void disposeComponent()
    {
    }

    private void removePluginFromProject(Project project)
    {
        ToolWindowManager.getInstance(project).unregisterToolWindow(TOOLWINDOW_ID);
    }

    @NotNull
    @Override
    public String getComponentName()
    {
        return "Outline Plugin";
    }

    @Override
    public void projectOpened()
    {
    }

    @Override
    public void projectClosed()
    {
    }
}

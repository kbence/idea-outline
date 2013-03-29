package com.bnc.outline;

import com.intellij.ide.highlighter.HighlighterFactory;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ComponentManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;

import javax.swing.*;
import java.awt.*;

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

    public OutlineWindow(OutlinePlugin plugin, Project project)
    {
        this.plugin  = plugin;
        this.project = project;
    }

    @Override
    public void paint(Graphics g)
    {
        Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();

        if (editor != null) {
            displayDocument(g, editor.getDocument());
        }
    }

    private void displayDocument(Graphics g, Document document)
    {
        g.setColor(new Color(0xffffff));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(0x000000));

        for (int lineNumber = 0; lineNumber < document.getLineCount(); lineNumber++) {
            String line = document.getText(new TextRange(
                document.getLineStartOffset(lineNumber),
                document.getLineEndOffset(lineNumber)
            ));

            for (int colNumber = 0; colNumber < line.length(); colNumber++) {
                if (line.charAt(colNumber) > 32) {
                    g.fillRect(colNumber, lineNumber, 1, 1);
                }
            }
        }
    }
}

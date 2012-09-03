package net.todo42.mylyn.basecamp.ui;

import net.todo42.mylyn.basecamp.core.BasecampCorePlugin;

import org.eclipse.mylyn.tasks.ui.ITasksUiConstants;
import org.eclipse.mylyn.tasks.ui.TasksUiImages;
import org.eclipse.mylyn.tasks.ui.TasksUiUtil;
import org.eclipse.mylyn.tasks.ui.editors.AbstractTaskEditorPageFactory;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditorInput;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.forms.editor.IFormPage;

/**
 * @author Dominik Hirt
 */
public class BasecampTaskEditorPageFactory extends AbstractTaskEditorPageFactory {

	@Override
	public boolean canCreatePageFor(TaskEditorInput input) {
		return (input.getTask().getConnectorKind().equals(BasecampCorePlugin.CONNECTOR_KIND) || TasksUiUtil.isOutgoingNewTask(
				input.getTask(), BasecampCorePlugin.CONNECTOR_KIND));
	}

	@Override
	public IFormPage createPage(TaskEditor editor) {
		return new BasecampTaskEditorPage(editor);
	}

	@Override
	public String[] getConflictingIds(TaskEditorInput input) {
		if (!input.getTask().getConnectorKind().equals(BasecampCorePlugin.CONNECTOR_KIND)) {
			return new String[] { ITasksUiConstants.ID_PAGE_PLANNING };
		}
		return null;
	}

	@Override
	public Image getPageImage() {
		return org.eclipse.mylyn.commons.ui.CommonImages.getImage(TasksUiImages.REPOSITORY_SMALL);
	}

	@Override
	public String getPageText() {
		return "XML";
	}

	@Override
	public int getPriority() {
		return PRIORITY_TASK;
	}

}

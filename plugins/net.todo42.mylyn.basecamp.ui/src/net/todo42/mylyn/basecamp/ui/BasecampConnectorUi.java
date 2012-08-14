package net.todo42.mylyn.basecamp.ui;

import net.todo42.mylyn.basecamp.core.BasecampCorePlugin;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.mylyn.tasks.core.IRepositoryQuery;
import org.eclipse.mylyn.tasks.core.ITaskMapping;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.ITaskRepositoryPage;
import org.eclipse.mylyn.tasks.ui.wizards.NewTaskWizard;
import org.eclipse.mylyn.tasks.ui.wizards.RepositoryQueryWizard;

/**
 * @author Dominik Hirt
 */
public class BasecampConnectorUi extends AbstractRepositoryConnectorUi {

	public BasecampConnectorUi() {
	}

	@Override
	public String getConnectorKind() {
		return BasecampCorePlugin.CONNECTOR_KIND;
	}

	@Override
	public ITaskRepositoryPage getSettingsPage(TaskRepository taskRepository) {
		return new BasecampRepositorySettingsPage(taskRepository);
	}

	@Override
	public boolean hasSearchPage() {
		return true;
	}

	@Override
	public IWizard getNewTaskWizard(TaskRepository repository, ITaskMapping selection) {
		return new NewTaskWizard(repository, selection);
	}

	@Override
	public IWizard getQueryWizard(TaskRepository repository, IRepositoryQuery query) {
		RepositoryQueryWizard wizard = new RepositoryQueryWizard(repository);
		wizard.addPage(new BasecampQueryPage(repository, query));
		return wizard;
	}

}

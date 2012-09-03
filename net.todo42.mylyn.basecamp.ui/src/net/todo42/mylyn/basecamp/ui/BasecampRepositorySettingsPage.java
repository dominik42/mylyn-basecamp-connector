package net.todo42.mylyn.basecamp.ui;

import java.net.MalformedURLException;
import java.net.URL;

import net.todo42.mylyn.basecamp.core.BasecampCorePlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.mylyn.commons.net.AuthenticationCredentials;
import org.eclipse.mylyn.commons.net.AuthenticationType;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Dominik Hirt
 */
public class BasecampRepositorySettingsPage extends AbstractRepositorySettingsPage
{

    public BasecampRepositorySettingsPage(TaskRepository taskRepository)
    {
        super("Basecamp Connector Settings", "Enter valid data", taskRepository);
        setNeedsAnonymousLogin(false);
        setNeedsAdvanced(false);
        setNeedsEncoding(false);
        setNeedsHttpAuth(false);
        setNeedsProxy(false);
    }

    @Override
    public void applyTo(TaskRepository repository)
    {
        super.applyTo(repository);
        String serverUrl = serverUrlCombo.getText();
        repository.setProperty(BasecampCorePlugin.REPOSITORY_KEY_SERVER_URL, serverUrl);

        AuthenticationCredentials httpCredentials = new AuthenticationCredentials(repositoryUserNameEditor.getStringValue(), repositoryPasswordEditor.getStringValue());
        repository.setCredentials(AuthenticationType.HTTP, httpCredentials, true);
    }

    @Override
    public void createControl(Composite parent)
    {
        super.createControl(parent);
    }

    @Override
    protected void createAdditionalControls(Composite parent)
    {
        // ignore		
    }

    @Override
    public String getConnectorKind()
    {
        return BasecampCorePlugin.CONNECTOR_KIND;
    }

    @Override
    protected Validator getValidator(final TaskRepository repository)
    {

        final String strServerUrl = serverUrlCombo.getText();

        return new Validator()
        {
            @Override
            public void run(IProgressMonitor monitor) throws CoreException
            {
                try
                {
                    if (strServerUrl != null)
                    {
                        new URL(strServerUrl);
                    }
                }
                catch (MalformedURLException mfuex)
                {
                    throw new CoreException(new Status(IStatus.ERROR, BasecampUiPlugin.ID_PLUGIN, NLS.bind("Malformed URL ''{0}''", strServerUrl)));
                }
            }
        };
    }

    @Override
    protected boolean isValidUrl(String url)
    {
        try
        {
            new URL(url);
            return true;
        }
        catch (MalformedURLException mfuex)
        {
            return false;
        }
    }
}

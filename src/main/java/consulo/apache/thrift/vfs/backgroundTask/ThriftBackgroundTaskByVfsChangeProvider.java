package consulo.apache.thrift.vfs.backgroundTask;

import javax.annotation.Nonnull;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.plugins.thrift.ThriftFileType;
import consulo.backgroundTaskByVfsChange.BackgroundTaskByVfsChangeProvider;
import consulo.backgroundTaskByVfsChange.BackgroundTaskByVfsParameters;

/**
 * @author VISTALL
 * @since 08.04.2015
 */
public class ThriftBackgroundTaskByVfsChangeProvider extends BackgroundTaskByVfsChangeProvider.ByFileType
{
	public ThriftBackgroundTaskByVfsChangeProvider()
	{
		super(ThriftFileType.INSTANCE);
	}

	@Override
	public void setDefaultParameters(@Nonnull Project project, @Nonnull VirtualFile virtualFile, @Nonnull BackgroundTaskByVfsParameters parameters)
	{
		if(SystemInfo.isWindows)
		{
			parameters.setExePath("thrift.exe");
		}
		else
		{
			parameters.setExePath("/usr/local/bin/thrift");
		}

		parameters.setProgramParameters("--gen java $FilePath$");
		parameters.setWorkingDirectory("$FileParentPath$");
		parameters.setOutPath("$FileParentPath$");
	}

	@Nonnull
	@Override
	public String getTemplateName()
	{
		return "Apache Thrift";
	}
}

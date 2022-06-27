package consulo.apache.thrift.vfs.backgroundTask;

import com.intellij.plugins.thrift.ThriftFileType;
import consulo.annotation.component.ExtensionImpl;
import consulo.platform.Platform;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileWatcher.BackgroundTaskByVfsChangeProvider;
import consulo.virtualFileSystem.fileWatcher.BackgroundTaskByVfsParameters;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 08.04.2015
 */
@ExtensionImpl
public class ThriftBackgroundTaskByVfsChangeProvider extends BackgroundTaskByVfsChangeProvider.ByFileType
{
	public ThriftBackgroundTaskByVfsChangeProvider()
	{
		super(ThriftFileType.INSTANCE);
	}

	@Override
	public void setDefaultParameters(@Nonnull Project project, @Nonnull VirtualFile virtualFile, @Nonnull BackgroundTaskByVfsParameters parameters)
	{
		if(Platform.current().os().isWindows())
		{
			parameters.setExePath("thrift.exe");
		}
		else
		{
			parameters.setExePath("/usr/local/bin/thrift");
		}

		parameters.setProgramParameters("-out $FileParentPath$ --gen java $FilePath$");
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

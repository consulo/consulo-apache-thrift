package org.mustbe.consulo.apache.thrift.vfs.backgroundTask;

import org.jetbrains.annotations.NotNull;
import org.mustbe.consulo.vfs.backgroundTask.BackgroundTaskByVfsChangeProvider;
import org.mustbe.consulo.vfs.backgroundTask.BackgroundTaskByVfsParameters;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.plugins.thrift.ThriftFileType;

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
	public void setDefaultParameters(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull BackgroundTaskByVfsParameters parameters)
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

	@NotNull
	@Override
	public String getTemplateName()
	{
		return "Apache Thrift";
	}
}

/**
 * @author VISTALL
 * @since 27-Mar-22
 */
module com.intellij.plugins.thrift {
	// TODO remove in future
	requires java.desktop;

	requires consulo.application.api;
	requires consulo.code.editor.api;
	requires consulo.color.scheme.api;
	requires consulo.component.api;
	requires consulo.document.api;
	requires consulo.file.editor.api;
	requires consulo.ide.api;
	requires consulo.index.io;
	requires consulo.language.api;
	requires consulo.language.editor.api;
	requires consulo.language.editor.impl;
	requires consulo.language.editor.ui.api;
	requires consulo.language.impl;
	requires consulo.localize.api;
	requires consulo.module.content.api;
	requires consulo.navigation.api;
	requires consulo.platform.api;
	requires consulo.project.api;
	requires consulo.project.content.api;
	requires consulo.ui.api;
	requires consulo.util.collection;
	requires consulo.util.io;
	requires consulo.util.lang;
	requires consulo.virtual.file.system.api;
	requires consulo.virtual.file.watcher.api;
}

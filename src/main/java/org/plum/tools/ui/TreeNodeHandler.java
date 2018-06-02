package org.plum.tools.ui;

import java.util.EventListener;

public interface TreeNodeHandler extends EventListener {
	public void invoke(TreeNode parent,TreeNode node);
}

package org.plum.tools.ui;

import java.util.ArrayList;
import java.util.List;

import org.plum.model.system.Func;

public class TreeNode implements Cloneable {

	private Integer funid;

	private String funcname;

	private Integer superid;

	private int order;

	private String path;

	private String icon;

	private boolean active;
	
	private boolean puissant;
	
	private TreeNode parent;
	

	public boolean isPuissant() {
		return puissant;
	}

	public void setPuissant(boolean puissant) {
		this.puissant = puissant;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	private List<TreeNode> children;

	public TreeNode(int funid, String funcname, int superid, int i, String path, String icon) {
		this.funcname = funcname;
		this.funid = funid;
		this.superid = superid;
		this.order = i;
		this.path = path;
		this.icon = icon;
	}

	public Integer getFunid() {
		return funid;
	}

	public void setFunid(Integer funid) {
		this.funid = funid;
	}

	public String getFuncname() {
		return funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public Integer getSuperid() {
		return superid;
	}

	public void setSuperid(Integer superid) {
		this.superid = superid;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int i) {
		this.order = i;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public TreeNode(Func func) {
		this.setFuncname(func.getFuncname());
		this.setFunid(func.getFunid());
		this.setOrder(func.getOrder());
		this.setPath(func.getPath());
		this.setSuperid(func.getSuperid());
		this.setIcon(func.getIcon());
	}


	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
		for (TreeNode treeNode : children) {
			treeNode.setParent(this);
		}
	}

	public void addChild(TreeNode node) {
		if (children == null) {
			children = new ArrayList<TreeNode>();
		}
		children.add(node);
		node.setParent(this);
		if (node.getSuperid() == null) {
			node.setSuperid(this.funid);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		TreeNode node = new TreeNode(funid, funcname, superid, order, path, icon);
		node.active = active;
		return node;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TreeNode){
			return ((TreeNode)obj).getFunid() == this.getFunid();
		}
		return false;
	}
	
}

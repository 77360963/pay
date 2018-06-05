package com.yunpan.service.service.bean;

import java.util.ArrayList;
import java.util.List;


/**
 * var alternateData = [ { text: '父节点 1', tags: ['2'], nodes: [ { text: '子节点 1',
 * tags: ['3'], nodes: [ { text: '孙子节点 1', tags: ['6'] }, { text: '孙子节点 2',
 * tags: ['3'] } ] }, { text: '子节点 2', tags: ['3'] } ] }, { text: '父节点 2', tags:
 * ['7'] }, { text: '父节点 3', icon: 'glyphicon glyphicon-earphone', href:
 * '#demo', tags: ['11'] }, { text: '父节点 4', icon: 'glyphicon
 * glyphicon-cloud-download', href: '/demo.html', tags: ['19'], selected: true
 * }, { text: '父节点 5', icon: 'glyphicon glyphicon-certificate', color: 'pink',
 * backColor: 'red', href: 'http://www.tesco.com', tags: ['available', '0'] } ];
 * 
 * @author yangmingming
 * 
 */
public class TreeViewNode {
	private Integer code;

	private String text;
	private String icon;
	private String href;
	private List<TreeViewNode> nodes;
	private int level;

	private Boolean selectable;
	private String parentcode;
	
	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public Boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(Boolean selectable) {
		this.selectable = selectable;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Boolean getSelectable() {
		return selectable;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void addNode(TreeViewNode node) {
		if (nodes == null) {
			nodes = new ArrayList<TreeViewNode>();
		}
		nodes.add(node);
	}

	public List<TreeViewNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeViewNode> nodes) {
		this.nodes = nodes;
	}

}

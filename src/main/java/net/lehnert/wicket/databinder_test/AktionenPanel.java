package net.lehnert.wicket.databinder_test;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.core.request.handler.IPageProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class AktionenPanel extends Panel {
	public AktionenPanel(String id, IModel<String> linkLabelModel, IPageLink link) {
		this(id, new IModel[] { linkLabelModel }, new IPageLink[] { link });
	}

	public AktionenPanel(String id, final IModel<String>[] linkLabelModels, final IPageLink[] links) {
		super(id);
		if (linkLabelModels.length != links.length) {
			throw new IllegalArgumentException("Es muss fuer jeden Link auch ein Label geben");
		}
		LoadableDetachableModel<List<Link>> linksModel = new LoadableDetachableModel<List<Link>>() {
			@Override
			protected List<Link> load() {
				ArrayList<Link> linkList = new ArrayList<Link>();
				for(int i=0; i<linkLabelModels.length; i++) {
					final IPageLink pageLink = links[i];
					Link link = new Link("link") {
						@Override
						public void onClick() {
							setResponsePage((WebPage)pageLink.getPage());
						}
					};
					link.add(new Label("linkLabel", linkLabelModels[i]));
					linkList.add(link);
				}
				return linkList;
			}
		};
		
		PropertyListView<Link> linkList = new PropertyListView<Link>("links", linksModel) {
			@Override
			protected void populateItem(ListItem<Link> item) {
				item.add(item.getModelObject());
			}
		};
		
		add(linkList);
		
	}
}

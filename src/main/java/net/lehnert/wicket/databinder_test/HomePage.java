package net.lehnert.wicket.databinder_test;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
		add(new Link("link") {
			@Override
			public void onClick() {
				setResponsePage(PersonListeAnzeigenPage.class);
			}
		});
    }
}

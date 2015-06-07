package net.lehnert.wicket.databinder_test;

import net.databinder.components.hib.DataForm;
import net.databinder.models.hib.HibernateObjectModel;
import net.lehnert.wicket.databinder_test.model.Person;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.Model;

public class PersonEditierenPage extends WebPage {
	public PersonEditierenPage(HibernateObjectModel<Person> rowModel) {
		HibernateObjectModel<Person> rowModelFixed = rowModel;
		if (rowModelFixed == null) {
			rowModelFixed = new HibernateObjectModel<Person>(Person.class);
		}
		final HibernateObjectModel<Person> model = rowModelFixed;
		DataForm<Person> form = new DataForm<Person>("editForm", model) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
				setResponsePage(PersonListeAnzeigenPage.class);
			}
		};
		add(form);
		form.add(new RequiredTextField<String>("name"));
		form.add(new RequiredTextField<String>("vorname"));
		form.add(new Button("speichern", Model.of("Speichern")));
	}
}

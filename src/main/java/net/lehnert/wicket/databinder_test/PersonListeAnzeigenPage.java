package net.lehnert.wicket.databinder_test;

import java.util.Arrays;

import net.databinder.hib.Databinder;
import net.databinder.models.hib.CriteriaFilterAndSort;
import net.databinder.models.hib.HibernateObjectModel;
import net.databinder.models.hib.SortableHibernateProvider;
import net.lehnert.wicket.databinder_test.model.Person;

import org.apache.wicket.Page;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.TextFilteredPropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class PersonListeAnzeigenPage extends WebPage {
	public PersonListeAnzeigenPage() {
		CriteriaFilterAndSort builder = new CriteriaFilterAndSort(Person.class, "name", true, false) {
			@Override
			public void buildUnordered(Criteria criteria) {
				criteria.add(Restrictions.eq("aktiv", true));
				super.buildUnordered(criteria);
			}
		};
		IColumn<Person, String>[] columns = new IColumn[] {
				new TextFilteredPropertyColumn<Person, String, String>(Model.of("Name"), "name"),
				new TextFilteredPropertyColumn<Person, String, String>(Model.of("Vorname"), "vorname"),
				new TextFilteredPropertyColumn<Person, String, String>(Model.of("Aktiv"), "aktiv"),
				new MyFilteredAbstractColumn<Person>(Model.of("Aktionen")) {
					@Override
					public void populateItem(Item<ICellPopulator<Person>> cellItem, String componentId, final IModel<Person> rowModel) {
						cellItem.add(new AktionenPanel(componentId, new IModel[] { Model.of("Editieren"), Model.of("Löschen") }, new IPageLink[] { new IPageLink() {
							@Override
							public Page getPage() {								
								return new PersonEditierenPage((HibernateObjectModel<Person>) ((CompoundPropertyModel)rowModel).getChainedModel());
							}
							@Override
							public Class<? extends Page> getPageIdentity() {
								return PersonEditierenPage.class;
							}
						}, new IPageLink() {
							@Override
							public Class<? extends Page> getPageIdentity() {
								return PersonListeAnzeigenPage.class;
							}
							@Override
							public Page getPage() {
								rowModel.getObject().setAktiv(false);
								Databinder.getHibernateSession().save(rowModel.getObject());
								Databinder.getHibernateSession().getTransaction().commit();
								Databinder.getHibernateSession().beginTransaction();
								return PersonListeAnzeigenPage.this;
							}
						}}));
					}
				}
		};
		DefaultDataTable<Person, String> dataTable = new DefaultDataTable<Person, String>("table", Arrays.asList(columns), new SortableHibernateProvider<Person>(Person.class, builder), 25);
		FilterForm<String> filterForm = new FilterForm<String>("filterForm", builder);
		dataTable.addTopToolbar(new FilterToolbar(dataTable, filterForm, builder));
		filterForm.add(dataTable);
		add(filterForm);
		add(new Link("neu") {
			@Override
			public void onClick() {
				setResponsePage(new PersonEditierenPage(null));
			}
		});
	}
}

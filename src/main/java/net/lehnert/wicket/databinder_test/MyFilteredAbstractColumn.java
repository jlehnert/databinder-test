package net.lehnert.wicket.databinder_test;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilteredAbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.GoAndClearFilter;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

public abstract class MyFilteredAbstractColumn<T> extends FilteredAbstractColumn<T, String> {

	public MyFilteredAbstractColumn(IModel<String> displayModel) {
		super(displayModel);
	}

	public MyFilteredAbstractColumn(final IModel<String> displayModel, final String sortProperty) {
		super(displayModel, sortProperty);
	}
	
	@Override
	public Component getFilter(String componentId, FilterForm<?> form) {
		return new GoAndClearFilter(componentId, form, new ResourceModel("tabelle.filtern"), new ResourceModel("tabelle.filter_loeschen"));
	}

}

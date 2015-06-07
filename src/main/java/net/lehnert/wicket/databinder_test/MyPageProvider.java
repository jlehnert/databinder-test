package net.lehnert.wicket.databinder_test;

import org.apache.wicket.core.request.handler.IPageProvider;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class MyPageProvider implements IPageProvider {

	
	@Override
	public PageParameters getPageParameters() {
		return null;
	}

	@Override
	public boolean isNewPageInstance() {
		return true;
	}

	@Override
	public Integer getRenderCount() {
		return 0;
	}

	@Override
	public void detach() {
		// NOP
	}

	@Override
	public boolean hasPageInstance() {
		return true;
	}

	@Override
	public boolean isPageInstanceFresh() {
		return true;
	}

}

package net.lehnert.wicket.databinder_test;

import net.databinder.hib.DataApplication;
import net.lehnert.wicket.databinder_test.model.Person;

import org.apache.wicket.markup.html.WebPage;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see net.lehnert.wicket.databinder_test.Start#main(String[])
 */
public class DatabinderTestApplication extends DataApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
	}
	
	@Override
	protected void configureHibernate(AnnotationConfiguration config) {		
		super.configureHibernate(config);
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		config.setProperty("hibernate.connection.driver_class","org.h2.Driver");
		config.setProperty("hibernate.connection.url", "jdbc:h2:./test");
		config.setProperty("hibernate.connection.username", "sa");
		config.setProperty("hibernate.connection.password", "sa");
		config.setProperty("hibernate.show_sql", "true");
		config.setProperty("hibernate.hbm2ddl.auto", "update");
		config.addAnnotatedClass(Person.class);
	}
}

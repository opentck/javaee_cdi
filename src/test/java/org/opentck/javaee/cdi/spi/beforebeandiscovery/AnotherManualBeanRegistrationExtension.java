package org.opentck.javaee.cdi.spi.beforebeandiscovery;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;

/**
 * An extension which registers a bean programmatically.
 *
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
public class AnotherManualBeanRegistrationExtension implements Extension
{
    public void registerBeans(@Observes BeforeBeanDiscovery event, BeanManager bm)
    {
        event.addAnnotatedType(bm.createAnnotatedType(AnotherBeanClassToRegister.class));
    }
}

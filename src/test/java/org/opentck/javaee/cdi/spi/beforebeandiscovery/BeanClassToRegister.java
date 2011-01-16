package org.opentck.javaee.cdi.spi.beforebeandiscovery;

/**
 * A valid bean class which will be registered manually using a CDI extension rather
 * than auto-discovered by inclusion in a bean archive.
 *
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
public class BeanClassToRegister
{
    public boolean isInvokable()
    {
        return true;
    }
}

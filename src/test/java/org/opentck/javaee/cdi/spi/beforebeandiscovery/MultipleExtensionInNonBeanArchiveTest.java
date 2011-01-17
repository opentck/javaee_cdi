package org.opentck.javaee.cdi.spi.beforebeandiscovery;

import javax.enterprise.inject.spi.Extension;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Validates that a bean can be registered by an extension that resides in a non-bean archive.
 *
 * @author <a href="http://community.jboss.org/people/LightGuard">Jason Porter</a>
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
@RunWith(Arquillian.class)
public class MultipleExtensionInNonBeanArchiveTest
{
    @Deployment
    public static Archive<?> createTestArchive()
    {
        // Our non-bean archive with an extension
        JavaArchive jar1 = ShrinkWrap.create(JavaArchive.class, "test1.jar")
              .addClasses(BeanClassToRegister.class);
        
        JavaArchive jar2 = ShrinkWrap.create(JavaArchive.class, "test2.jar")
              .addClasses(AnotherBeanClassToRegister.class, ManualBeanRegistrationExtension.class, AnotherManualBeanRegistrationExtension.class)
              .addServiceProvider(Extension.class, ManualBeanRegistrationExtension.class, AnotherManualBeanRegistrationExtension.class);

        // Web archive is necessary so that Arquillian can find the BeanManager
        return ShrinkWrap.create(WebArchive.class, "test.war")
              .addWebResource(EmptyAsset.INSTANCE, "beans.xml")
              .addLibraries(jar1, jar2);
    }

    @Test
    public void shouldFindBeanReference(AnotherBeanClassToRegister bean)
    {
        assertThat(bean, is(notNullValue()));
        BeanClassToRegister collaborator = bean.getCollaborator();
        assertThat(collaborator, is(notNullValue()));
        assertThat(collaborator.isInvokable(), equalTo(true));
    }
}

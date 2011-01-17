package org.opentck.javaee.cdi.spi.beforebeandiscovery;

import javax.inject.Inject;

public class AnotherBeanClassToRegister
{
   @Inject
   private BeanClassToRegister collaborator;
   
   public BeanClassToRegister getCollaborator()
   {
      return collaborator;
   }
}

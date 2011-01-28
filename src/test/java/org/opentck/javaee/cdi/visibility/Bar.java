package org.opentck.javaee.cdi.visibility;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class Bar
{
   @SuppressWarnings("unused")
   @Inject
   private Foo foo;
}

/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.explorer.activity.ju.testcases;

import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.la.NewBlankFunctionalDataflowDiagramAdapter;

public class LDFBActivityExplorerTestCase extends DiagramActivityExplorerTestCase {

  @Override
  protected AbstractCapellaNewDiagramHyperlinkAdapter createLink() {
    return new NewBlankFunctionalDataflowDiagramAdapter() {
      @Override
      protected boolean useDefaultName() {
        return true;
      }
    };
  }

  @Override
  public ModelElement getTestModelElement() {
    return ((NewBlankFunctionalDataflowDiagramAdapter) link).getModelElement(project);
  }

  @Override
  public AbstractFunction getStructure() {
    return context.getSemanticElement(LA__ROOT_LF);
  }

  @Override
  public String getDefaultName() {
    return "[LDFB] Root Logical Function";
  }

}

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
package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

public class CreateDuration extends InsertAllocatedFunctions {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };

    initializeTests(types, SequenceType.ES);
    initializeTests(typesIS, SequenceType.IS);
    //initializeTests(types, SequenceType.FS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);
    diagram.ceateDuration(GenericModel.FUNCTION_1, GenericModel.FUNCTION_2);
  }
  
  public void setUpDiagram(SequenceDiagram diagram) {
    super.setUpDiagram(diagram);
  }
}

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
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.OA_ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.model.GenericModel;

public class DeleteMessage extends CreateSequenceMessage {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] typesES = { BlockArchitectureExt.Type.OA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA, BlockArchitectureExt.Type.LA,
        BlockArchitectureExt.Type.PA };

    initializeTests(typesES, SequenceType.ES);
    initializeTests(typesIS, SequenceType.IS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);
    if (diagram instanceof ISDiagram) {
      ((ISDiagram) diagram).deleteMessage(GenericModel.EXCHANGE_ITEM_ALLOCATION_1, actor1, actor2);
    }
    else if (diagram instanceof OA_ESDiagram){
      ((OA_ESDiagram) diagram).deleteMessage(actor1, actor2);
    }
  }
}
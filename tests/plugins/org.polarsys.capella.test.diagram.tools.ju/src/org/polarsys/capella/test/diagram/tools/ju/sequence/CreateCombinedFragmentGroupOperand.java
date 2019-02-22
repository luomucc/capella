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

import org.polarsys.capella.core.data.interaction.InteractionOperatorKind;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.ESDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.ISDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;

public class CreateCombinedFragmentGroupOperand extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };

    initializeTests(types, SequenceType.ES);
    initializeTests(typesIS, SequenceType.IS);
    initializeTests(types, SequenceType.FS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);
    if(diagram instanceof ESDiagram) {
      testCreateCombinedFragment(diagram, actor1);
      testCreateCombinedFragment(diagram, actor2);
      testCreateCombinedFragment(diagram, component1);
    }
    if(diagram instanceof ISDiagram) {
      testCreateCombinedFragment(diagram, actor1);
    }
    if(diagram instanceof FSDiagram) {
      testCreateCombinedFragment(diagram, function1);
    }
  }
  
  public void testCreateCombinedFragment(SequenceDiagram diagram, String containerId) {
    diagram.createCombinedFragment(InteractionOperatorKind.ALT, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.ALT, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.LOOP, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.PAR, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.CRITICAL, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.NEG, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.ASSERT, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.STRICT, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.SEQ, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.IGNORE, containerId);
    diagram.createCombinedFragment(InteractionOperatorKind.CONSIDER, containerId);
  }
}

/*******************************************************************************
 * Copyright 2021 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.validation.rules.ju.testcases.dwf_sm;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.test.framework.api.OracleDefinition;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractRulesOnDesignTest;

public class Rule_DWF_SM_16 extends AbstractRulesOnDesignTest {

  @Override
  protected List<String> getScopeDefinition() {
    return Arrays.asList(new String[] { "e52917a6-5a3f-42d4-96e3-f0820f31742b" });
  }

  @Override
  protected EClass getTargetedEClass() {
    return CapellacommonPackage.Literals.STATE_MACHINE;
  }

  @Override
  protected String getRuleID() {
    return "org.polarsys.capella.core.data.common.validation.DWF_SM_16";
  }

  @Override
  protected List<OracleDefinition> getOracleDefinitions() {
    return Arrays.asList(new OracleDefinition[] {
        new OracleDefinition("e52917a6-5a3f-42d4-96e3-f0820f31742b", 1) });
  }

}

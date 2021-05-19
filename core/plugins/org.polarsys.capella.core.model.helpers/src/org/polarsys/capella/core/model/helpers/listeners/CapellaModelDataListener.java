/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.listeners;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.model.IDelegatedListener;

/**
 */
public class CapellaModelDataListener extends AdapterImpl {

  private IDelegatedListener delegatedListener;

  /**
   * @return {@code TRUE} if at least one contributor to the extension point {@code delegatedCapellaListener}
   *  wants the given notification {@code notification} to be filtered, and {@code FALSE} otherwise.
   */
  public boolean filterNotification(Notification notification) {
    IDelegatedListener delegatedListener = getDelegatedListener();
    if (null != delegatedListener) {
      return delegatedListener.filterNotification(notification);
    }
    return false;
  }

  /**
   * @return {@link IDelegatedListener}
   */
  private IDelegatedListener getDelegatedListener() {
    if (null == delegatedListener) {
      for (IConfigurationElement configurationElement : ExtensionPointHelper.getConfigurationElements("org.polarsys.capella.common.model", "DelegatedListener")) { //$NON-NLS-1$ //$NON-NLS-2$
        delegatedListener = (IDelegatedListener) ExtensionPointHelper.createInstance(configurationElement, ExtensionPointHelper.ATT_CLASS);
      }
    }
    return delegatedListener;
  }

  /**
   * 
   */
  protected void executeCommand(EObject context, ICommand command) {
	executeCommand(TransactionHelper.getEditingDomain(context), TransactionHelper.getExecutionManager(context), command);
  }

  /**
   * 
   */
  protected void executeCommand(Collection<? extends EObject> context, ICommand command) {
	executeCommand(TransactionHelper.getEditingDomain(context), TransactionHelper.getExecutionManager(context), command);
  }

  /**
   * This method verifies if there is an active transaction.<br>
   * If such case, the command is simply run.<br>
   * If not, the command is executed through the execution manager.<br>
   * @param editingDomain the editing domain
   * @param executionManager the execution manager
   * @param command the command to be executed
   */
  protected void executeCommand(TransactionalEditingDomain editingDomain, ExecutionManager executionManager, ICommand command) {
    if (editingDomain instanceof InternalTransactionalEditingDomain) {
      InternalTransaction activeTransaction = ((InternalTransactionalEditingDomain) editingDomain).getActiveTransaction();
      if (null != activeTransaction && activeTransaction.isActive()) {
        command.run();
      } else {
    	executionManager.execute(command);
      }
    }
  }
  
  /**
   * Rename given elements with the given name.<br>
   * An element is renamed only if :
   * <ul>
   * <li>it is an instance of AbstractNamedElement,</li>
   * <li>it doesn't already has the new name.</li>
   * </ul>
   * One command is used to rename all given elements.
   * @param elementsToRename
   * @param newName
   */
  protected void renameElements(final Collection<? extends EObject> elementsToRename, final String newName) {
    // Precondition : doesn't execute a command if nothing to rename (avoid unwanted Ctrl+z...).
    boolean renameNeeded = false;
    for (EObject elementToRename : elementsToRename) {
      if (!(elementToRename instanceof AbstractNamedElement)) {
        // Ignore elements which are null or not instance of AbstractNamedElement.
        continue;
      }
      AbstractNamedElement namedElementToRename = (AbstractNamedElement) elementToRename;
      if (!StringUtils.equals(namedElementToRename.getName(), newName)) {
        renameNeeded = true;
        break;
      }
    }
    if (!renameNeeded) {
      return;
    }
    // Execute command.
    executeCommand(elementsToRename, new AbstractReadWriteCommand() {
      public void run() {
        for (EObject elementToRename : elementsToRename) {
          if (!(elementToRename instanceof AbstractNamedElement)) {
            // Ignore elements which are null or not instance of AbstractNamedElement.
            continue;
          }
          AbstractNamedElement namedElementToRename = (AbstractNamedElement) elementToRename;
          if (!StringUtils.equals(namedElementToRename.getName(), newName)) {
            namedElementToRename.setName(newName);
          }
        }
      }
    });
  }
}

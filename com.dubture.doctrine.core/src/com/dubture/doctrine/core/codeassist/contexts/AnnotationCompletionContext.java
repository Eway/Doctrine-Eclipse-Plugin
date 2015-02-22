/*******************************************************************************
 * This file is part of the doctrine eclipse plugin.
 * 
 * (c) Robert Gruendler <r.gruendler@gmail.com>
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.dubture.doctrine.core.codeassist.contexts;

import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.php.internal.core.codeassist.contexts.PHPDocTagContext;
import org.eclipse.php.internal.core.util.text.TextSequence;

import com.dubture.doctrine.core.DoctrineNature;
import com.dubture.doctrine.core.log.Logger;

/**
 * 
 * {@link AnnotationCompletionContext} checks if we're
 * in a valid PHPDocTag completion context for annotations.
 * 
 * 
 * @author Robert Gruendler <r.gruendler@gmail.com>
 *
 */
@SuppressWarnings("restriction")
public class AnnotationCompletionContext extends PHPDocTagContext {
	
	
	@Override
	public boolean isValid(ISourceModule sourceModule, int offset,
			CompletionRequestor requestor) {

		if (!super.isValid(sourceModule, offset, requestor) == true) {
			return false;
		}

		try {
			// wrong nature
			if(!sourceModule.getScriptProject().getProject().hasNature(DoctrineNature.NATURE_ID)) {
				return false;	
			}
			
			TextSequence sequence = getStatementText();
			int start = sequence.toString().lastIndexOf("@");
			int end = sequence.toString().length();
			
			String line = sequence.toString().substring(start, end);
			
			// we're inside an annotation's parameters
			// can't complete this so far.
			if (line.contains("(")) {
				return false;
			}
			if (line.trim().endsWith("*")) { //$NON-NLS-1$
				return false;
			}
			
		} catch (Exception e) {
			Logger.logException(e);
			return false;			
		}
		
		return true;
	}
}

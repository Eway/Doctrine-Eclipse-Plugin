/*******************************************************************************
 * This file is part of the Symfony eclipse plugin.
 * 
 * (c) Robert Gruendler <r.gruendler@gmail.com>
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.dubture.doctrine.annotation.parser.antlr.error;


import org.antlr.runtime.RecognitionException;

/**
 * 
 * Interface to Antlr error-reporting
 * 
 * 
 * @author "Robert Gruendler <r.gruendler@gmail.com>"
 *
 */
public interface IAnnotationErrorReporter {
	
	void reportError(String header, String message, RecognitionException e);	

}

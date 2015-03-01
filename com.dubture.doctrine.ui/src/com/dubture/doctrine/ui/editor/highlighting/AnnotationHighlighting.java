/*******************************************************************************
 * This file is part of the Doctrine eclipse plugin.
 *
 * (c) Robert Gruendler <r.gruendler@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.dubture.doctrine.ui.editor.highlighting;

import java.util.List;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.php.internal.core.ast.nodes.Comment;
import org.eclipse.php.internal.ui.editor.highlighter.AbstractSemanticApply;
import org.eclipse.php.internal.ui.editor.highlighter.AbstractSemanticHighlighting;
import org.eclipse.swt.graphics.RGB;

import com.dubture.doctrine.annotation.model.Annotation;
import com.dubture.doctrine.annotation.parser.AnnotationCommentParser;
import com.dubture.doctrine.annotation.parser.antlr.SourcePosition;
import com.dubture.doctrine.core.utils.AnnotationUtils;

/** 
 *
 * Highlighting for Annotations.
 *
 */
@SuppressWarnings("restriction")
public class AnnotationHighlighting extends AbstractSemanticHighlighting {

    protected class AnnotationApply extends AbstractSemanticApply {

        protected ISourceModule sourceModule;
        protected AnnotationCommentParser parser;

        public AnnotationApply() {
            this.sourceModule = getSourceModule();
            this.parser = AnnotationUtils.createParser();
        }

        @Override
        public boolean visit(Comment comment) {
        	if (comment.getCommentType() != Comment.TYPE_PHPDOC) {
        		return true;
        	}
            List<Annotation> annotations = AnnotationUtils.extractAnnotations(parser, comment, sourceModule);
            for (Annotation annotation : annotations) {
                SourcePosition sourcePosition = annotation.getSourcePosition();
                highlight(sourcePosition.startOffset, sourcePosition.length);
            }

            return true;
        }

    }

    public AnnotationHighlighting() {
        super();
    }

    @Override
    public String getDisplayName() {
        return "Annotations";
    }

    @Override
    public AbstractSemanticApply getSemanticApply() {
        return new AnnotationApply();
    }

    @Override
    public void initDefaultPreferences() {
        getStyle().setUnderlineByDefault(false).setDefaultTextColor(
                new RGB(64, 64, 64));
    }
}

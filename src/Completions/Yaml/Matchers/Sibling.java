package Completions.Yaml.Matchers;

import com.intellij.patterns.PatternCondition;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import com.jetbrains.php.lang.parser.PhpElementTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.yaml.YAMLTokenTypes;
import org.jetbrains.yaml.YAMLUtil;
import org.jetbrains.yaml.psi.YAMLKeyValue;

public class Sibling extends PatternCondition<PsiElement>
{
    public Sibling()
    {
        super("Verify previous sibling.");
    }

    @Override
    public boolean accepts(@NotNull PsiElement psiElement, ProcessingContext processingContext)
    {
        if (psiElement instanceof PsiComment) {
            return false;
        }

        YAMLKeyValue keyValue;
        LeafPsiElement previous;
        try {
            keyValue = PsiTreeUtil.getParentOfType(psiElement, YAMLKeyValue.class);
            previous = (LeafPsiElement)psiElement.getParent().getPrevSibling();
        }
        catch (Exception e) {
            return false;
        }

        if (keyValue == null) {
            return false;
        }

        String keyName = keyValue.getName();
        if (keyName == null) {
            return false;
        }

        IElementType elementType = previous.getElementType();

        if (keyName.equals("match")) {
            Integer parentTabLength = YAMLUtil.getIndentInThisLine(keyValue.getPrevSibling());
            Integer currentTabLength = YAMLUtil.getIndentInThisLine(previous);

            if (currentTabLength <= parentTabLength) {
                return false;
            }

            return elementType == YAMLTokenTypes.INDENT;
        }

        // Previous key already has a value.
        if (psiElement.getPrevSibling() != null) {
            return false;
        }

        return elementType == PhpElementTypes.WHITE_SPACE;
    }
}

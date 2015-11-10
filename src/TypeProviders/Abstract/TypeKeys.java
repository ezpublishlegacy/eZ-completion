package TypeProviders.Abstract;

import com.intellij.psi.PsiElement;
import com.jetbrains.php.lang.psi.elements.PhpTypedElement;

public class TypeKeys
{
    // Phield is right up there with Phteven.
    public static final char FIELD_KEY = 'φ';
    // Omicron persei 8
    public static final char ARRAY_FIELD_KEY = 'Ο';
    // Kappa: "Kåntent".
    public static final char CONTENT_KEY = 'κ';

    protected static String getType(PsiElement psiElement, char typeKey)
    {
        if (psiElement == null) {
            return null;
        }

        PhpTypedElement typedElement;
        try {
            typedElement = (PhpTypedElement)psiElement;
        }
        catch (Exception e) {
            return null;
        }

        for (String type : typedElement.getType().getTypes()) {
            if (type == null || type.indexOf(typeKey) > 0) {
                return type;
            }
        }

        return null;
    }

    public static boolean isContent(PsiElement psiElement)
    {
        return getType(psiElement, CONTENT_KEY) != null;
    }

    public static boolean isArrayofFields(PsiElement psiElement)
    {
        return getType(psiElement, ARRAY_FIELD_KEY) != null;
    }

    public static boolean isField(PsiElement psiElement)
    {
        return getType(psiElement, TypeKeys.FIELD_KEY) != null;
    }

    public static String getTypeString(PsiElement psiElement, char typeKey)
    {
        String type = getType(psiElement, typeKey);
        if (type == null) {
            return null;
        }

        String typeString = type.replaceAll("#.", "").replace("IntellijIdeaRulezzz", "").trim();
        if (!typeString.contains(".")) {
            return typeString;
        }

        String[] parts = typeString.split("\\.");

        return parts[0];
    }
}

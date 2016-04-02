package Completions.Yaml.Providers;

import Completions.Php.Repository.Completion;
import Framework.CompletionContainer;
import Framework.CompletionPreloader;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.project.Project;
import com.intellij.util.ProcessingContext;
import java.util.ArrayList;
import java.util.Collection;

public class MatchValues extends YamlCompletionProvider
{
    @Override
    protected Collection<LookupElement> getLookupElements(Project project, ProcessingContext context)
    {
        String type = (String)context.get("type");
        String value = (String)context.get("value");
        if (type == null || value == null) {
            return null;
        }

        if (!type.equals("ContentType") && !type.equals("ParentContentType")) {
            return null;
        }

        CompletionPreloader preloader = CompletionPreloader.getInstance(project);
        CompletionContainer completions = preloader.getCurrentCompletions();

        ArrayList<Completion> valueCompletions = null;
        if (value.equals(("Id"))) {
             valueCompletions = completions.getContentTypeIdCompletions();
        }
        else if (value.equals("Identifier")) {
            valueCompletions = completions.getContentTypeIdentifierCompletions();
        }

        if  (valueCompletions == null) {
            return null;
        }

        return new ArrayList<>(valueCompletions);
    }
}

Provides code-completion for the eZ-API
---------------------------------------

Installation:
-------------
1. Install plugin.
2. Install the bundle: flageolett/ezcompletionbundle

Requirements:
-------------
PhpStorm 11 or IntelliJ IDEA equivalent.
Bundle: https://github.com/whitefire/ez-completion-bundle

For best results you should also enable:
 - Symfony2-plugin
 - Yaml-plugin
 - Twig-plugin

If you need to fetch completions from a remote installation:
 - Remote Php Interpreters
 - SSH Remote-run.

What does it do?
----------------
Provides completion for:

* repository-services:
* query-criteria:
* Available fields for Content (also in Twig)
* Config-resolver

For a complete list take a look in the "Examples" directory within the bundle-repository.

Commands:

* Clear cache from IDE.
* Toggle assetic-watch.
* Refresh completions.

Usage:
------
Configure a PHP-interpreter (local or remote).
Request completions from within literals.
Completions are cached, so remember to refresh completions when necessary.

Troubleshooting:
----------------
Run the ezcode:completion-command and make sure that it does not output anything other than valid JSON.
Any errors in the console?
If all is good, click the "refresh-completions icon" and try again.

Known issues:
-------------
You might need to clear the symfony-cache before refreshing completions.
Rename-refactoring does not work for @ContentType doc-blocks.

Current:
--------
* Write blog-post

Roadmap 1.0.5:
--------------
* Migrate from PhpTypeProvider2 to PhpTypeProvider3
* Move settings to Frameworks-section.
* Completions for getFields(...) needs to be reworked.
    - Return value is on the format: ['eng-GB']['identifier']
* Look into handleElementRename(...) and see if it might solve the refactoring-issue.
* Might it be possible to make completions fully searchable?
    - Easily noticed when completing numerics.
    - Seems like some magic is needed for this to work.
* Replacing numeric-completions is cumbersome.
* Replace usages of the ContentClass-name with its correct equivalent ContentType
* Allow user to select ContentType if multiple are detected.
* Completions for supported template-scopes (global, sitaccess etc...)
* Donut?
* Make use of completion-confidence for auto-completion when applicable.
    - Add CompletionConfidence (order="before javaSkipAutopopupInStrings")
* Add better error-message if Bundle is missing.
* context.getEditor().getCaretModel().getPrimaryCaret().moveCaretRelatively(2, 0, false, false);
    - This one needs to get smarter. Check all completions and take parameters into consideration.
* Walk through initial-install and see if the UX can be improved.
* Goto definitions for ezsettings. (gotoSymbolContributor?)
* Goto definitions for yaml-classes/definitions.
* Inspections for field-accessors.
* Streamline insertion of completions.
* Support completions for multiple-values for criteria.
* Display FieldType in field-completion lookup. (setTypeText)
* Make data-provider for completions abstract.
* Data-duplication in the completion-bundle is getting ridiculous.
* Make better use of processingContext.
* Type providers for fields in twig.
* Automatic eZDoc if possible.
    - Direct sql-access through plugin.
        - loadContent => resolve content-type.
    - Does database access yield other possibilities as well?
        - Database is being difficult, postponed for now.
* Provide an ad-hoc way to search for ContentTypes and Fields.

Roadmap 1.0.6:
--------------
* Provide better progress-indicators for commands
    - Time based (beware of yml-changes)
    - Activity-indicator for watch.
* Execute SearchService-query:
    - Add support for services.
    - Ask for unresolved criteria-values.
    - Modify method to return eval'd data.
    - Present the results in an easily browsible manner.
    - Perhaps ezsh could be of service?

package Framework.Console;

import com.intellij.openapi.project.Project;
import com.jetbrains.php.config.commandLine.PhpCommandSettingsBuilder;

public class ConsoleCommandFactory
{
    public enum CommandName {
        ASSETIC_WATCH("assetic:watch"),
        CLEAR_CACHE("cache:clear"),
        REFRESH_COMPLETIONS("ezcode:completion");

        protected String command;

        CommandName(String command) { this.command = command; }

        @Override
        public String toString() { return command; }
    }

    public static ConsoleService createConsoleCommand(CommandName commandName, Project project)
    {
        ConsoleService consoleService = null;
        Command command;

        try {
            // Will generate an error if remote-dependencies are missing.
            PhpCommandSettingsBuilder.create(project, false);
            switch (commandName) {
                case ASSETIC_WATCH:
                    command = new Assetic(commandName.toString(), project) { @Override public void success() {}};
                    consoleService = new ConsoleService(project, "Watching assets for environment: " + command.getEnvironment(), false);
                    consoleService.setEzCommand(command);
                break;
                case CLEAR_CACHE:
                    command = new Command(commandName.toString(), project) { @Override public void success() {}};
                    command.setExpectedResultLength(755);
                    consoleService = new ConsoleService(project, "Clearing cache for environment: " + command.getEnvironment(), false);
                    consoleService.setEzCommand(command);
                break;
                case REFRESH_COMPLETIONS:
                    command = new Framework.Console.RefreshCompletions(commandName.toString(), project);
                    command.setExpectedResultLength(249035);
                    command.setStoreResult(true);
                    consoleService = new ConsoleService(project, "Fetching eZ-completions", false);
                    consoleService.setEzCommand(command);
                break;
            }
        } catch (Exception e) {
            ConsoleService.notifyFailure(e.getMessage(), commandName.toString(), project);
        }

        return consoleService;
    }

    public static void runConsoleCommand(CommandName commandName, Project project)
    {
        ConsoleService consoleService = createConsoleCommand(commandName, project);
        if (consoleService != null) {
            consoleService.queue();
        }
    }
}

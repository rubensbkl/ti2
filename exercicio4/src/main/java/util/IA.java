package util;

import java.util.Arrays;
import java.util.List;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
// import com.azure.ai.openai.models.ChatRequestAssistantMessage;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestSystemMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
//import com.azure.ai.openai.models.CompletionsUsage;
import com.azure.core.credential.AzureKeyCredential;
//import com.azure.core.util.Configuration;

public class IA {

	private String apiKey = "<your-apikey>";
	private String endpoint = "<your-endpoint>";
    private String deploymentName = "<your-modelname>";
    

    public void consultarIA(String prompt) {

        // Construir o prompt system
        String promptSystem = "Você é uma inteligencia artificial de teste para retornar informações para o cliente com base em suas perguntas.";

        // Construir o prompt user
        String promptUser = prompt;

        System.out.println("==================== PROMPT ENVIADO À IA ====================");
        System.out.println("SYSTEM PROMPT: " + promptSystem);
        System.out.println("\nUSER PROMPT: " + promptUser);
        System.out.println("==============================================================");

        // Inicializa o cliente da Azure OpenAI
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new AzureKeyCredential(apiKey))
                .endpoint(endpoint)
                .buildClient();

        // Monta as mensagens
        List<ChatRequestMessage> messages = Arrays.asList(
                new ChatRequestSystemMessage(promptSystem),
                new ChatRequestUserMessage(promptUser)
        );

        // Define as opções da completude
        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(messages);
        chatCompletionsOptions.setMaxTokens(4096);
        chatCompletionsOptions.setTemperature(1d);
        chatCompletionsOptions.setTopP(1d);

        // Faz a requisição e obtém a resposta
        ChatCompletions chatCompletions = client.getChatCompletions(deploymentName, chatCompletionsOptions);

        System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());
        }
    }
}

package br.com.projeto.view;

import br.com.projeto.util.SessaoUsuario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChatView {
    public void start (Stage stage) {
        // --- BARRA LATERAL (SIDEBAR) ---
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(250);
        sidebar.setStyle("-fx-background-color: #2b2b2b;"); // Fundo escuro

        Label lbll0la = new Label("Olá, " + SessaoUsuario.getUsuarioLogado().getNome());
        lbll0la.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");

        Button btnNovaConversa = new Button("+ Nova Conversa");
        btnNovaConversa.setMaxWidth(Double.MAX_VALUE); // Faz o botão esticar
        btnNovaConversa.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white; -fx-cursor: hand;");

        // Lista para exibir o histórico de conversas
        ListView<String> listaConversas = new ListView<>();
        listaConversas.getItems().addAll("Chat sobre Java", "Dúvidas MongoDB"); // Dados falsos temporários
        listaConversas.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        Button btnSair = new Button("Sair(Logout)");
        btnSair.setMaxWidth(Double.MAX_VALUE);
        btnSair.setStyle("-fx-background-color: #d9534f; -fx-text-fill: white; -fx-cursor: hand;");

        // Ação de Logout
        btnSair.setOnAction(e -> {
            SessaoUsuario.logout();
            stage.close();
            new LoginView().start(new Stage());
        });

        sidebar.getChildren().addAll(lbll0la, btnNovaConversa, listaConversas, btnSair);

        // --- ÁREA DE MENSAGENS (CENTRO) ---
        BorderPane areaCentral = new BorderPane();
        areaCentral.setPadding(new Insets(20));
        areaCentral.setStyle("-fx-background-color: #f4f4f4;"); // Fundo claro

        // Respostas IA
        TextArea areaMensagens = new TextArea("IA Chat Desk: Olá! Como posso ajudar você?\n");
        areaMensagens.setEditable(false);
        areaMensagens.setWrapText(true); // Quebra de linha automática
        areaMensagens.setFont(Font.font("Segoe UI", 14));
        areaMensagens.setStyle("-fx-control-inner-background: #ffffff");

        areaCentral.setCenter(areaMensagens);

        // --- BARRA DE DIGITAÇÃO (RODAPÉ) ---
        HBox rodape = new HBox(10);
        rodape.setPadding(new Insets(10, 0, 0, 0));
        rodape.setAlignment(Pos.CENTER);

        TextArea campoDigitacao = new TextArea();
        campoDigitacao.setPromptText("Digite a sua pergunta aqui...");
        campoDigitacao.setPrefRowCount(2);
        campoDigitacao.setWrapText(true);
        campoDigitacao.setMaxWidth(600);

        Button btnEnviar = new Button("Enviar");
        btnEnviar.setPrefHeight(45);
        btnEnviar.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand");
    }
}

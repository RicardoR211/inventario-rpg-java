# 🎒 Sistema de Inventário RPG em Java

Este projeto é uma aplicação de console que simula o sistema de inventário de um personagem de RPG. Ele foca na gestão dinâmica de itens, diferenciando entre armazenamento geral (mochila) e slots de equipamento.

Este é o **terceiro e último projeto** da trilha de aprendizado prático, consolidando os conhecimentos de Coleções, POO e Interação entre Sistemas.

---

## 🎮 Funcionalidades

O jogador pode interagir com o sistema através de um menu para:

-   **Lootear:** Encontrar itens aleatórios (gerados a partir de um arquivo externo) e adicioná-los à mochila.
-   **Gerenciar Mochila:** Visualizar todos os itens coletados.
-   **Equipar Itens:** Equipar armaduras em seus slots específicos (Cabeça, Peitoral, etc.). O sistema gerencia a troca automática de itens (o antigo volta para a mochila).
-   **Usar Consumíveis:** Beber poções, aplicando seus efeitos e consumindo suas cargas (`usosRestantes`). Quando acabam, são removidos.
-   **Visualizar Equipamentos:** Ver o estado atual de todos os slots de armadura do personagem.

---

## 🛠️ Tecnologias e Conceitos Chave

-   **Linguagem:** Java
-   **Bibliotecas:** Gson (Google) para leitura de dados JSON.

### A Arquitetura do Inventário:

Este projeto foi desenhado para ensinar **quando usar qual coleção**:

*   **`ArrayList` (Mochila):** Usado para a mochila porque precisamos de ordem, acesso por índice e permitir itens duplicados (várias poções iguais).
*   **`HashMap` (Equipamentos):** Usado para os equipamentos porque precisamos garantir a **unicidade por slot**. A chave é o `Enum SlotArmadura` (ex: `CABECA`), garantindo que o jogador só possa ter um capacete equipado por vez.

### Integração de Sistemas:

*   **Desserialização de Dados:** O sistema não cria itens do zero no código. Ele lê o arquivo `catalogo.json` (gerado pelo projeto anterior "Catálogo de Itens") e transforma os dados JSON em objetos Java (`Pocao`, `Armadura`, etc.) prontos para uso.
*   **Polimorfismo:** O inventário trata tudo como `Item`, mas usa `instanceof` para decidir se um item pode ser equipado (`Armadura`) ou usado (`Usavel`).

---

## 🚀 Como Rodar

1.  **Clone este repositório.**
2.  **Configuração:** Certifique-se de que o arquivo `catalogo.json` está na raiz do projeto.
3.  **Dependências:** Adicione o `gson-2.10.1.jar` (na pasta `lib/`) às dependências do seu projeto no IntelliJ/Eclipse.
4.  **Execute:** Rode a classe `Main.java` e siga as instruções no console.
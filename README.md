# Conversor de Moedas em Java

Um conversor de moedas simples que usa a API Exchange Rate para obter taxas de câmbio em tempo real.

## Requisitos

- Java Development Kit (JDK) 8 ou superior
- Chave de API do Exchange Rate (gratuita)

## Configuração

### 1. Instalar o JDK

1. Baixe e instale o JDK:
   - Opção recomendada (gratuita): [Eclipse Temurin / Adoptium](https://adoptium.net/)
   - Alternativa: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)

2. Verifique a instalação abrindo o PowerShell e executando:
   ```powershell
   java -version
   javac -version
   ```

### 2. Obter uma API Key

1. Acesse [Exchange Rate API](https://www.exchangerate-api.com/)
2. Registre-se gratuitamente
3. Copie sua API key do painel de controle

### 3. Configurar a API Key (opcional)

Escolha uma das opções:

#### Opção A - Variável de ambiente permanente:
```powershell
setx EXCHANGE_API_KEY "SUA_API_KEY_AQUI"
```
*Nota: Feche e reabra o PowerShell após executar este comando.*

#### Opção B - Variável de ambiente temporária (só para sessão atual):
```powershell
$env:EXCHANGE_API_KEY = "SUA_API_KEY_AQUI"
```

#### Opção C - Informar a chave ao executar:
- O programa solicitará a chave na primeira execução se não encontrar a variável de ambiente.

## Compilar e Executar

### Compilar
```powershell
javac "c:\Users\valde\OneDrive\Documentos\alura\.vscode\Conversor de moedas\Conversor.java" -encoding UTF-8
```

### Executar
```powershell
java -cp "c:\Users\valde\OneDrive\Documentos\alura\.vscode\Conversor de moedas" Conversor
```

## Como Usar

1. Execute o programa
2. Escolha a opção 1 para converter moedas
3. Digite o código da moeda de origem (ex: USD, EUR, BRL)
4. Digite o código da moeda de destino
5. Digite o valor a ser convertido
6. O programa mostrará o resultado da conversão

### Códigos de Moeda Comuns

- USD: Dólar Americano
- EUR: Euro
- BRL: Real Brasileiro
- GBP: Libra Esterlina
- JPY: Iene Japonês
- ARS: Peso Argentino
- CLP: Peso Chileno
- CNY: Yuan Chinês

## Solução de Problemas

### Erro: 'javac' não é reconhecido
- Verifique se o JDK está instalado corretamente
- Verifique se a pasta bin do JDK está no PATH do sistema
- Tente reinstalar o JDK

### Erro de API
- Verifique se a API key está correta
- Confirme se tem conexão com a internet
- Verifique se o código da moeda é válido

### Resultado -1
- Indica erro na conversão
- Verifique a mensagem de erro exibida
- Confirme se os códigos das moedas estão corretos

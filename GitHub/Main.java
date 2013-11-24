import java.util.*;

public class Main {
  // Hash utilizado para armazenar os pares de chave valor, sendo a chave a palavra e o valor o número da palavra no
  // dicionario
  public static Hashtable <String, Integer> dicionarioHashtable; 
  
  public static void main (String args[])
  {
    Scanner scanner = new Scanner(System.in);
    
    // Cria hash com as palavras do dicionário
    dicionarioHashtable = new Hashtable<String, Integer>();
     
      while (scanner.hasNext()) 
      {
        int numeroDePalavrasDicionario = 0;
        String linha = scanner.nextLine();
        
        if (linha.equals("#"))
        {
          String textoCifrado = scanner.nextLine();
          decriptaTextoPorForcaBruta(textoCifrado);
          break;
        }
        else 
        {
          // Etapa 1 - Adiciona palavras no dicionário
          dicionarioHashtable.put(linha, numeroDePalavrasDicionario);
          numeroDePalavrasDicionario++;
        }
      }
    }
  
  // Etapa 2 - Método que tenta por força bruta a descoberta da chave
  public static void decriptaTextoPorForcaBruta (String textoCifrado)
  {
    int encontrouMaisPalavrasNoDicionario = 0;
    int nrPalavrasEncontradasNoDicionario = 0;
    String textoDecriptado = "";
    String textoClaro = "";
    // Método que testa todas as chaves possíveis (1 a 26)
    int chave = 26;
    
    while(chave > 0)
    {
      nrPalavrasEncontradasNoDicionario = 0;
      // Chama método que vai obter o texto claro usando a chave passada como parâmetro
      textoClaro = decriptaTexto(textoCifrado, chave);
      
      String[] palavras = textoClaro.split(" ");
      
      // Conta quantas coincidências no dicionário o texto que foi decriptografado contém
      for (int i = 0; i < palavras.length; i++) 
      {
        if (dicionarioHashtable.containsKey(palavras[i]))
        {
          nrPalavrasEncontradasNoDicionario++;
        }
      }
      
      // Se for a melhor opção até o momento considera o texto como o decriptado
      if (nrPalavrasEncontradasNoDicionario > encontrouMaisPalavrasNoDicionario)
      {
        textoDecriptado = textoClaro;
      }      
      chave--;
    }
    
    imprimeTextoDecriptado(textoDecriptado);
  }
  
  // Método que implementa o algoritmo de decriptografia utilizando o texto cifrado e a chave
  public static String decriptaTexto (String textoCifrado, int chave)
  {
    String textoDecifrado = "";
    char letra;
    
    for (int i = 0; i < textoCifrado.length(); i++) 
    {
      int caractere = -1;
      
      // Consideramos que o caractere em branco tenha valor 64
      if (Character.toString(textoCifrado.charAt(i)).equals(" "))
      {
        caractere = 64;
      }
      else 
        caractere = textoCifrado.charAt(i);
      
      // Definição de resto para o Java é diferente da definição matemática
      int charToInt = (caractere - 'A' + 1 - chave)%27;
      
      // Se a % b for negativo, e se b for positivo, então use para o valor do resto b + a % b
      if (charToInt < 0)
        charToInt = 27 + charToInt;
      
      // Transforma para char de volta 
      int intToChar = (charToInt - 1 + 'A');
      
      // Se valor for 64, então o caractere é um espaço
      if (intToChar == 64)
      {
        letra = ' ';
      }
      else
      {
        letra = (char) (intToChar);
      }
      textoDecifrado = textoDecifrado + Character.toString(letra);
    }
    
    return textoDecifrado;
  }
  
  // Etapa 3 - Imprime cada linha com no máximo 60 caracteres e sem espaços brancos no começo e no final da linha 
  public static void imprimeTextoDecriptado (String textoDecriptado)
  {
    String linha = "";
    String[] palavras = textoDecriptado.split(" ");
    
    for (int k = 0; k < palavras.length ; k++) 
    {
      String linhaComMaisUmaPalavra = "";
      
      // Tenta adicionar mais uma palavra na linha
      linhaComMaisUmaPalavra = linha + " " + palavras[k];    
      
      // Olha se não ultrapassa o tamanho máximo
      if (linhaComMaisUmaPalavra.length() <= 60) 
      {
        linha = linhaComMaisUmaPalavra;
      }
      // Se ultrapassar, imprime o que já tinha retirando os espaços em branco 
      // e grava a nova linha como sendo a palavra (se ela não for espaço em branco)
      else 
      {
        System.out.println (linha.trim());
        if (!palavras[k].equals(" "))
        {
          linha = palavras[k];
        }
        continue;
      }
    }
    // Imprime a linha final (se ela tiver menos que 60 caracteres
    System.out.println (linha.trim()); 
} 
  
}
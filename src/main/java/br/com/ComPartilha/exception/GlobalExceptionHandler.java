package br.com.ComPartilha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
- Classe responsável pelo tratamento global de erros da API
- Interpreta exceções lançadas em qualquer Controller e retorna
- Respostas padronizadas e limpas, sem expor stack trace
*/

@RestControllerAdvice // Interpreta exceções de todos os Controllers da aplicação
public class GlobalExceptionHandler {

    // Trata erros de validação (@valid)
    // Disparado quando um campo obrigatório está vazio ou inválido
    // Retorna status 400 bad Request com a lista de mensagens de erro
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        // Coleta todas as mensagens de erro dos campos inválidos
        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)// Pega a mensagem definida na anotação
                .collect(Collectors.toList());

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", 400);
        resposta.put("erro", "Erro de validação");
        resposta.put("mensagens", mensagens);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    // Trata erros de negócio (RuntimeException)
    // Disparado quando um erro não é encontrado no banco de dados
    // Retorna status 404 Not Found com a mensagem do erro
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", 404);
        resposta.put("erro", ex.getMessage()); // Mensagem definida no Service

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }
}
package br.com.ComPartilha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExceptionUtil {
    public static ResponseEntity<Map<String, Object>> tratarValidacao(MethodArgumentNotValidException ex) {
        List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", 400);
        resposta.put("erro", "Erro de validação");
        resposta.put("mensagens", mensagens);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    public static ResponseEntity<Map<String, Object>> tratarRuntime(RuntimeException ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", 404);
        resposta.put("erro", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    public static ResponseEntity<Map<String, Object>> tratarGenerico(Exception ex) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", 500);
        resposta.put("erro", "Erro interno do servidor");
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
    }
}


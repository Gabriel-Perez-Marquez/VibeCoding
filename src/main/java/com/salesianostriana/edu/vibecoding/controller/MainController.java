package com.salesianostriana.edu.vibecoding.controller;

import com.salesianostriana.edu.vibecoding.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MainController {

    private final JwtService jwtService;

    // 1. Endpoint para obtener el pase VIP (Token)
    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        // En la vida real validarías password aquí.
        String username = request.get("username");
        String token = jwtService.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }

    // 2. Endpoint PÚBLICO (Sin seguridad)
    @GetMapping("/public/status")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("🟢 Sistema Online. Acceso público permitido en Spring Boot 4.");
    }

    // 3. Endpoint PRIVADO (Requiere JWT)
    @GetMapping("/resource/secret")
    public ResponseEntity<String> privateEndpoint() {
        return ResponseEntity.ok("🚀 Acceso Autorizado. Estás viendo datos protegidos nivel Spring Security 7.");
    }
}
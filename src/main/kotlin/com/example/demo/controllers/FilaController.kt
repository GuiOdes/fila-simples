package com.example.demo.controllers

import com.example.demo.models.Fila
import com.example.demo.models.Senha
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fila")
class FilaController {

    private val fila = Fila(
        senhas = mutableListOf()
    )

    @GetMapping
    fun getFila(): List<Senha> {
        return fila.senhas.sortedBy { it.prioritaria }
    }

    @PostMapping
    fun novaSenha(@RequestBody senha: Senha) {
        fila.senhas.add(senha)
    }

    @DeleteMapping("/proximo")
    fun deletarSenha() {
        getFila().firstOrNull()?.let {
            fila.senhas.remove(it)
        }
    }
}
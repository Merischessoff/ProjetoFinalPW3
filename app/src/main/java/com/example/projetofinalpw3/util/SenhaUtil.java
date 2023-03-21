package com.example.projetofinalpw3.util;
import org.mindrot.jbcrypt.BCrypt;

public class SenhaUtil {
    public static String criptografarSenha(String senha) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(senha, salt);
    }

    public static boolean verificarSenha(String senha, String hashSenha) {
        return BCrypt.checkpw(senha, hashSenha);
    }
}

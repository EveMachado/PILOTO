package ufu.piloto.error.user;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String email) {
        super("Senha fornecida para o usuário " + email + " está inválida!");
    }
}

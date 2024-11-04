package ufu.piloto.error.user;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String email) {
        super("Usuário " + email + " não encontrado!");
    }
}

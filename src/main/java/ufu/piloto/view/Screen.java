package ufu.piloto.view;

import ufu.piloto.error.RequestException;
import ufu.piloto.error.user.InvalidPasswordException;
import ufu.piloto.error.user.UserAlreadyExists;
import ufu.piloto.error.user.UserNotFoundException;
import ufu.piloto.model.series.Series;
import ufu.piloto.model.user.User;
import ufu.piloto.repository.UserRepository;
import ufu.piloto.service.api.tmdb.TmdbService;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen {

    private User loggedUser;
    private final UserRepository userRepository;
    private final TmdbService tmdbService;

    public Screen() {
        this.userRepository = new UserRepository();
        this.tmdbService = TmdbService.getInstance();
        this.mostrarTelaLogin();
    }

    // Tela de Login
    private void mostrarTelaLogin() {
        JFrame frameLogin = new JFrame("Piloto - Login");
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(1300, 998);


        JPanel panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("PILOTO");
        lblTitulo.setForeground(Color.RED);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 45));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelLogin.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        panelLogin.add(lblEmail, gbc);

        gbc.gridx = 1;
        JTextField txtEmail = new JTextField(15);
        panelLogin.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        panelLogin.add(lblSenha, gbc);

        gbc.gridx = 1;
        JPasswordField txtSenha = new JPasswordField(15);
        panelLogin.add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(Color.RED);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        panelLogin.add(btnLogin, gbc);

        gbc.gridy = 4;
        JButton btnCadastrar = new JButton("Cadastrar-se");
        btnCadastrar.setBackground(Color.DARK_GRAY);
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 14));
        panelLogin.add(btnCadastrar, gbc);

        frameLogin.getContentPane().add(panelLogin, BorderLayout.CENTER);
        frameLogin.setVisible(true);

        // Listener do Login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());

                try {
                    loggedUser = userRepository.login(email, senha);
                    frameLogin.dispose();
                    mostrarTelaPrincipal();
                } catch (InvalidPasswordException | UserNotFoundException ex) {
                    JOptionPane.showMessageDialog(frameLogin, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Listener para cadastrar
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameLogin.dispose();
                mostrarTelaCadastro();
            }
        });
    }

    // Tela de Cadastro
    private void mostrarTelaCadastro() {
        JFrame frameCadastro = new JFrame("Piloto - Cadastro");
        frameCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCadastro.setSize(1305, 998);

        JPanel panelCadastro = new JPanel(new GridBagLayout());
        panelCadastro.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Cadastro");
        lblTitulo.setForeground(Color.RED);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelCadastro.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setForeground(Color.WHITE);
        lblNome.setFont(new Font("Arial", Font.PLAIN, 16));
        panelCadastro.add(lblNome, gbc);

        gbc.gridx = 1;
        JTextField txtNome = new JTextField(15);
        panelCadastro.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        panelCadastro.add(lblEmail, gbc);

        gbc.gridx = 1;
        JTextField txtEmail = new JTextField(15);
        panelCadastro.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 16));
        panelCadastro.add(lblSenha, gbc);

        gbc.gridx = 1;
        JPasswordField txtSenha = new JPasswordField(15);
        panelCadastro.add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(Color.RED);
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        panelCadastro.add(btnCadastrar, gbc);

        frameCadastro.getContentPane().add(panelCadastro, BorderLayout.CENTER);
        frameCadastro.setVisible(true);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                String email = txtEmail.getText();
                String senha = new String(txtSenha.getPassword());

                if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                    try {
                        userRepository.register(new User(nome, email, senha));
                        JOptionPane.showMessageDialog(frameCadastro, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        frameCadastro.dispose();
                        mostrarTelaLogin();
                    } catch (UserAlreadyExists ex) {
                        JOptionPane.showMessageDialog(frameCadastro, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frameCadastro, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Tela Principal
    private void mostrarTelaPrincipal() {
        JFrame frame = new JFrame("Piloto - Séries");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1305, 998);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.BLACK);

        JLabel lblTitulo = new JLabel("Bem-vindo ao Piloto!");
        lblTitulo.setForeground(Color.RED);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 25));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new GridLayout(5, 1, 10, 10));
        panelBotoes.setBackground(Color.BLACK);

        JButton btnBuscarSerie = new JButton("Buscar Série");
        JButton btnFavoritarSerie = new JButton("Favoritar Série");
        JButton btnAvaliarSerie = new JButton("Avaliar Série");
        JButton btnVerFavoritas = new JButton("Ver Favoritas");
        JButton btnVerAvaliacoes = new JButton("Ver Avaliações");

        JButton[] botoes = {btnBuscarSerie, btnFavoritarSerie, btnAvaliarSerie, btnVerFavoritas, btnVerAvaliacoes};
        for (JButton botao : botoes) {
            botao.setBackground(Color.RED);
            botao.setForeground(Color.WHITE);
            botao.setFont(new Font("Arial", Font.BOLD, 20));
            panelBotoes.add(botao);
        }

        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotoes, BorderLayout.CENTER);

        frame.getContentPane().add(panelPrincipal);
        frame.setVisible(true);

        btnBuscarSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Digite o título da série:");
                try {
                    List<Series> resultados = tmdbService.searchSeries(titulo);
                    StringBuilder mensagem = new StringBuilder("Séries encontradas:\n");
                    for (Series serie : resultados) {
                        mensagem.append(serie.getOriginalName()).append(" - ").append(serie.getSynopsis()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, mensagem.toString());
                } catch (RequestException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnFavoritarSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Funcionalidade não implementada!", "warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnAvaliarSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Funcionalidade não implementada!", "warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnVerFavoritas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Funcionalidade não implementada!", "warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnVerAvaliacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Funcionalidade não implementada!", "warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

}

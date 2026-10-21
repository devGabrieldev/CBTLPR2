package Calculadora;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    
    private JTextField txtDisplay;
    private JPanel panelBotoes;
    
    private double primeiroNumero = 0;
    private String operacao = "";
    private boolean novaOperacao = true;

    private boolean estadoErro = false;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        txtDisplay = new JTextField("0");
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        txtDisplay.setEditable(false);
        txtDisplay.setFont(new Font("Arial", Font.BOLD, 20));
        add(txtDisplay, BorderLayout.NORTH);

        panelBotoes = new JPanel(new GridLayout(5, 4, 3, 3));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "",  "",  ""   
        };

        for (String texto : botoes) {
            if (texto.isEmpty()) {
                panelBotoes.add(new JLabel("")); 
            } else {
                JButton botao = new JButton(texto);
                botao.setFont(new Font("Arial", Font.BOLD, 16));
                botao.addActionListener(this);
                panelBotoes.add(botao);
            }
        }

        add(panelBotoes, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (estadoErro && !comando.equals("C")) {
                return;
            }

            if ((comando.charAt(0) >= '0' && comando.charAt(0) <= '9') || comando.equals(".")) {
                if (novaOperacao) {
                    txtDisplay.setText(comando);
                    novaOperacao = false;
                } else {
                    if (comando.equals(".") && txtDisplay.getText().contains(".")) {
                        return; 
                    }
                    txtDisplay.setText(txtDisplay.getText() + comando);
                }
            } 
            else if (comando.equals("C")) {
                limparMemoria();
            } 
            else if (comando.equals("=")) {
                calcularResultado();
                operacao = "";
                novaOperacao = true;
            } 
            else {
                if (!operacao.isEmpty()) {
                    calcularResultado();
                }
                if (estadoErro) return;

                primeiroNumero = Double.parseDouble(txtDisplay.getText());
                operacao = comando;
                novaOperacao = true;
            }
        } catch (NumberFormatException ex) {
            definirErro("Erro de Formato");
        } catch (ArithmeticException ex) {
            definirErro(ex.getMessage());
        } catch (Exception ex) {
            definirErro("Erro Inesperado");
        } finally {
            System.out.println("Botão clicado: " + comando);
        }
    }

    private void calcularResultado() {
        if (operacao.isEmpty()) return;

        double segundoNumero;
        try {
            segundoNumero = Double.parseDouble(txtDisplay.getText());
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Erro de Formato");
        }

        double resultado = 0;

        switch (operacao) {
            case "+": resultado = primeiroNumero + segundoNumero; break;
            case "-": resultado = primeiroNumero - segundoNumero; break;
            case "*": resultado = primeiroNumero * segundoNumero; break;
            case "/": 
                if (segundoNumero == 0) {
                    throw new ArithmeticException("Divisão por zero!");
                }
                resultado = primeiroNumero / segundoNumero; 
                break;
        }

        if (Double.isInfinite(resultado) || Double.isNaN(resultado)) {
            throw new ArithmeticException("Erro de Cálculo");
        }

        if (resultado % 1 == 0) {
            txtDisplay.setText(String.valueOf((long) resultado));
        } else {
            txtDisplay.setText(String.valueOf(resultado));
        }
        primeiroNumero = resultado;
    }

    private void definirErro(String mensagem) {
        txtDisplay.setText(mensagem);
        estadoErro = true;
        novaOperacao = true;
    }

    private void limparMemoria() {
        primeiroNumero = 0;
        operacao = "";
        txtDisplay.setText("0");
        novaOperacao = true;
        estadoErro = false; 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculadora().setVisible(true);
        });
    }
}


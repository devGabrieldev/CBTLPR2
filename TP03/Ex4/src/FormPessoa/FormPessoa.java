package FormPessoa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; 

class Pessoa {
    private int numero;
    private String nome;
    private char sexo;
    private int idade;
    
    public static int kp = 0;

    public void setDados(int numero, String nome, char sexo, int idade) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome é de preenchimento obrigatório.");
        }
        sexo = Character.toUpperCase(sexo);
        if (sexo != 'M' && sexo != 'F') {
            throw new IllegalArgumentException("O sexo deve ser estritamente M ou F.");
        }
        if (idade <= 0) {
            throw new IllegalArgumentException("A idade deve ser maior que zero.");
        }

        this.numero = numero;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        
        kp++;
    }

    public int getNumero() { return numero; }
    public String getNome() { return nome; }
    public char getSexo() { return sexo; }
    public int getIdade() { return idade; }
}

public class FormPessoa extends JFrame implements ActionListener {
    
    private JTextField txtNumero, txtNome, txtIdade;
    private JRadioButton rbMasculino, rbFeminino;
    private ButtonGroup grupoSexo;
    private JButton btnOK, btnLimpar, btnMostrar, btnSair;
    
    private ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    private int proximoNumero = 1;

    public FormPessoa() {
        setTitle("Semana 06 - Exercicio 01");
        setSize(450, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout(5, 5));

        JPanel painelDados = new JPanel(new GridLayout(4, 2, 5, 5));

        painelDados.add(new JLabel(" Numero:"));
        txtNumero = new JTextField(String.valueOf(proximoNumero));
        txtNumero.setEditable(false);
        painelDados.add(txtNumero);

        painelDados.add(new JLabel(" Nome:"));
        txtNome = new JTextField();
        painelDados.add(txtNome);

        painelDados.add(new JLabel(" Sexo:"));
        JPanel painelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        rbMasculino = new JRadioButton("M", true);
        rbFeminino = new JRadioButton("F", false);
        grupoSexo = new ButtonGroup();
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFeminino);
        painelRadio.add(rbMasculino);
        painelRadio.add(rbFeminino);
        painelDados.add(painelRadio);

        painelDados.add(new JLabel(" Idade:"));
        txtIdade = new JTextField();
        painelDados.add(txtIdade);

        add(painelDados, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 2, 2));
        
        btnOK = new JButton("OK");
        btnLimpar = new JButton("Limpar");
        btnMostrar = new JButton("Mostrar");
        btnSair = new JButton("Sair");

        btnOK.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnMostrar.addActionListener(this);
        btnSair.addActionListener(this);

        painelBotoes.add(btnOK);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnMostrar);
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOK) {
            try {
                int numero = Integer.parseInt(txtNumero.getText());
                String nome = txtNome.getText().trim();
                
                if (nome.isEmpty() || txtIdade.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nome e Idade são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                char sexo = rbMasculino.isSelected() ? 'M' : 'F';
                int idade = Integer.parseInt(txtIdade.getText().trim());

                Pessoa novaPessoa = new Pessoa();
                novaPessoa.setDados(numero, nome, sexo, idade);
                
                listaPessoas.add(novaPessoa);
                
                JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                proximoNumero++;
                limparCamposTela();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "A idade deve ser um número inteiro válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validação Falhou", JOptionPane.WARNING_MESSAGE);
            }
        } 
        else if (e.getSource() == btnLimpar) {
            limparCamposTela();
        } 
        else if (e.getSource() == btnMostrar) {
            if (listaPessoas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma pessoa foi cadastrada ainda!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                StringBuilder relatorio = new StringBuilder();
                relatorio.append("=== LISTA DE PESSOAS CADASTRADAS ===\n\n");
                
                for (Pessoa p : listaPessoas) {
                    relatorio.append("Número: ").append(p.getNumero()).append("\n")
                             .append("Nome: ").append(p.getNome()).append("\n")
                             .append("Sexo: ").append(p.getSexo()).append("\n")
                             .append("Idade: ").append(p.getIdade()).append("\n")
                             .append("------------------------------------\n");
                }
                
                relatorio.append("\nContador Total (kp): ").append(Pessoa.kp).append(" pessoa(s) distintas.");
                
                JTextArea textArea = new JTextArea(relatorio.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(350, 300));
                
                JOptionPane.showMessageDialog(this, scrollPane, "Histórico do Objeto", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getSource() == btnSair) {
            System.exit(0);
        }
    }

    private void limparCamposTela() {
        txtNumero.setText(String.valueOf(proximoNumero));
        txtNome.setText("");
        txtIdade.setText("");
        rbMasculino.setSelected(true);
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormPessoa().setVisible(true);
        });
    }
}

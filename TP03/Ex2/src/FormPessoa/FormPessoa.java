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
    
    private JTextField txtNumero, txtNome, txtSexo, txtIdade;
    private JButton btnOK, btnMostrar;
    
    private ArrayList<Pessoa> listaPessoas = new ArrayList<>();
    private int proximoNumero = 1;

    public FormPessoa() {
        setTitle("Cadastro de Pessoa");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 8, 8));

        add(new JLabel(" Número:"));
        txtNumero = new JTextField(String.valueOf(proximoNumero));
        txtNumero.setEditable(false);
        add(txtNumero);

        add(new JLabel(" Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel(" Sexo (M/F):"));
        txtSexo = new JTextField(); 
        add(txtSexo);

        add(new JLabel(" Idade:"));
        txtIdade = new JTextField();
        add(txtIdade);

        btnOK = new JButton("OK");
        btnMostrar = new JButton("Mostrar");
        
        btnOK.addActionListener(this);
        btnMostrar.addActionListener(this);

        add(btnOK);
        add(btnMostrar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOK) {
            try {
                int numero = Integer.parseInt(txtNumero.getText());
                String nome = txtNome.getText().trim();
                String sexoTexto = txtSexo.getText().trim();
                
                if (nome.isEmpty() || sexoTexto.isEmpty() || txtIdade.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nome, Sexo e Idade são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                char sexo = sexoTexto.charAt(0);
                int idade = Integer.parseInt(txtIdade.getText().trim());

                Pessoa novaPessoa = new Pessoa();
                novaPessoa.setDados(numero, nome, sexo, idade);
                
                listaPessoas.add(novaPessoa);
                
                JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                
                proximoNumero++;
                txtNumero.setText(String.valueOf(proximoNumero));
                txtNome.setText("");
                txtSexo.setText(""); 
                txtIdade.setText("");
                txtNome.requestFocus(); 
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "A idade deve ser um número inteiro válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validação Falhou", JOptionPane.WARNING_MESSAGE);
            }
        } 
        else if (e.getSource() == btnMostrar) {
            if (listaPessoas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma pessoa foi cadastrada ainda!", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                StringBuilder relatorio = new StringBuilder();
                relatorio.append("=== TODAS AS PESSOAS CADASTRADAS ===\n\n");
                
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
                scrollPane.setPreferredSize(new Dimension(320, 250));
                
                JOptionPane.showMessageDialog(this, scrollPane, "Dados do Histórico", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormPessoa().setVisible(true);
        });
    }
}


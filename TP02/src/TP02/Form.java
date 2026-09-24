package TP02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame {

    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtEndereco;
    
    private List<Aluno> listaAlunos;

    public Form() {
        listaAlunos = new ArrayList<>();

        setTitle("TP02 - LP2I4");
        setSize(400, 210); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuSobre = new JMenu("Sobre");
        JMenuItem itemAutor = new JMenuItem("Autor");
        
        itemAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Form.this, 
                        "CBTPLR2 - TP02 \n"
                      + "Gabriel Ribeiro -   CB3021726",                     
                        "Créditos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        menuSobre.add(itemAutor);
        barraMenu.add(menuSobre);
        setJMenuBar(barraMenu); 

        setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel(new GridLayout(3, 2, 10, 10));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        painelSuperior.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelSuperior.add(txtNome);

        painelSuperior.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelSuperior.add(txtIdade);

        painelSuperior.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painelSuperior.add(txtEndereco);

        JPanel painelInferior = new JPanel(new GridLayout(1, 4, 5, 5));
        painelInferior.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton btnOk = new JButton("Ok");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");

        painelInferior.add(btnOk);
        painelInferior.add(btnLimpar);
        painelInferior.add(btnMostrar);
        painelInferior.add(btnSair);

        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);


        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtNome.getText().trim().isEmpty() || txtIdade.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(Form.this, 
                                "Por favor, preencha pelo menos Nome e Idade.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Aluno novoAluno = new Aluno();
                    novoAluno.setNome(txtNome.getText());
                    novoAluno.setIdade(Integer.parseInt(txtIdade.getText().trim()));
                    novoAluno.setEndereco(txtEndereco.getText());

                     
                    listaAlunos.add(novoAluno);

                    JOptionPane.showMessageDialog(Form.this, "Aluno cadastrado com sucesso!");
                    
                    limparCampos();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Form.this, 
                            "A idade precisa ser um número inteiro válido.", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listaAlunos.isEmpty()) {
                    JOptionPane.showMessageDialog(Form.this, 
                            "Nenhum aluno cadastrado até o momento.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                StringBuilder mensagem = new StringBuilder("Resultado\n");
                for (Aluno aluno : listaAlunos) {
                    mensagem.append("Id: ").append(aluno.getUuid())
                            .append(" Nome: ").append(aluno.getNome())
                            .append("\n");
                }

                JOptionPane.showMessageDialog(Form.this, mensagem.toString(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtEndereco.setText("");
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Form().setVisible(true);
            }
        });
    }
}

import javax.swing.JButton; //Gabriel Ribeiro CB3021726
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TelaPrincipal extends JFrame {

    private JTextField txtMaximo;
    private JTextField txtPrioridade1;
    private JTextField txtPrioridade2;
    private JButton btnIniciar;
    private JButton btnLimpar;
    private Contador contador1;
    private Contador contador2;

    public TelaPrincipal() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Trabalho Prático 5");
        setSize(520, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblMaximo = new JLabel("Máximo:");
        lblMaximo.setBounds(40, 20, 80, 25);
        add(lblMaximo);

        txtMaximo = new JTextField("1000");
        txtMaximo.setBounds(125, 20, 120, 25);
        add(txtMaximo);

        JLabel lblP1 = new JLabel("Prioridade 1:");
        lblP1.setBounds(40, 55, 80, 25);
        add(lblP1);

        txtPrioridade1 = new JTextField("1");
        txtPrioridade1.setBounds(125, 55, 120, 25);
        add(txtPrioridade1);

        JLabel lblP2 = new JLabel("Prioridade 2:");
        lblP2.setBounds(40, 90, 80, 25);
        add(lblP2);

        txtPrioridade2 = new JTextField("10");
        txtPrioridade2.setBounds(125, 90, 120, 25);
        add(txtPrioridade2);

        JLabel lblInfoPrioridade = new JLabel("}   Número entre 1 e 10");
        lblInfoPrioridade.setBounds(255, 65, 180, 35);
        add(lblInfoPrioridade);

        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(215, 130, 80, 28);
        add(btnIniciar);

        contador1 = new Contador();
        contador1.setBounds(110, 175, 85, 145);
        add(contador1);

        contador2 = new Contador();
        contador2.setBounds(315, 175, 85, 145);
        add(contador2);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(215, 340, 80, 28);
        add(btnLimpar);

        btnIniciar.addActionListener(e -> iniciarThreads());
        btnLimpar.addActionListener(e -> limparTela());
    }

    private void iniciarThreads() {
        try {
            int maximo = Integer.parseInt(txtMaximo.getText().trim());
            int p1 = Integer.parseInt(txtPrioridade1.getText().trim());
            int p2 = Integer.parseInt(txtPrioridade2.getText().trim());

            if (p1 < Thread.MIN_PRIORITY || p1 > Thread.MAX_PRIORITY ||
                p2 < Thread.MIN_PRIORITY || p2 > Thread.MAX_PRIORITY) {
                JOptionPane.showMessageDialog(this,
                        "As prioridades devem estar entre 1 e 10.",
                        "Valor Inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            contador1.limpar();
            contador2.limpar();

            contador1.setMaximo(maximo);
            contador2.setMaximo(maximo);

            Thread t1 = new Thread(contador1);
            Thread t2 = new Thread(contador2);

            t1.setPriority(p1);
            t2.setPriority(p2);

            t1.start();
            t2.start();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos com números inteiros válidos.",
                    "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparTela() {
        contador1.limpar();
        contador2.limpar();
        txtMaximo.setText("");
        txtPrioridade1.setText("");
        txtPrioridade2.setText("");
        txtMaximo.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}

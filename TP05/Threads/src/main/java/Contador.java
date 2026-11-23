import java.awt.BorderLayout; //Gabriel Ribeiro CB3021726
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Contador extends JPanel implements Runnable {

    private final DefaultListModel<Integer> modeloLista;
    private final JList<Integer> listaNumeros;
    private final JLabel lblFinal;
    private int maximo;

    public Contador() {
        setLayout(new BorderLayout(0, 10));

        modeloLista = new DefaultListModel<>();
        listaNumeros = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaNumeros);

        lblFinal = new JLabel("Final", SwingConstants.CENTER);
        lblFinal.setVisible(false);

        add(scrollPane, BorderLayout.CENTER);
        add(lblFinal, BorderLayout.SOUTH);
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public void limpar() {
        modeloLista.clear();
        lblFinal.setVisible(false);
    }

    @Override
    public void run() {
        for (int i = 0; i < maximo; i++) {
            final int valor = i;
            
            SwingUtilities.invokeLater(() -> {
                modeloLista.addElement(valor);
                int ultimoIndice = modeloLista.getSize() - 1;
                if (ultimoIndice >= 0) {
                    listaNumeros.ensureIndexIsVisible(ultimoIndice);
                }
            });

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        SwingUtilities.invokeLater(() -> lblFinal.setVisible(true));
    }
}

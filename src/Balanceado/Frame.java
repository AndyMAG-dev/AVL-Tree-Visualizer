package Balanceado;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import cu.edu.cujae.ceis.tree.iterators.binary.PosOrderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.PreorderIterator;
import cu.edu.cujae.ceis.tree.iterators.binary.SymmetricIterator;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;



public class Frame extends JFrame {
	public JTextField textField;
	public JTextArea textArea;
	public JButton btnInsertar;
	private JButton btnInsertar_1;
	private JButton btnEliminar;
	private JButton btnBuscar;
    private JTextField textFieldRecorrido;
	private JLabel lblRecorrido;
    private JLabel lblNodos;
    private JLabel lblTitulo;
	private JButton btnPreOrden;
	private JButton btnEntreOrden;
	private JButton btnPosorden;
    private ArbolAVL<Integer> arbol;

	public Frame() {
        setResizable(false);
		setTitle("Arbol AVL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 647);
		JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(45, 45, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
        textArea.setSelectionColor(new Color(230, 230, 250));
		textArea.setBackground(new Color(200, 200, 200));
		textArea.setFont(new Font("Arial", Font.BOLD, 25));
		textArea.setBounds(30, 52, 682, 371);
		contentPane.add(textArea);
        textArea.setEditable(false);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.BOLD, 18));
		textField.setBounds(36, 506, 116, 33);
        textField.setBackground(new Color(200, 200, 200));
		contentPane.add(textField);
		textField.setColumns(10);
        textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e){
				int key = e.getKeyChar();
				boolean numeros = key >= 48 && key <= 57;

			            
			     if (!(numeros))
			    {
			        e.consume();
			    }
			    if (textField.getText().trim().length() == 5) {
			         e.consume();
			    }
			}
		});
		
		btnInsertar_1 = new JButton("Insertar");
		btnInsertar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numero = Integer.parseInt(textField.getText());
                arbol.insertar(numero);		
				String dibujo = arbol.exportarArbolAVL();
                textArea.setText(dibujo);
                textField.setText("");
			}
		});
		btnInsertar_1.setBounds(176, 510, 97, 25);
        btnInsertar_1.setForeground(new Color(0, 0, 0));
		btnInsertar_1.setBackground(new Color(170, 0, 255));
        btnInsertar_1.setFocusable(false);
		btnInsertar_1.setBorderPainted(false);
		contentPane.add(btnInsertar_1);
		
		btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numero = Integer.parseInt(textField.getText());
                arbol.eliminar(numero);
				String dibujo = arbol.exportarArbolAVL();
                textArea.setText(dibujo);
                textField.setText("");
			}
		});
		btnEliminar.setBounds(176, 555, 97, 25);
        btnEliminar.setForeground(new Color(0, 0, 0));
		btnEliminar.setBackground(new Color(170, 0, 255));
        btnEliminar.setFocusable(false);
		btnEliminar.setBorderPainted(false);
		contentPane.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numero = Integer.parseInt(textField.getText());
                boolean existe = arbol.buscar(numero);
                textField.setText("");
                String mensaje = existe ? "El número si existe en el árbol." : "El número no existe en el árbol.";
                JOptionPane.showMessageDialog(null, mensaje, "Búsqueda de Número", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnBuscar.setBounds(176, 464, 97, 25);
        btnBuscar.setForeground(new Color(0, 0, 0));
		btnBuscar.setBackground(new Color(170, 0, 255));
        btnBuscar.setFocusable(false);
		btnBuscar.setBorderPainted(false);
		contentPane.add(btnBuscar);

        textFieldRecorrido = new JTextField();
		textFieldRecorrido.setFont(new Font("Arial", Font.BOLD, 18));
		textFieldRecorrido.setBackground(new Color(200, 200, 200));
		textFieldRecorrido.setBounds(377, 481, 336, 33);
		contentPane.add(textFieldRecorrido);
		textFieldRecorrido.setColumns(10);
        textFieldRecorrido.setEditable(false);
		
		lblRecorrido = new JLabel("Recorrido:");
        lblRecorrido.setFont(new Font("Arial", Font.BOLD, 18));
		lblRecorrido.setBounds(380, 450, 100, 16);
        lblRecorrido.setForeground(new Color(200,200,200));
        lblRecorrido.setBackground(new Color(200,200,200));
		contentPane.add(lblRecorrido);

        lblNodos = new JLabel("Nodos:");
        lblNodos.setFont(new Font("Arial", Font.BOLD, 18));
		lblNodos.setBounds(40, 460, 100, 16);
        lblNodos.setForeground(new Color(200,200,200));
        lblNodos.setBackground(new Color(200,200,200));
		contentPane.add(lblNodos);

        lblTitulo = new JLabel("Representación horizontal de árbol AVL:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setBounds(50, 20, 600, 16);
        lblTitulo.setForeground(new Color(200,200,200));
        lblTitulo.setBackground(new Color(200,200,200));
		contentPane.add(lblTitulo);
		
		btnPreOrden = new JButton("Preorden");
		btnPreOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                PreorderIterator<Integer> iterador = arbol.preOrderIterator();
                String recorrido = "";
                while(iterador.hasNext()){
                    recorrido = recorrido + iterador.next() + ", ";
                }
                textFieldRecorrido.setText(recorrido);
    
			}
		});
		btnPreOrden.setBounds(388, 527, 97, 25);
        btnPreOrden.setForeground(new Color(0, 0, 0));
		btnPreOrden.setBackground(new Color(170, 0, 255));
        btnPreOrden.setFocusable(false);
		btnPreOrden.setBorderPainted(false);
		contentPane.add(btnPreOrden);
		
		btnEntreOrden = new JButton("Entreorden");
        btnEntreOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                SymmetricIterator<Integer> iterador = arbol.symmetricIterator();
                String recorrido = "";
                while(iterador.hasNext()){
                    recorrido = recorrido + iterador.next() + ", ";
                }
                textFieldRecorrido.setText(recorrido);
    
			}
		});
		btnEntreOrden.setBounds(497, 527, 97, 25);
        btnEntreOrden.setForeground(new Color(0, 0, 0));
		btnEntreOrden.setBackground(new Color(170, 0, 255));
        btnEntreOrden.setFocusable(false);
		btnEntreOrden.setBorderPainted(false);
		contentPane.add(btnEntreOrden);
		
		btnPosorden = new JButton("Posorden");
        btnPosorden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                PosOrderIterator<Integer> iterador = arbol.posOrderIterator();
                String recorrido = "";
                while(iterador.hasNext()){
                    recorrido = recorrido + iterador.next() + ", ";
                }
                textFieldRecorrido.setText(recorrido);
    
			}
		});
        btnPosorden.setBounds(606, 527, 97, 25);
        btnPosorden.setForeground(new Color(0, 0, 0));
		btnPosorden.setBackground(new Color(170, 0, 255));
        btnPosorden.setFocusable(false);
		btnPosorden.setBorderPainted(false);
		contentPane.add(btnPosorden);

        arbol = new ArbolAVL<Integer>();
	}
}
